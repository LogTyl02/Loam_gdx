package com.almanac.loam.View;

import com.almanac.loam.Model.Monster;
import com.almanac.loam.Model.Player;
import com.almanac.loam.Utils.IsoHelper;
import com.almanac.loam.Utils.OrthoCamController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class WorldRenderer {

	World world;
	SpriteBatch spriteBatch;
	Texture playerTexture;
	Texture monsterTexture;
	Texture grassTexture;
	
	public float cameraWidth;
	public float cameraHeight;
	 
	final Matrix4 matrix = new Matrix4();
	float width, height;
	ShapeRenderer shapeDebugger;
	
	Player player;
	Monster monster;
	
	OrthographicCamera camera;
	OrthoCamController cameraController;
	Texture textureTileset;
	TextureRegion[] tileSet;
	int[][] map;
	
	public WorldRenderer(World world) {
		this.world = world;
		
		
		
		cameraWidth = (Gdx.graphics.getWidth());
		cameraHeight = (Gdx.graphics.getHeight());
		camera = new OrthographicCamera(cameraWidth / 3, cameraHeight /3);
		camera.setToOrtho(false, cameraWidth, cameraHeight);

		
		//Gdx.input.setInputProcessor(cameraController);
		player = world.getPlayer();
		camera.position.set(500, 500, 1);
		camera.update();
		
		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.setTransformMatrix(matrix);
		playerTexture = new Texture("data/chronosprite.png"); // placeholder sprite
		playerTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		monsterTexture = new Texture("data/golemsprite.png");
		monsterTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		grassTexture = new Texture("data/grasspink.png");
		grassTexture.setFilter(TextureFilter.Nearest,  TextureFilter.Nearest);
		
		shapeDebugger = new ShapeRenderer();
		shapeDebugger.setColor(Color.CYAN);
		
		}
	
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		
		
		player = world.getPlayer();
		monster = world.getMonster();
		
		
		camera.position.set(player.getX(), player.getY(), 1);  // Make the camera follow the player
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);
		
		
		
		spriteBatch.begin();
		
			for (float i = 0; i <= Gdx.graphics.getWidth() * 3; i += 32) {
				for (float j = 0; j <= Gdx.graphics.getHeight() * 3; j += 32) {
					spriteBatch.draw(grassTexture, i, j);
					
				}
			}
		
			
			spriteBatch.draw(monsterTexture, monster.getX(), monster.getY(), monster.getWidth() / 2, monster.getHeight() / 2, monster.getWidth(), monster.getHeight(), 1, 1, 0, 0, 0, monsterTexture.getWidth(), monsterTexture.getHeight(), false, false);
			spriteBatch.draw(playerTexture, player.getX(), player.getY(), player.getWidth() / 2, player.getHeight() / 2, player.getWidth(), player.getHeight(), 1, 1, 0, 0, 0, playerTexture.getWidth(), playerTexture.getHeight(), false, false);
		spriteBatch.end();
		
		shapeDebugger.setProjectionMatrix(camera.combined);
		shapeDebugger.begin(ShapeType.Line);
		
		shapeDebugger.rect(player.getX(), player.getY(), player.getWidth(), player.getHeight());		// Bounding box for player
		shapeDebugger.rect(monster.getX(), monster.getY(), monster.getWidth(), monster.getHeight());	// Bounding box for monster
		shapeDebugger.end();
		
	}

	private void renderMap() {
		for (int x = 0; x < 10; x++) {
			for (int y = 10 - 1; y >= 0; y--) {
				float x_pos = (x * 64 / 2.0f) + (y * 64 / 2.0f);
				float y_pos = - (x * 32 / 2.0f) + (y * 32 /2.0f);
				
				spriteBatch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
				spriteBatch.draw(tileSet[map[x][y]], x_pos, y_pos, 64, 32);
			}
		}
	}
	
	public Camera getCamera() {
		return camera;
	}
	

	public void update() {
		// player.update
		// everything else updates too
	}
	
	public void dispose() {
		spriteBatch.dispose();
		playerTexture.dispose();
	}
}
