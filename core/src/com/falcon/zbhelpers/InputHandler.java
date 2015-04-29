package com.falcon.zbhelpers;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.falcon.gameobjects.Jet;
import com.falcon.gameworld.GameWorld;

public class InputHandler implements InputProcessor {

	private Jet myJet;
	private GameWorld myWorld;

	public InputHandler(GameWorld myWorld) {
		this.myWorld = myWorld;
		this.myJet = myWorld.getJet();


	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.SPACE) {

			if (myWorld.isReady()) {
				myWorld.start();
			}

		}
		if(myWorld.isRunning()) {
			if(keycode == Input.Keys.A)
				myJet.moveLeft();
			else if(keycode == Input.Keys.D)
				myJet.moveRight();
		}
		return false;
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

		if(pointer == 0) {
			if (myWorld.isReady()) {
				myWorld.start();
				myJet.shoot();
			} else if (myWorld.isRunning()) {
				myJet.shoot();
			}
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false; // Return true to say we handled the touch.
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
