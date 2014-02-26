package com.almanac.loam.Model;


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
