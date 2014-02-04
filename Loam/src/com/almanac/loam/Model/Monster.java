package com.almanac.loam.Model;

import com.badlogic.gdx.math.Vector2;

public abstract class Monster extends MoveableEntity {
	
	public Monster(float SPEED, float width, float height, float x, float y) {
		super(SPEED, width, height, x, y);
	}
	
	public abstract void advance(float delta, Player player);
	
}
