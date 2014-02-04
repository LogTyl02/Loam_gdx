package com.almanac.loam.View;

import com.almanac.loam.Model.Player;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;


public class InputHandler implements InputProcessor {

	World world;
	WorldRenderer worldRenderer;
	Player player;
	Camera camera;

	
	public InputHandler(World world) {
		this.world = world;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		player = world.getPlayer();
		camera = worldRenderer.getCamera();
		
		switch(keycode) {
			case Keys.NUMPAD_8:
				player.setY(player.getY() + 16);
				break;
			case Keys.NUMPAD_2:
				player.setY(player.getY() - 16);
				break;
			case Keys.NUMPAD_4:
				player.setX(player.getX() - 16);
				break;
			case Keys.NUMPAD_6:
				player.setX(player.getX() + 16);
				break;
				

			default:
				break;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		player = world.getPlayer();
		switch(character) {
			case Keys.NUMPAD_8:
				player.setY(player.getY() + 16);
				break;
			case Keys.NUMPAD_2:
				player.setY(player.getY() - 16);
				break;
			case Keys.NUMPAD_4:
				player.setX(player.getX() - 16);
				break;
			case Keys.NUMPAD_6:
				player.setX(player.getX() + 16);
				break;
			default:
				break;
		}
		return true;
		
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
