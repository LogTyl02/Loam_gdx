package com.almanac.loam.View;

import com.almanac.loam.Model.Player;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;


public class InputHandler implements InputProcessor {

	World world;
	Player player;
	
	public InputHandler(World world) {
		this.world = world;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		player = world.getPlayer();
		switch(keycode) {
			case Keys.NUMPAD_8:
				player.setY(player.getY() + 5);
				break;
			case Keys.NUMPAD_2:
				player.setY(player.getY() - 5);
				break;
			case Keys.NUMPAD_4:
				player.setX(player.getX() - 5);
				break;
			case Keys.NUMPAD_6:
				player.setX(player.getX() + 5);
				break;
			default:
				break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
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
