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

	
	public InputHandler(World world, WorldRenderer worldRenderer) {
		this.world = world;
		this.worldRenderer = worldRenderer;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		
		switch(keycode) {
		case Keys.NUMPAD_8:
			world.player().moveBy(0, 1);
			break;
		case Keys.NUMPAD_2:
			world.player().moveBy(0, -1);
			break;
		case Keys.NUMPAD_4:
			world.player().moveBy(-1, 0);
			break;
		case Keys.NUMPAD_6:
			world.player().moveBy(1, 0);
			break;
		case Keys.NUMPAD_7:
			world.player().moveBy(-1, 1);
			break;
		case Keys.NUMPAD_9:
			world.player().moveBy(1, 1);
			break;
		case Keys.NUMPAD_1:
			world.player().moveBy(-1, -1);
			break;
		case Keys.NUMPAD_3:
			world.player().moveBy(1, -1);
			break;
		}
		
		world.darkYoung().wander();

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
				System.out.println("Movin up");
				break;
			case Keys.NUMPAD_2:
				System.out.println("Movin down");
				break;
			case Keys.NUMPAD_4:
				System.out.println("Movin right");
				break;
			case Keys.NUMPAD_6:
				System.out.println("Movin left");
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
