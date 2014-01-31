package com.almanac.loam.Model;

import com.badlogic.gdx.math.Vector2;

public abstract class MoveableEntity extends Entity {

	protected float SPEED;
	
	
	public MoveableEntity(float SPEED, float width, float height, Vector2 position) {
		super(position, width, height);
		this.SPEED = SPEED;
	}
		
}
