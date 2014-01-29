package com.almanac.loam;

import com.almanac.loam.Screens.SplashScreen;
import com.badlogic.gdx.Game;

public class Loam extends Game {
	public static final String VERSION = "0.0.0.01 Pre-Alpha";
	public static final String LOG = "Loam";
	
	
	@Override
	public void create() {		
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
