package com.falcon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.falcon.gameworld.GameRenderer;
import com.falcon.gameworld.GameWorld;
import com.falcon.zbhelpers.InputHandler;

public class GameScreen implements Screen {

	private GameWorld world;
	private GameRenderer renderer;
	private float runTime;

	public GameScreen() {
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float gameWidth = 136;
		float gameHeight = screenHeight / (screenWidth / gameWidth);
		runTime = 0;
		int midBottomY = (int) ((gameHeight / 4) * 3);

		world = new GameWorld(midBottomY);
		renderer = new GameRenderer(world, (int) gameHeight);

		Gdx.input.setInputProcessor(new InputHandler(world.getJet()));
	}

	@Override
	public void render(float delta) {
		// Covert Frame rate to String, print it
		Gdx.app.log("GameScreen FPS", (1/delta) + "");
		runTime++;
		world.update(delta, runTime);  //updates the world
		renderer.render(runTime);    //paints the updated world
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log("GameScreen", "resizing");
	}

	@Override
	public void show() {
		Gdx.app.log("GameScreen", "show called");
	}

	@Override
	public void hide() {
		Gdx.app.log("GameScreen", "hide called");     
	}

	@Override
	public void pause() {
		Gdx.app.log("GameScreen", "pause called");        
	}

	@Override
	public void resume() {
		Gdx.app.log("GameScreen", "resume called");       
	}

	@Override
	public void dispose() {
		// Leave blank
	}
}
