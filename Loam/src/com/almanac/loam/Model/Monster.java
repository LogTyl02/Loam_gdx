package com.almanac.loam.Model;

import com.badlogic.gdx.math.Vector2;

public class Monster extends MoveableEntity {
	
	public Monster(float SPEED, float width, float height, int x, int y) {
		super(SPEED, width, height, x, y);
	}
	
	public void update() {
		bounds.x = x;
		bounds.y = y;
	}
	
	
	
}
