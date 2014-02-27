package com.almanac.loam.Model;

import com.almanac.loam.View.World;
import com.badlogic.gdx.graphics.Texture;

public class Creature {
	private World world;
	
	public int x;
	public int y;
	
	private String name;
	private Texture texture;
	
	private CreatureAI AI;
	private int visionRadius;
	
	public Creature(World world, String name, Texture texture) {
		this.world = world;
		this.name = name;
		this.texture = texture;
		
		this.visionRadius = 9;
	}
	
	public String name() {
		return name;
	}
	
	public Texture texture() {
		return texture;
	}
	
	public int visionRadius() {
		return visionRadius;
	}
	
	public Tile tile(int wx, int wy) {
		return world.tile(wx, wy);
	}
	
	public boolean canSee(int wx, int wy) {
		return AI.canSee(wx, wy);
	}

	
	public void moveBy(int mx, int my) {
		Tile t = world.tile(x + mx, y + my);
		
		if (t.isGround()) {
			x = x + (mx);
			y = y + (my);
		}
	}
	
	public void update() {
		AI.onUpdate();
	}
	
	public void setCreatureAI(CreatureAI AI) {
		this.AI = AI;
	}
	
	public void wander() {
		this.AI.wander();
	}
	
	public World world() {
		return world;
	}

}
