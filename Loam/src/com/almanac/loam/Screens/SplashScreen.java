package com.almanac.loam.Screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.almanac.loam.Loam;
import com.almanac.loam.TweenAccessors.SpriteTween;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen implements Screen {
	
	Texture splashTexture;
	Sprite splashSprite;
	SpriteBatch spriteBatch;
	Loam game;
	TweenManager manager;
	Music spooky;
	
	public SplashScreen(Loam game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);			// Set the color used for clearing
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);	// Do the actual clearing
		manager.update(delta);
		
		//Gdx.app.log(Loam.fpsLogger, "Tween Complete");
		
		spriteBatch.begin();								// All batch rendering must be between begin() and end()
			splashSprite.draw(spriteBatch);
		spriteBatch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		
		
	}

	@Override
	public void show() {
		spooky = Gdx.audio.newMusic(Gdx.files.internal("data/spook.ogg"));
		splashTexture = new Texture("data/almanac_007.png");
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		splashSprite = new Sprite(splashTexture);
		splashSprite.setColor(1, 1, 1, 0);
			
		spriteBatch = new SpriteBatch();
		
		Tween.registerAccessor(Sprite.class, new SpriteTween());
		
		manager = new TweenManager();
		
		TweenCallback cb = new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				tweenCompleted();
			}
		};
		
		spooky.play();
		Tween.to(splashSprite, SpriteTween.ALPHA, 5f).target(1).ease(TweenEquations.easeInQuad).repeatYoyo(1, 5f).setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE).start(manager);
	}

	private void tweenCompleted() {
		Gdx.app.log(Loam.LOG, "Tween Complete");
		game.setScreen(new MainMenu(game));
	}
	
	@Override
	public void hide() {
		
		
	}

	@Override
	public void pause() {
		
		
	}

	@Override
	public void resume() {
		
		
	}

	@Override
	public void dispose() {
		spooky.dispose();
		splashTexture.dispose();
		spriteBatch.dispose();
		
		
	}

}
