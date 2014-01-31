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
		player = new Player(3f, 32f, 32f, new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight()));
		
	}
	
	public Player getPlayer() {
		return player;
	}
	

}

