package com.almanac.loam.Model;

import com.badlogic.gdx.math.Vector2;

public abstract class Monster extends MoveableEntity {
	
	public Monster(float SPEED, float width, float height, Vector2 position) {
		super(SPEED, width, height, position);
	}
	
	public abstract void advance(float delta, Player player);
	
}
