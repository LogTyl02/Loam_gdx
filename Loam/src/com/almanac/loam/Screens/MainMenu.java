package com.almanac.loam.Screens;

import com.almanac.loam.Loam;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MainMenu implements Screen {
		//Loam game;
		private SpriteBatch spriteBatch;
		private BitmapFont font;
		//private ShapeRenderer renderer;
		//private BitmapFont multiPageFont;
		String sphinxText;
		String menuTitle;
		String PLAY;
		String QUIT;
		TextBounds bounds;
		Texture loamTitle;
		Sprite loamTitleSprite;
		
		private ParticleEffect effect;
		
	
	public MainMenu(Loam game) {
		//this.game = game;		
		}
	
	
	
	@Override
	public void render(float delta) {
		int viewHeight = Gdx.graphics.getHeight();
		int viewWidth  = Gdx.graphics.getWidth();
		
		float x = 100;
		float y = 20;
		float alignmentWidth = 150;
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		
		spriteBatch.begin();
		font.setColor(Color.RED);
		
		//font.draw(spriteBatch, text, 100f, 400f);
		font.draw(spriteBatch, sphinxText, (viewWidth / 2) - (454 / 2), viewHeight / 2 - 300);
		//font.setColor(Color.LIGHT_GRAY);
		//font.draw(spriteBatch, menuTitle, (viewWidth / 2) - (65 / 2), viewHeight - 300);
		
		loamTitleSprite.draw(spriteBatch);
		effect.draw(spriteBatch, delta);
		
		font.setColor(Color.WHITE);
		font.draw(spriteBatch, PLAY, (viewWidth / 2) - 70 - 70, (viewHeight - 450));
		font.draw(spriteBatch, QUIT, (viewWidth / 2) + 71, (viewHeight - 450));
		spriteBatch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		
		
		effect = new ParticleEffect();
		effect.load(Gdx.files.internal("data/snow.p"), Gdx.files.internal("data"));
		effect.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
		effect.start();
		
		sphinxText = "Sphinx of black quartz, judge my vows.";
		menuTitle = "Loam";
		PLAY = "PLAY";
		QUIT = "QUIT";
		
		spriteBatch = new SpriteBatch();
		
		font = new BitmapFont(Gdx.files.internal("data/gameFont.fnt"),
				Gdx.files.internal("data/gameFont_0.tga"), false);	
		float alignmentWidth;
		bounds = font.getBounds(sphinxText);
		alignmentWidth = 1000;
		System.out.println(font.getBounds(QUIT).width);
		loamTitle = new Texture("data/LoamTitle.png");
		loamTitle.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		loamTitleSprite = new Sprite(loamTitle);
		loamTitleSprite.setColor(0.4f, 0.3f, 0.2f, 1);
		loamTitleSprite.setPosition(1400 / 2 - 150, 900 / 2);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
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
		spriteBatch.dispose();
		font.dispose();
		
	}
	

}
