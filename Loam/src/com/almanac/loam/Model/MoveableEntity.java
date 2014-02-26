package com.almanac.loam.Model;

import com.almanac.loam.Model.Tile;
import com.badlogic.gdx.math.Vector2;

public abstract class MoveableEntity extends Entity {

	protected float SPEED;
	
	
	public MoveableEntity(float SPEED, float width, float height, int x, int y) {
		super(x, y, width, height);
		this.SPEED = SPEED;
	}
	
	// Everything needs an update method that can be called when the world updates.
	public abstract void update();
	
	public void onEnter(int f, int g, Tile tile) {
		if (tile.isGround()) {
			this.x = f;
			this.y = g;
		}
		
	}
}