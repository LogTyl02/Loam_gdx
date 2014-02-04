package com.almanac.loam.View;

import com.almanac.loam.Loam;
import com.almanac.loam.Model.Monster;
import com.almanac.loam.Model.Player;
import com.almanac.loam.Utils.IsoHelper;
import com.badlogic.gdx.Gdx;

public class World {
	
	Loam game;
	Player player;
	Monster monster;
	
	float monsterStartX = 32;
	float monsterStartY = 32;
	
	float playerStartX = 256;
	float playerStartY = 256;
	
	public World(Loam game) {
		this.game = game;
		player = new Player(3f, 18f, 37f, playerStartX + 8, playerStartY + 8);
		monster = new Monster(3f, 80f, 63f, monsterStartX, monsterStartY);
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

