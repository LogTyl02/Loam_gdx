package com.almanac.loam.Model;

import com.badlogic.gdx.math.Vector2;

public class Monster extends MoveableEntity {
	
	public Monster(float SPEED, float width, float height, float x, float y) {
		super(SPEED, width, height, x, y);
	}
	
	public void update() {
		bounds.x = x;
		bounds.y = y;
	}
	
	
	
}
