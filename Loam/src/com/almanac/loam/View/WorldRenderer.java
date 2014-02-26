package com.almanac.loam.View;

import java.util.List;

import com.almanac.loam.Model.Creature;
import com.almanac.loam.Model.CreatureFactory;
import com.almanac.loam.Model.FieldOfView;
import com.almanac.loam.Model.ItemFactory;
import com.almanac.loam.Model.Monster;
import com.almanac.loam.Model.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;

public class WorldRenderer {
	
	private int screenWidth = 800;
	private int screenHeight = 600;

	World world;
	SpriteBatch spriteBatch;
	Texture grassTexture;
	private BitmapFont font;
	
	public float cameraWidth;
	public float cameraHeight;
	
	private List<String> messages;
	
	Matrix4 matrix;
	float width, height;
	ShapeRenderer shapeDebugger;
	
	private Creature player;
	private FieldOfView fov;
	
	private OrthographicCamera camera;
	
	public WorldRenderer(World world, Creature player, FieldOfView fov) {
		this.world = world;
		this.player = player;
		this.fov = fov;
		
		font = new BitmapFont(Gdx.files.internal("data/gameFont.fnt"),
				Gdx.files.internal("data/gameFont_0.tga"), false);
		
		cameraWidth = (Gdx.graphics.getWidth() / 0.5f);
		cameraHeight = (Gdx.graphics.getHeight() / 0.5f);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, cameraWidth, cameraHeight);
		
		camera.update();
		
		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(camera.combined);
		
		shapeDebugger = new ShapeRenderer();
		shapeDebugger.setColor(Color.CYAN);
		
		matrix = new Matrix4();
		
		/*
		 * 	Camera Stuff!
		 * 	Creating a new camera for rendering. Ortho for now but
		 * 		might make it isometric.
		 */
		//camera = new OrthographicCamera();
		

		//matrix.setToRotation(new Vector3(1, 0, 0), 90);
		
	}
	
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.position.set(player.x, player.y, 1);
		
		renderTiles();

	}

	private void renderTiles() {
		System.out.println(this.player.visionRadius());
		fov.update(this.player.x, this.player.y, this.player.visionRadius());
		
		spriteBatch.begin();
		
		for (int x = 0; x < screenWidth ; x++){
	        for (int y = 0; y < screenHeight; y++){
	        		int wx = x + 10;
	        		int wy = y + 10;
	            
	        	if (player.canSee(wx, wy))
	        		spriteBatch.draw(world.tile(x, y).texture(), x * 32, y * 32);
	        }
	    }
		
		spriteBatch.end();
	}
	

	public Camera getCamera() {
		return camera;
	}
	
	public void setCamera(int x, int y, int z) {
		camera.position.set(x, y, z);
	}

	public void update() {
		// player.update
		// everything else updates too
	}
	
	public void dispose() {
		spriteBatch.dispose();
	}
	
}
