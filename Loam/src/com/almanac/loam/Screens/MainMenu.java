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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class MainMenu implements Screen {
		Loam game;
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
		
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		//stage.act(delta);
		
		spriteBatch.begin();
		//stage.draw();
		font.setColor(Color.RED);
		button.draw(spriteBatch, 1f);;
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
		viewHeight = Gdx.graphics.getHeight();
		viewWidth  = Gdx.graphics.getWidth();
		
		effect.setPosition(viewWidth/2, viewHeight);
		
		if (stage == null) {
			stage = new Stage(width, height, true);
			
		}
		stage.clear();
		
		
		Gdx.input.setInputProcessor(stage);
		
		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.getDrawable("buttonnormal");
		style.down = skin.getDrawable("buttonpressed");
		style.font = font;
		
		button = new TextButton("PLAY", style);
		button.setWidth(200);
		button.setWidth(100);
		button.setX(viewWidth / 2 - button.getWidth() / 2);
		button.setY(viewWidth / 2 - button.getHeight() / 2);
		
		//stage.addActor(button);
		button.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("DOWN");
				return true;
			}
			
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("BOINK");
				
			}
		});
		
	}

	@Override
	public void show() {
		
		atlas = new TextureAtlas("data/button.pack");
		skin = new Skin();
		skin.addRegions(atlas);
		
		effect = new ParticleEffect();
		effect.load(Gdx.files.internal("data/snow.p"), Gdx.files.internal("data"));
		
		effect.start();
		
		sphinxText = "Sphinx of black quartz, judge my vows.";
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
		stage.dispose();
		atlas.dispose();
		loamTitle.dispose();
		effect.dispose();
		font.dispose();
		
	}
	

}
