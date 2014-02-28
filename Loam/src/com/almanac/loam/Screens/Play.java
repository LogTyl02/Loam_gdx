package com.almanac.loam.Screens;

import com.almanac.loam.Loam;
import com.almanac.loam.Model.FieldOfView;
import com.almanac.loam.Model.Tile;
import com.almanac.loam.View.InputHandler;
import com.almanac.loam.View.World;
import com.almanac.loam.View.WorldBuilder;
import com.almanac.loam.View.WorldRenderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class Play implements Screen {

	Loam game;
	World world;

	public WorldRenderer renderer;
		
	private int worldWidth;
	private int worldHeight;
	
	FieldOfView fov;
	
	public Play(Loam game) {
		this.game = game;

		worldWidth			=	200;
		worldHeight			=	100;
		int octave			= 	4;
		float thresholdLow	=	0.3f;
		float thresholdHigh	=	0.45f;
		
		createWorld(game, worldWidth, worldHeight);
		//createPerlinWorld(game, worldWidth, worldHeight, octave, thresholdLow, thresholdHigh);
		
		this.fov = world.fov();

		
		renderer = new WorldRenderer(world, fov);
		//Gdx.input.setInputProcessor(new InputHandler(world, renderer));
	}
	
	private void createWorld(Loam game, int worldWidth, int worldHeight) {
		// Use the cellular automata method to build a basic map
		world = new WorldBuilder(worldWidth, worldHeight).makeCaves().build(game);
		// Generate some paths with a perlin noise map
		World perlinMap = new WorldBuilder(worldWidth, worldHeight).perlinMap(4, 0.4f, 0.5f).build(game);
		// Intersect to carve paths into map

		for (int x = 0; x < world.getTiles().length; x++) {
			for (int y = 0; y < world.getTiles()[x].length; y++) {
				if (world.tile(x, y) == Tile.ROCK && perlinMap.tile(x, y) == Tile.GRASS) {
					world.setTile(x, y, Tile.GRASS);
				}
			}
		}
	}
	
	private void createPerlinWorld(Loam game, int worldWidth, int worldHeight, int octave, float thresholdLow, float thresholdHigh) {
		world = new WorldBuilder(worldWidth, worldHeight).perlinMap(octave, thresholdLow, thresholdHigh).build(game);
	}
	
	
	@Override
	public void render(float delta) {
		world.update();
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		game.isPlaying = true;
		
	}

	@Override
	public void hide() {
		dispose();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
