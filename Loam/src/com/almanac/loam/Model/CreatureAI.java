package com.almanac.loam.Model;

import java.util.Random;


public class CreatureAI {

	protected Creature creature;
	
	public CreatureAI(Creature creature) {
		this.creature = creature;
		this.creature.setCreatureAI(this);
	}
	
	public void onEnter(int x, int y, Tile tile) {
		if (tile.isGround()) {
			creature.x = x;
			creature.y = y;
		} else {
			System.out.println("COLLISION");
		}
	}
	
	public void wander() {
		int mx;
		int my;
		Random random = new Random();
		float r1 = random.nextFloat();
		float r2 = random.nextFloat();
		
		if (r1 > 5.0) {
			mx = 1;
		} else {
			mx = -1;
		}
		
		if (r2 > 5.0) {
			my = 1;
		} else {
			my = -1;
		}
		creature.moveBy(mx, my);
	}
	
	public void onUpdate() {
		
	}
	
	public boolean canSee(int wx, int wy) {
		
		if ((creature.x-wx)*(creature.x-wx) + (creature.y-wy)*(creature.y-wy) > creature.visionRadius()*creature.visionRadius())
            return false;
			
		// Cast rays from a creature to a world position
		for (Point p : new Line(creature.x, creature.y, wx, wy)){
            if (creature.tile(p.x, p.y).isGround() || p.x == wx && p.y == wy)
                continue;
         
            return false;
        }
				
		return true;
	}
	
}
