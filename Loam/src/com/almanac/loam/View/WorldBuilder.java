package com.almanac.loam.View;

import com.almanac.loam.Loam;
import com.almanac.loam.Model.Tile;


public class WorldBuilder {
	Loam game;

	private int width;
	private int height;
	
	private Tile[][] tiles;
	private float[][] perlin;
	
	public WorldBuilder(int width, int height) {

		this.width = width;
		this.height = height;
		this.tiles = new Tile[width][height];
		

		
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
	
	public WorldBuilder perlinMap(int octave, float thresholdLow, float thresholdHigh) {
		perlin = PerlinNoiseGenerator.generateWhiteNoise(width, height);
		perlin = PerlinNoiseGenerator.generateSmoothNoise(perlin, octave);
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				tiles[x][y] = perlin[x][y] > thresholdLow && perlin[x][y] < thresholdHigh ? Tile.GRASS : Tile.ROCK;
			}
		}
		return this;
	}
	
	public WorldBuilder addDirtMap() {
		// Perlin noise this
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

	public WorldBuilder makeCaves() {
		return randomizeTiles().smooth(11);
	}
	
}
