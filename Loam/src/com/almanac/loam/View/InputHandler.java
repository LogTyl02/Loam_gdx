package com.almanac.loam.View;

import com.almanac.loam.Loam;
import com.almanac.loam.Model.Creature;
import com.almanac.loam.Screens.Play;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;


public class InputHandler implements InputProcessor {

	World world;
	WorldRenderer worldRenderer;
	Creature player;
	Camera camera;
	int state;
	Loam game;

	
	public InputHandler(World world, Creature player, int state) {
		this.world = world;
		this.player = player;
		this.state = state;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		
		if (this.state == 0) {
			switch(keycode) {
			case Keys.P:
				game.setScreen(new Play(game));
			}
		}
		
if (this.state == 1) {
	
		
		switch(keycode) {
			case Keys.NUMPAD_8:
				player.moveBy(0, 1);;
				break;
			case Keys.NUMPAD_2:
				player.moveBy(0, 1);;
				break;
			case Keys.NUMPAD_4:
				player.moveBy(0, 1);;
				break;
			case Keys.NUMPAD_6:
				player.moveBy(0, 1);;
				break;
			case Keys.NUMPAD_7:
				player.moveBy(0, 1);;
				break;
			case Keys.NUMPAD_1:
				player.moveBy(0, 1);;
				break;
			case Keys.NUMPAD_9:
				player.moveBy(0, 1);;
				break;
			case Keys.NUMPAD_3:
				player.moveBy(0, 1);;
				break;
			case Keys.W:
				
				
			case Keys.ESCAPE:
				if (!world.game.isPaused) {
					world.game.pause();
				} else if (world.game.isPaused) {
					world.game.resume();
				}
			default:
				break;
		}
		
}
return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {

		switch(character) {
			case Keys.NUMPAD_8:
				player.moveBy(0, 1);;
				break;
			case Keys.NUMPAD_2:
				player.moveBy(0, 1);;
				break;
			case Keys.NUMPAD_4:
				player.moveBy(0, 1);;
				break;
			case Keys.NUMPAD_6:
				player.moveBy(0, 1);;
				break;
			default:
				break;
		}
		return false;
		
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
