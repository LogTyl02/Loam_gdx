package com.almanac.loam.View;

import com.almanac.loam.Model.Creature;
import com.almanac.loam.Model.FieldOfView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
	
	Matrix4 matrix;
	float width, height;
	ShapeRenderer shapeDebugger;
	
	private Creature player;
	private FieldOfView fov;
	
	private OrthographicCamera camera;
	
	public WorldRenderer(World world, FieldOfView fov) {
		this.world = world;
		this.player = world.player();
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

	}
	
	public void render() {
		int left = getScrollX();
		int top = getScrollY();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
				
		//camera.position.set(player.x, player.y, 1);
		camera.position.set(world.width() / 2, world.height() / 2, 1);
		renderTiles(left, top);
		renderItems();
		renderCreatures();

	}

	private void renderTiles(int left, int top) {
		fov.update(player.x, player.y, player.visionRadius());
		
		spriteBatch.begin();
		
	
		
		for (int x = 0; x < world.width(); x++){
	        for (int y = 0; y < world.height(); y++){
	        		int wx = x;
	        		int wy = y;
	        		// spriteBatch.draw(world.tile(wx, wy).texture(), x * 32, y * 32);
	        	//if (!player.canSee(wx, wy))
	        		spriteBatch.draw(world.tile(wx, wy).texture(), x * 32, y * 32);
	        }
	    }
		
		spriteBatch.end();
	}
	
	private void renderItems() {
		spriteBatch.begin();
		
		for (int x = 0; x < world.width(); x++) {
			for (int y = 0; y < world.height(); y++) {
				if (!(world.item(x, y) == null)) {
        			spriteBatch.draw(world.item(x, y).texture(), x * 32, y * 32);
        		
        		}
			}
		}
		
		spriteBatch.end();
	}
	

	private void renderCreatures() {
		spriteBatch.begin();
		
		for (Creature creature : world.getCreatures()) {
			spriteBatch.draw(creature.texture(), creature.x * 32, creature.y * 32);
		}
		
		spriteBatch.end();
	}
	

	public Camera getCamera() {
		return camera;
	}
	
	public void setCamera(int x, int y, int z) {
		camera.position.set(x, y, z);
	}
	
	public int getScrollX() {
		return Math.max(0,  Math.min(player.x * 32 - screenWidth / 2, world.width()- screenWidth));
	}
	
	public int getScrollY() {
		return Math.max(0, Math.min(player.y * 32 - screenHeight / 2, world.height() - screenHeight));
	}

	public void update() {
		// player.update
		// everything else updates too
	}
	
	public void dispose() {
		spriteBatch.dispose();
	}
	
}
