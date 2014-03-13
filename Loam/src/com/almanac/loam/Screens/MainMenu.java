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
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;


public class MainMenu implements Screen {
		Loam game;
		private SpriteBatch spriteBatch;
		private BitmapFont font;
		String sphinxText;
		String menuTitle;
		String PLAY;
		String QUIT;
		TextBounds bounds;
		Texture loamTitle;
		Sprite loamTitleSprite;
		
		Stage stage;
		TextureAtlas atlas;
		Skin skin;
		TextButton button;
		
		int viewHeight;
		int viewWidth;
		
		private ParticleEffect effect;
		
	
	public MainMenu(Loam game) {
		//this.game = game;		
		}
	
	@Override
	public void render(float delta) {
		int viewHeight = Gdx.graphics.getHeight();
		int viewWidth  = Gdx.graphics.getWidth();
		
		loamTitleSprite.setPosition((viewWidth / 2) - loamTitleSprite.getWidth() / 2, viewHeight / 2);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		
		spriteBatch.begin();

		font.setColor(Color.RED);
		font.draw(spriteBatch, sphinxText, (viewWidth / 2) - 120, viewHeight / 2 - 100);
		loamTitleSprite.draw(spriteBatch);
		effect.draw(spriteBatch, delta);
		
		font.setColor(Color.WHITE);

		
		spriteBatch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		viewHeight = Gdx.graphics.getHeight();
		viewWidth  = Gdx.graphics.getWidth();
		
		effect.setPosition(viewWidth/2, viewHeight);

	}

	@Override
	public void show() {
		
		effect = new ParticleEffect();
		effect.load(Gdx.files.internal("data/snow.p"), Gdx.files.internal("data"));
		
		effect.start();
		
		sphinxText = "Press a Key to Begin";
		menuTitle = "Loam";
		PLAY = "PLAY";
		QUIT = "QUIT";
		
		spriteBatch = new SpriteBatch();
		
		font = new BitmapFont(Gdx.files.internal("data/gameFont.fnt"),
				Gdx.files.internal("data/gameFont_0.tga"), false);	
		//float alignmentWidth;
		bounds = font.getBounds(sphinxText);
		//alignmentWidth = 1000;
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
		loamTitle.dispose();
		effect.dispose();
		font.dispose();
		
	}
	

}
