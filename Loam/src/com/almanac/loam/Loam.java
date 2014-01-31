package com.almanac.loam;

import com.almanac.loam.Screens.MainMenu;
import com.almanac.loam.World.World;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.FPSLogger;

public class Loam extends Game {
	public static final String VERSION = "0.0.0.01 Pre-Alpha";
	public static final String LOG = "Loam";
	public static FPSLogger FPSLOGGER;
	public AssetManager assetManager = new AssetManager();
	
	public World w;
	
	@Override
	public void create() {		
		FPSLOGGER = new FPSLogger();
		
		//setScreen(new SplashScreen(this));
		setScreen(new MainMenu(this));
		w = new World();
		w.growMushrooms();
		
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {		
		super.render();
		FPSLOGGER.log();
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
