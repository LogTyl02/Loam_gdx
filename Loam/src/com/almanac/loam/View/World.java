package com.almanac.loam.View;

import java.util.ArrayList;
import java.util.List;

import com.almanac.loam.Loam;
import com.almanac.loam.Model.Creature;
import com.almanac.loam.Model.CreatureFactory;
import com.almanac.loam.Model.FieldOfView;
import com.almanac.loam.Model.Item;
import com.almanac.loam.Model.ItemFactory;
import com.almanac.loam.Model.Tile;
import com.badlogic.gdx.graphics.Texture;

public class World {
	Loam game;

	private Tile[][] tiles;
	private List<Creature> creatures;
	
	private Creature player;
	private Creature darkYoung;
	private FieldOfView fov;
	
	private CreatureFactory creatureFactory;
	private ItemFactory itemFactory;
	private List<String> messages;
	
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
		
		messages =	new ArrayList<String>();
		fov = new FieldOfView(this);
		
		CreatureFactory creatureFactory = new CreatureFactory(this, fov);
		ItemFactory itemFactory = new ItemFactory(this);
		
		createCreatures(creatureFactory);
		createItems(itemFactory);
		
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
	
	public Tile[][] getTiles() {
		return tiles;
	}
	
	public void setTile(int x, int y, Tile tile) {
		tiles[x][y] = tile;
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
	
	public FieldOfView fov() {
		return fov;
	}
	
	public Creature player() {
		return player;
	}
	
	public Item item(int x, int y) {
		return items[x][y];
	}
	
	public Item[][] getItems() {
		return items;
	}
	
	
	private void createCreatures(CreatureFactory creatureFactory) {
		player = creatureFactory.newPlayer(messages);
		System.out.println("Player spawned at " + player.x + " " + player.y);
		darkYoung = creatureFactory.newDarkYoung();
		System.out.println("Dark Young spawned at " + darkYoung.x + " " + darkYoung.y);
	}
	
	private void createItems(ItemFactory itemFactory) {
		for (int i = 0; i < 100; i++) {
			itemFactory.newRedMushroom();
			System.out.println("New red mushroom");
		}
		
		for (int i = 0; i < 30; i++) {
			itemFactory.newBlueMushroom();
		}
		
		for (int i = 0; i < 2; i++) {
			itemFactory.newGoldMushroom();
		}
	}
	
	public int px() {
		return player.x;
	}
	
	public int py() {
		return player.y;
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
