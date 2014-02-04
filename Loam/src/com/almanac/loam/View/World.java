package com.almanac.loam.View;

import java.util.ArrayList;

import com.almanac.loam.Loam;
import com.almanac.loam.Model.Mushroom;
import com.almanac.loam.Model.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class World {
	
	Loam game;
	Player player;
	
	public World(Loam game) {
		this.game = game;
		player = new Player(3f, 8f, 8f, 5, 5);
		Gdx.input.setInputProcessor(new InputHandler(this));
		
	}
	
	
	public Player getPlayer() {
		return player;
	}
	
	public void update() {
		player.update(); // World renderer will use this.
	}
	
	public void dispose() {
		
	}

}

