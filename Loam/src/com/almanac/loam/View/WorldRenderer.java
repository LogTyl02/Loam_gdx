package com.almanac.loam.View;

import com.almanac.loam.Model.Creature;
import com.almanac.loam.Model.FieldOfView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

public class WorldRenderer {
	
	private int screenWidth = 800;
	private int screenHeight = 600;
	private int lightSize = 256;
	private TextureRegion occluders;
	private FrameBuffer occludersFBO;
	private FrameBuffer shadowMapFBO;
	private Texture shadowMapTex;

	World world;
	SpriteBatch spriteBatch;
	Texture grassTexture;
	private BitmapFont font;
	
	public float cameraWidth;
	public float cameraHeight;
	
	float width, height;
	
	private Creature player;
	private FieldOfView fov;
	
	private OrthographicCamera camera;
	
	public WorldRenderer(World world, FieldOfView fov) {
		this.world = world;
		this.player = world.player();
		this.fov = fov;
		
		// Create a frame buffer object with no depth
		occludersFBO = new FrameBuffer(Format.RGBA8888, lightSize, lightSize, false);
		// Get the color buffer texture of FBO for region
		occluders = new TextureRegion(occludersFBO.getColorBufferTexture());
		// Flip on the y-axis cuz of OpenGL coords
		occluders.flip(false, true);
		
		// 1D shadow map, lightSize * 1 pixels, no depth
		shadowMapFBO = new FrameBuffer(Format.RGBA8888, lightSize, 1, false);
		shadowMapTex = shadowMapFBO.getColorBufferTexture();
		
		// Use linear filtering and repeat mode when sampling
		shadowMapTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		shadowMapTex.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		
		font = new BitmapFont(Gdx.files.internal("data/gameFont.fnt"),
				Gdx.files.internal("data/gameFont_0.tga"), false);
		
		cameraWidth = (Gdx.graphics.getWidth() / 0.5f);
		cameraHeight = (Gdx.graphics.getHeight() / 0.5f);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, cameraWidth, cameraHeight);
		camera.position.set(player.x * 32, player.y * 32, 1);
		
		camera.update();
		
		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(camera.combined);
	}
	
	public void render() {
		int camX = player.x;
		int camY = player.y;
		
		int left = getScrollX();
		int top = getScrollY();
		
		// Bind the occluder frame buffer
		occludersFBO.begin();
		
		// Clear the frame buffer
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.setToOrtho(false, occludersFBO.getWidth(), occludersFBO.getHeight());
		
		// Update the camera, and keep it centered on the player for now
		camera.position.set(player.x * 32, player.y * 32, 1);
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.setShader(null);
		
		
		
		renderTiles(left, top);
		renderItems();
		renderCreatures();
		
		occludersFBO.end();
		snagItems();

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
	
	private void snagItems() {
		int px = player.x;
		int py = player.y;
		
		if (world.item(px, py) != null) {
			world.updateScore(world.item(px, py).value());
			System.out.println("SCORE: " + world.score());
			world.remove(px, py);
		}
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
	
	private void renderLight(Light o) {
		occludersFBO.begin();
		
		Gdx.gl.glClearColor(0f,0f,0f,0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}
	
}
