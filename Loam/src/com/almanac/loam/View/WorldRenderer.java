package com.almanac.loam.View;


import com.almanac.loam.Model.Creature;
import com.almanac.loam.Model.FieldOfView;
import com.almanac.loam.Model.Tile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class WorldRenderer {
	
	public enum ShaderSelection {
		Default,
		Ambient,
		Light,
		Final
	};
	
	private int screenWidth = 800;
	private int screenHeight = 600;

	World world;
	int width = screenWidth;
	int height = screenHeight;
	
	private Creature player;
	
	// For drawing
	SpriteBatch spriteBatch;
	private BitmapFont bitmapFont;
	private OrthographicCamera camera;
	private OrthographicCamera camera2d;
	public float cameraWidth;
	public float cameraHeight;
	private boolean lightMove = false;
	private boolean lightOscillate = false;
	private Texture light;
	private FrameBuffer FBO;
	
	// Different shaders
	private ShaderSelection shaderSelection = ShaderSelection.Default;
	private ShaderProgram currentShader;
	private ShaderProgram defaultShader;
	private ShaderProgram ambientShader;
	private ShaderProgram lightShader;
	private ShaderProgram finalShader;
	
	// Pass to the shader
	public static final float ambientIntensity = 0.1f;
	public static final Vector3 ambientColor = new Vector3(0.3f, 0.3f, 0.7f);
	
	// To make the light flicker
	public float zAngle;
	public static final float zSpeed = 15.0f;
	public static final float PI2 = 3.1415926535897932384626433832795f * 2.0f;
	
	//read our shader files
	final String vertexShader = Gdx.files.internal("data/vertexShader.glsl").readString();
	final String defaultPixelShader = Gdx.files.internal("data/defaultPixelShader.glsl").readString();
	final String ambientPixelShader = Gdx.files.internal("data/ambientPixelShader.glsl").readString();
	final String lightPixelShader =  Gdx.files.internal("data/lightPixelShader.glsl").readString();
	final String finalPixelShader =  Gdx.files.internal("data/pixelShader.glsl").readString();
	
	// Change the shader selection
	public void setShader(ShaderSelection shader) {
		shaderSelection = shader;
		
		
		// Change to case later
		if(shader == ShaderSelection.Final){
			currentShader = finalShader;
		}
		else if(shader == ShaderSelection.Ambient){
			currentShader = ambientShader;
		}
		else if(shader == ShaderSelection.Light){
			currentShader = lightShader;
		}
		else{
			shader = ShaderSelection.Default;
			currentShader = defaultShader;
		}
	}

	public WorldRenderer(World world, FieldOfView fov) {
		this.world = world;
		this.player = world.player();
		
		light = new Texture(Gdx.files.internal("data/light.png"));
	
		bitmapFont = new BitmapFont(Gdx.files.internal("data/gameFont.fnt"),
				Gdx.files.internal("data/gameFont_0.tga"), false);
		
		ShaderProgram.pedantic = false;
		defaultShader = new ShaderProgram(vertexShader, defaultPixelShader);
		ambientShader = new ShaderProgram(vertexShader, ambientPixelShader);
		lightShader = new ShaderProgram(vertexShader, lightPixelShader);
		finalShader = new ShaderProgram(vertexShader, finalPixelShader);
		setShader(shaderSelection);
		
		camera = new OrthographicCamera(width, height);
		camera.position.set(player.x * 32, player.y * 32, 1);
		camera.update();
		
		camera2d = new OrthographicCamera(width, height);
		camera2d.position.set(player.x * 32, player.y * 32, 1);
		camera2d.update();


		FBO = new FrameBuffer(Format.RGBA8888, width, height, false);
		 
		lightShader.begin();
		lightShader.setUniformf("resolution", width, height);
		lightShader.end();

		finalShader.begin();
		finalShader.setUniformf("resolution", width, height);
		finalShader.end();
		
		ambientShader.begin();
		ambientShader.setUniformf("ambientColor", ambientColor.x, ambientColor.y,
				ambientColor.z, ambientIntensity);
		ambientShader.end();
		

		lightShader.begin();
		lightShader.setUniformi("u_lightmap", 1);
		lightShader.end();
		
		finalShader.begin();
		finalShader.setUniformi("u_lightmap", 1);
		finalShader.setUniformf("ambientColor", ambientColor.x, ambientColor.y,
				ambientColor.z, ambientIntensity);
		finalShader.end();
		
		spriteBatch = new SpriteBatch();
		light = new Texture("data/light.png");
		bitmapFont = new BitmapFont();
		bitmapFont.setUseIntegerPositions(false);
		bitmapFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		bitmapFont.setColor(Color.WHITE);
		bitmapFont.setScale(1.0f/32.0f);
		
		// Input processing
		Gdx.input.setInputProcessor(new InputAdapter() {
			
			public boolean scrolled(int amount){
				camera.zoom += (float)amount * 0.08f;
				camera.update();
				return false;
			}
			public boolean keyUp(int keycode) {
				if(keycode == Keys.LEFT){
					camera.translate(-1.0f, 0.0f);
					camera.update();
				}
				else if(keycode == Keys.RIGHT){
					camera.translate(1.0f, 0.0f);
					camera.update();
				}
				else if(keycode == Keys.UP){
					camera.translate(0.0f, 1.0f);
					camera.update();
				}
				else if(keycode == Keys.DOWN){
					camera.translate(0.0f, -1.0f);
					camera.update();
				}
				else if(keycode == Keys.NUM_1){
					setShader(ShaderSelection.Default);
				}
				else if(keycode == Keys.NUM_2){
					setShader(ShaderSelection.Ambient);
				}
				else if(keycode == Keys.NUM_3){
					setShader(ShaderSelection.Light);
				}
				else if(keycode == Keys.NUM_4){
					setShader(ShaderSelection.Final);
				}
				else if(keycode == Keys.SPACE){
					lightOscillate = !lightOscillate;
				}
				
				return false;
			}
			public boolean touchUp(int x, int y, int pointer, int button) {
				lightMove = !lightMove;
				return false;
			}
		});
	}
	
	public void render() {
		final float dt = Gdx.graphics.getRawDeltaTime();

		zAngle += dt * zSpeed;
		while(zAngle > PI2)
			zAngle -= PI2;
		//draw the light to the FBO
		FBO.begin();
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.setShader(defaultShader);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		spriteBatch.begin();
		float lightSize = lightOscillate? (4.75f + 0.25f * (float)Math.sin(zAngle) + .2f*MathUtils.random()):5.0f;
		
		spriteBatch.draw(light, (player.x * 32) - light.getWidth() / 4, (player.y * 32) - light.getHeight() / 2);
		
		spriteBatch.end();
		FBO.end();
		
		//draw the actual scene
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.setShader(currentShader);
		spriteBatch.begin();
		FBO.getColorBufferTexture().bind(1); //this is important! bind the FBO to the 2nd texture unit
		light.bind(0);
		renderGrass();
		renderRocks();
		renderCreatures();
		renderItems();
		spriteBatch.end();
		
		//debug information
		spriteBatch.setProjectionMatrix(camera2d.combined);
		spriteBatch.setShader(defaultShader);
		spriteBatch.begin();
		float x = 0.0f;
		bitmapFont.setColor(shaderSelection==ShaderSelection.Default?Color.YELLOW:Color.WHITE);
		x += bitmapFont.draw(spriteBatch, "1=Default Shader", x, camera2d.viewportHeight).width;
		bitmapFont.setColor(shaderSelection==ShaderSelection.Ambient?Color.YELLOW:Color.WHITE);
		x += bitmapFont.draw(spriteBatch, " 2=Ambiant Light", x, camera2d.viewportHeight).width;
		bitmapFont.setColor(shaderSelection==ShaderSelection.Light?Color.YELLOW:Color.WHITE);
		x += bitmapFont.draw(spriteBatch, " 3=Light Shader", x, camera2d.viewportHeight).width;
		bitmapFont.setColor(shaderSelection==ShaderSelection.Final?Color.YELLOW:Color.WHITE);
		x += bitmapFont.draw(spriteBatch, " 4=Final Shader", x, camera2d.viewportHeight).width;
		x = 0.0f;
		bitmapFont.setColor(lightMove?Color.YELLOW:Color.WHITE);
		x += bitmapFont.draw(spriteBatch, "click=light control (" +lightMove+ ")", x, camera2d.viewportHeight-bitmapFont.getLineHeight()).width;
		bitmapFont.setColor(lightOscillate?Color.YELLOW:Color.WHITE);
		x += bitmapFont.draw(spriteBatch, " space=light flicker (" +lightOscillate+ ")", x, camera2d.viewportHeight-bitmapFont.getLineHeight()).width;
		x = 0.0f;
		bitmapFont.setColor(Color.WHITE);
		x += bitmapFont.draw(spriteBatch, Gdx.graphics.getFramesPerSecond() + " fps", x, camera2d.viewportHeight-bitmapFont.getLineHeight()*2.0f).width;
		spriteBatch.end();
	}

	private void renderGrass() {
		for (int x = 0; x < world.width(); x++){
	        for (int y = 0; y < world.height(); y++){
	        		int wx = x;
	        		int wy = y;
	        		// spriteBatch.draw(world.tile(wx, wy).texture(), x * 32, y * 32);
	        	//if (!player.canSee(wx, wy))
	        		
	        		if (world.tile(wx, wy) == Tile.GRASS) {
	        		spriteBatch.draw(world.tile(wx, wy).texture(), x * 32, y * 32);
	        		}
	        }
	    }
	}
	
	private void renderRocks() {
		for (int x = 0; x < world.width(); x++){
	        for (int y = 0; y < world.height(); y++){
	        		int wx = x;
	        		int wy = y;
	     		
	        		if (world.tile(wx, wy) == Tile.ROCK) {
	        		spriteBatch.draw(world.tile(wx, wy).texture(), x * 32, y * 32);
	        		}
	        }
	    }
	}
	
	private void renderItems() {	
		for (int x = 0; x < world.width(); x++) {
			for (int y = 0; y < world.height(); y++) {
				if (!(world.item(x, y) == null)) {
        			spriteBatch.draw(world.item(x, y).texture(), x * 32, y * 32); 		
        		}
			}
		}
	}
	

	private void renderCreatures() {
		for (Creature creature : world.getCreatures()) {
			spriteBatch.draw(creature.texture(), creature.x * 32, creature.y * 32);
		}
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
		finalShader.dispose();
		lightShader.dispose();
		ambientShader.dispose();
		defaultShader.dispose();
		light.dispose();
		FBO.dispose();
		bitmapFont.dispose();
	}
	
	
	public void resize(final int width, final int height) {
		camera = new OrthographicCamera(20.0f, 20.0f * height / width);
		camera.position.set(camera.viewportWidth / 2.0f, camera.viewportHeight / 2.0f, 0.0f);
		camera.update();
		
		camera2d = new OrthographicCamera(20.0f, 20.0f * height / width);
		camera2d.position.set(camera.viewportWidth / 2.0f, camera.viewportHeight / 2.0f, 0.0f);
		camera2d.update();


		FBO = new FrameBuffer(Format.RGBA8888, width, height, false);
		 
		lightShader.begin();
		lightShader.setUniformf("resolution", width, height);
		lightShader.end();

		finalShader.begin();
		finalShader.setUniformf("resolution", width, height);
		finalShader.end();
	}
	

	
}
