package com.almanac.loam.View;

import com.almanac.loam.Loam;
import com.almanac.loam.Model.Monster;
import com.almanac.loam.Model.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;

public class World {
	
	Loam game;
	Player player;
	Monster monster;
	
	
	public World(Loam game) {
		this.game = game;
		player = new Player(3f, 18f, 37f, 5, 5);
		monster = new Monster(3f, 80f, 63f, 5, 5);
		Gdx.input.setInputProcessor(new InputHandler(this));
		
	}
	
	
	public Player getPlayer() {
		return player;
	}
	
	

	
	public Monster getMonster() {
		return monster;
	}
	
	public void update() {
		player.update(); // World renderer will use this.
	}
	
	public void dispose() {
		
	}

}

