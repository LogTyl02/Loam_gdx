package com.almanac.loam.View;

import com.almanac.loam.Model.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;

public class WorldRenderer {

	World world;
	SpriteBatch spriteBatch;
	Texture playerTexture;
	Matrix4 matrix;
	float width, height;
	ShapeRenderer shapeDebugger;
	
	Player player;
	OrthographicCamera camera;
	
	public WorldRenderer(World world) {
		this.world = world;
		
		width = (Gdx.graphics.getWidth() / 10);
		height = (Gdx.graphics.getHeight() / 10);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		
		camera.update();
		
		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(camera.combined);
		playerTexture = new Texture("data/1up_mushroom.png"); // placeholder sprite
		playerTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		shapeDebugger = new ShapeRenderer();
		shapeDebugger.setColor(Color.CYAN);
		
		matrix = new Matrix4();
		
		/*
		 * 	Camera Stuff!
		 * 	Creating a new camera for rendering. Ortho for now but
		 * 		might make it isometric.
		 */
		//camera = new OrthographicCamera();
		
		
		//camera.position.set(5, 5, 10);
		//camera.direction.set(-1, -1, -1);
		//camera.near = 1;
		//camera.far = 100;
		//matrix.setToRotation(new Vector3(1, 0, 0), 90);
		
		
	}
	
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		
		
		player = world.getPlayer();
		
		
		camera.position.set(player.getX(), player.getY(), 0);  // Make the camera follow the player
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);
		
		shapeDebugger.setProjectionMatrix(camera.combined);
		shapeDebugger.begin(ShapeType.Line);
		shapeDebugger.rect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
		shapeDebugger.rect(20, 20, player.getWidth(), player.getHeight());
		shapeDebugger.end();
		
		spriteBatch.begin();
		
			spriteBatch.draw(playerTexture, player.getX(), player.getY(), player.getWidth() / 2, player.getHeight() / 2, player.getWidth(), player.getHeight(), 1, 1, 0, 0, 0, playerTexture.getWidth(), playerTexture.getHeight(), false, false);
		spriteBatch.end();
		
		
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
