package com.almanac.loam.Model;

import com.badlogic.gdx.graphics.Texture;

public enum Tile {
	GRASS("data/grass32.png"),
	ROCK("data/rock.png"),
	BOUNDS("data/bounds.png"),
	UNKNOWN("data/bounds.png");
	
	private Texture texture;
	
	public Texture texture() {
		return texture;
	}
	
	Tile(String texturePath) {
		this.texture = new Texture(texturePath);
	}
	
	public boolean isGround() {
		return this != ROCK && this != BOUNDS;
	}
	
	public boolean isRock() {
		return this == ROCK;
	}
	
}
