package com.almanac.loam.World;

import java.util.ArrayList;

public class World {
	
	/*
	 * 		COLORS
	 */
	
		public static String BLUE = "BLUE";
		public static String RED  = "RED";
		public static String GOLD = "GOLD";
		
	private ArrayList<Mushroom> mushrooms = new ArrayList<Mushroom>();
	
	public World() {
		
	}
	
	public void growMushrooms() {
//		this.mushrooms.add(new Mushroom());
		
		int mooshies = 30;
		
		if (this.mushrooms.size() == 0) {
			System.out.println("You have zero mushrooms, yo.");
		}
		
		for (int i = 0; i < mooshies; i ++) {
			Mushroom spawn = new Mushroom();
			this.mushrooms.add(spawn);
			System.out.println("Adding mushroom!");
			
		}
	}
	
	public void printMushrooms() {
		for (Mushroom m : this.mushrooms) {
			System.out.println(m.color);
		}
	}
}
