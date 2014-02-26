package com.almanac.loam.Model;

import com.almanac.loam.View.World;
import com.badlogic.gdx.graphics.Texture;

public class ItemFactory {
	private World world;
	private Texture redTexture;
	private Texture blueTexture;
	private Texture goldTexture;
	
	public ItemFactory(World world) {
		this.world = world;
	}
	
	public Item newRedMushroom() {
		Texture redTexture = new Texture("data/redMushroom.png");
		Item redMushroom = new Item("Red Mushroom", 50, redTexture);
		world.addAtEmptyLocation(redMushroom);
		return redMushroom;
	}
	
	public Item newBlueMushroom() {
		Texture blueTexture = new Texture("data/blueMushroom.png");
		Item blueMushroom = new Item("Blue Mushroom", 100, blueTexture);
		world.addAtEmptyLocation(blueMushroom);
		return blueMushroom;
	}
	
	public Item newGoldMushroom() {
		Texture goldTexture = new Texture("data/goldMushroom.png");
		Item goldMushroom = new Item("Gold Mushroom", 1000, goldTexture);
		world.addAtEmptyLocation(goldMushroom);
		return goldMushroom;
	}
}
