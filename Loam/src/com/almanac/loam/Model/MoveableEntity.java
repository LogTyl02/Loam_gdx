package com.almanac.loam.Model;

import com.badlogic.gdx.math.Vector2;

public abstract class MoveableEntity extends Entity {

	protected float SPEED;
	
	
	public MoveableEntity(float SPEED, float width, float height, float x, float y) {
		super(x, y, width, height);
		this.SPEED = SPEED;
	}
	
	// Everything needs an update method that can be called when the world updates.
	public abstract void update();
		
}
