package com.almanac.loam.View;

import com.almanac.loam.Loam;
import com.almanac.loam.Model.Tile;


public class WorldBuilder {
	Loam game;

	private int width;
	private int height;
	
	private Tile[][] tiles;
	private float[][] n;
	
	public WorldBuilder(int width, int height) {

		this.width = width;
		this.height = height;
		this.tiles = new Tile[width][height];
		
		n = PerlinNoiseGenerator.generateWhiteNoise(width, height);
		
		this.n = PerlinNoiseGenerator.generateSmoothNoise(n, 4);
		
		for (int x = 0; x < n.length; x++) {
			for (int y = 0; y < n[x].length; y++) {
				System.out.println(n[x][y]);
			}
		}
	}
	
	public World build(Loam game) {
		return new World(game, tiles);
	}
	
	private WorldBuilder randomizeTiles() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				tiles[x][y] = Math.random() < 0.5 ? Tile.GRASS : Tile.ROCK;
			}
		}
		return this;
	}
	
	public WorldBuilder perlinTiles() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				tiles[x][y] = n[x][y] > 0.3 && n[x][y] < 0.5 ? Tile.GRASS : Tile.ROCK;
			}
		}
		return this;
	}
	
	public WorldBuilder addDirt() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (tiles[x][y] == Tile.GRASS) {
					tiles[x][y] = Math.random() < 0.5 ? Tile.GRASS : Tile.DIRT;
				}
			}
		}
		return this;
	}
	
	private WorldBuilder smooth(int times) {
        Tile[][] tiles2 = new Tile[width][height];
        for (int time = 0; time < times; time++) {
 
         for (int x = 0; x < width; x++) {
             for (int y = 0; y < height; y++) {
              int floors = 0;
              int rocks = 0;
 
              for (int ox = -1; ox < 2; ox++) {
                  for (int oy = -1; oy < 2; oy++) {
                   if (x + ox < 0 || x + ox >= width || y + oy < 0
                        || y + oy >= height)
                       continue;
 
                   if (tiles[x + ox][y + oy] == Tile.GRASS)
                       floors++;
                   else
                       rocks++;
                  }
              }
              tiles2[x][y] = floors >= rocks ? Tile.GRASS : Tile.ROCK;
             }
         }
         tiles = tiles2;
        }
        return this;
    }
	
	public WorldBuilder smoothDirt(int times) {
        Tile[][] tiles2 = new Tile[width][height];
        for (int time = 0; time < times; time++) {
 
         for (int x = 0; x < width; x++) {
             for (int y = 0; y < height; y++) {
              int floors = 0;
              int dirt = 0;
 
              for (int ox = -1; ox < 2; ox++) {
                  for (int oy = -1; oy < 2; oy++) {
                   if (x + ox < 0 || x + ox >= width || y + oy < 0
                        || y + oy >= height)
                       continue;
 
                   if (tiles[x + ox][y + oy] == Tile.GRASS || tiles[x + ox][y + oy] == Tile.ROCK )
                       floors++;
                   else
                       dirt++;
                  }
              }
              tiles2[x][y] = floors >= dirt ? Tile.GRASS : Tile.DIRT;
             }
         }
         tiles = tiles2;
        }
        return this;
    }
	

	
	public WorldBuilder makeCaves() {
		return randomizeTiles().smooth(11);
	}
	
	public WorldBuilder makeDirty(WorldBuilder tiles) {
		return tiles.addDirt().smoothDirt(11);
	}
}
