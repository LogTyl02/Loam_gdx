package com.almanac.loam.Model;

import com.badlogic.gdx.graphics.Texture;

public class Item {
	private String name;
	private int value;
	private Texture texture;
	
	public Item(String name, int value, Texture texture) {
		this.name = name;
		this.value = value;
		this.texture = texture;
	}
	
	public String name() {
		return name;
	}
	
	public int value() {
		return value;
	}
	
	public Texture texture() {
		return texture;
	}

}
