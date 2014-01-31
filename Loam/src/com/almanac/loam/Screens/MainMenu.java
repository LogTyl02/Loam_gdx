package com.almanac.loam.Screens;

import com.almanac.loam.Loam;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenu implements Screen {
		//Loam game;
		private SpriteBatch spriteBatch;
		private BitmapFont font;
		//private ShapeRenderer renderer;
		//private BitmapFont multiPageFont;
		String text;
	
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
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		spriteBatch.begin();
		font.setColor(Color.RED);
		//font.draw(spriteBatch, text, 100f, 400f);
		font.drawWrapped(spriteBatch, text, viewWidth / 2 - (alignmentWidth / 2), viewHeight / 2, alignmentWidth, HAlignment.CENTER);
		spriteBatch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		float alignmentWidth;
		spriteBatch = new SpriteBatch();
		
		text = "Sphinx of pink quartz, judge my plow.";
		
		font = new BitmapFont(Gdx.files.internal("data/fonts/gameFont.fnt"),
				Gdx.files.internal("data/fonts/gameFont_0.tga"), false);
		
		TextBounds bounds = font.getMultiLineBounds(text);
		alignmentWidth = bounds.width;
		
		
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
