package com.almanac.loam.Model;

public class DarkYoungAI extends CreatureAI {
	private CreatureFactory factory;
	
	public DarkYoungAI(Creature creature, CreatureFactory factory) {
		super(creature);
		this.factory = factory;
	}
}
