package com.almanac.loam.View;

import com.badlogic.gdx.graphics.Color;

public class Light {
	
	float x;
	float y;	
	Color color;
	
	public static Color randomColor() {
		float intensity = (float)Math.random() * 0.5f + 0.5f;
		return new Color((float)Math.random(), (float)Math.random(), (float)Math.random(), intensity);
		
	}
	
	public Light(float x, float y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
}
