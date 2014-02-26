package com.almanac.loam.Model;

import java.util.List;

import com.almanac.loam.View.World;
import com.badlogic.gdx.graphics.Texture;

public class CreatureFactory {
	private World world;
	private FieldOfView fov;
	
	public CreatureFactory(World world, FieldOfView fov) {
		this.world = world;
		this.fov = fov;
	}
	
	public Creature newPlayer(List<String> messages) {
		Texture playerTexture = new Texture("data/doctor.png");
		Creature player = new Creature(world, "Player", playerTexture);
		world.addAtEmptyLocation(player);
		new PlayerAI(player, messages, fov);
		return player;
	}
	
	public Creature newDarkYoung() {
		Texture darkYoungTexture = new Texture("data/golemsprite.png");
		Creature darkYoung = new Creature(world, "Dark Young", darkYoungTexture);
		world.addAtEmptyLocation(darkYoung);
		new DarkYoungAI(darkYoung, this);
		return darkYoung;
		
	}
	
}
