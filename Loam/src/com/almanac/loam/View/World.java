package com.almanac.loam.View;

import java.util.ArrayList;
import java.util.List;

import com.almanac.loam.Loam;
import com.almanac.loam.Model.Creature;
import com.almanac.loam.Model.Item;
import com.almanac.loam.Model.Tile;
import com.badlogic.gdx.graphics.Texture;

public class World {
	Loam game;

	private Tile[][] tiles;
	private List<Creature> creatures;
	
	// Only one item per tile, for now
	private Item[][] items;
	
	private int width;
	private int height;
	
	/*
	 * 	Constructor
	 */
	
	public World(Loam game, Tile[][] tiles) {
		this.game = game;

		this.tiles = tiles;
		this.width = tiles.length;
		this.height = tiles[0].length;
		
		this.creatures = new ArrayList<Creature>();
		this.items = new Item[width][height];
	}
	
	/*
	 * 	Methods
	 */
	
	public Tile tile(int x, int y){
        if (x < 0 || x >= width || y < 0 || y >= height)
            return Tile.BOUNDS;
        else
            return tiles[x][y];
    }
	
	public Creature creature(int x, int y){
		for (Creature c : creatures){
			if (c.x == x && c.y == y)
				return c;
		}
		return null;
	}
	
	public void addAtEmptyLocation(Creature creature) {
		int x;
		int y;
			
		// Pick random spots until you find a walkable one
		do {
			x = (int)(Math.random() * width);
			y = (int)(Math.random() * height);
		}
		while (!tile(x, y).isGround() || creature(x, y) != null);	// Make sure no other creature is there
		
		// Put the creature there
		creature.x = x;
		creature.y = y;

		// Add the creature to the master list
		creatures.add(creature);
	}
	
	public void addAtEmptyLocation(Item item) {
		int x;
		int y;
		
		do {
			x = (int)(Math.random() * width);
			y = (int)(Math.random() * height);
		}
		while (!tile(x, y).isGround() || item(x, y) != null);
		
		System.out.println("Adding item");
		System.out.println(item.texture());
		
		items[x][y] = item;
	}
	
	public void remove(Creature target) {
		creatures.remove(target);
	}
	
	public void update() {
		
		List<Creature> toUpdate = new ArrayList<Creature>(creatures);
		for (Creature creature : toUpdate) {
			
			creature.update();
		}
	}
	
	public void remove(int x, int y) {
		items[x][y] = null;
	}
		
	/*
	 * 	Getters
	 */
	
	public int width() {
		return width;
	}
	
	public int height() {
		return height;
	}
	
	public Item item(int x, int y) {
		return items[x][y];
	}
	
	public Item[][] getItems() {
		return items;
	}
	
	public List<Creature> getCreatures() {
		return creatures;
	}
	
	public Texture texture(int x, int y) {
		Creature creature = creature(x, y);

		if (creature != null) {
			return creature.texture();
		}
		
		if (item(x, y) != null) {
			return item(x, y).texture();
		}
		
		return tile(x, y).texture();
    }
	
	public void dispose() {
		
	}
	
}
