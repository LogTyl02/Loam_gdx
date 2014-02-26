package com.almanac.loam.Model;

import java.util.List;

public class PlayerAI extends CreatureAI {
	
	private FieldOfView fov;
	
	private List<String> messages;
	
	public PlayerAI(Creature creature, List<String> message, FieldOfView fov) {
		super(creature);
		this.messages = messages;
		this.fov = fov;
	}
	
	public void onNotify(String message) {
		messages.add(message);
	}
	
	public boolean canSee(int wx, int wy) {
		return fov.isVisible(wx, wy);
	}
	
	public void onEnter(int x, int y, Tile tile) {
		if (tile.isGround()) {
			creature.x = x;
			creature.y = y;
		}
	}

}
