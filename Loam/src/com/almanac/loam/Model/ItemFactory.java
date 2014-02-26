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
		Item redMushroom = new Item("Red Mushroom", 50, redTexture);
		world.addAtEmptyLocation(redMushroom);
		return redMushroom;
	}
	
	public Item newBlueMushroom() {
		Item blueMushroom = new Item("Blue Mushroom", 100, blueTexture);
		world.addAtEmptyLocation(blueMushroom);
		return blueMushroom;
	}
	
	public Item newGoldMushroom() {
		Item goldMushroom = new Item("Gold Mushroom", 1000, goldTexture);
		world.addAtEmptyLocation(goldMushroom);
		return goldMushroom;
	}
}
