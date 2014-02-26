package com.almanac.loam.Screens;

import java.util.ArrayList;
import java.util.List;

import com.almanac.loam.Loam;
import com.almanac.loam.Model.Creature;
import com.almanac.loam.Model.CreatureFactory;
import com.almanac.loam.Model.FieldOfView;
import com.almanac.loam.Model.ItemFactory;
import com.almanac.loam.View.InputHandler;
import com.almanac.loam.View.World;
import com.almanac.loam.View.WorldBuilder;
import com.almanac.loam.View.WorldRenderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class Play implements Screen {

	Loam game;
	World world;
	private List<String> messages;
	public WorldRenderer renderer;
	
	private Creature player;
	private Creature darkYoung;
	private FieldOfView fov;
	
	private int worldWidth;
	private int worldHeight;
	
	public Play(Loam game) {
		this.game = game;
		
		messages =	new ArrayList<String>();
		worldWidth			=	75;
		worldHeight			=	35;
		createWorld(game, worldWidth, worldHeight);
		
		fov = new FieldOfView(world);
		CreatureFactory creatureFactory = new CreatureFactory(world, fov);
		ItemFactory itemFactory = new ItemFactory(world);
		
		createCreatures(creatureFactory);
		createItems(itemFactory);
		
		renderer = new WorldRenderer(world, this.player, fov);
		Gdx.input.setInputProcessor(new InputHandler(world));
		
	}
	
	private void createWorld(Loam game, int worldWidth, int worldHeight) {
		world = new WorldBuilder(worldWidth, worldHeight).makeCaves().build(game);
	}
	
	private void createCreatures(CreatureFactory creatureFactory) {
		player = creatureFactory.newPlayer(messages);
		System.out.println("Player spawned at " + player.x + " " + player.y);
		darkYoung = creatureFactory.newDarkYoung();
		System.out.println("Dark Young spawned at " + darkYoung.x + " " + darkYoung.y);
	}
	
	private void createItems(ItemFactory itemFactory) {
		for (int i = 0; i < 50; i++) {
			itemFactory.newRedMushroom();
			System.out.println("New red mushroom");
		}
		
		for (int i = 0; i < 10; i++) {
			itemFactory.newBlueMushroom();
		}
		
		for (int i = 0; i < 2; i++) {
			itemFactory.newGoldMushroom();
		}
	}
	
	public int px() {
		return player.x;
	}
	
	public int py() {
		return player.y;
	}
	
	public Creature player() {
		return player;
	}
	
	@Override
	public void render(float delta) {
		world.update();
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		game.isPlaying = true;
		
	}

	@Override
	public void hide() {
		dispose();
		
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
		// TODO Auto-generated method stub
		
	}
}
