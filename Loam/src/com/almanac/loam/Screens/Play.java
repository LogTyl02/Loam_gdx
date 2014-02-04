package com.almanac.loam.Screens;

import com.almanac.loam.Loam;
import com.almanac.loam.View.World;
import com.almanac.loam.View.WorldRenderer;
import com.badlogic.gdx.Screen;

public class Play implements Screen {

	Loam game;
	World world;
	public WorldRenderer renderer;
	
	public Play(Loam game) {
		this.game = game;
		world = new World(game);
		renderer = new WorldRenderer(world);
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
		// TODO Auto-generated method stub
		
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
