package com.falcon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.falcon.gameworld.GameRenderer;
import com.falcon.gameworld.GameWorld;
import com.falcon.starshipinvader.SIGame;
import com.falcon.zbhelpers.InputHandler;

public class GameScreen implements Screen {

	private final SIGame game;
	private GameWorld world;
	private GameRenderer renderer;
	private float runTime;
	private StretchViewport viewport;

	public GameScreen(SIGame game) {
		this.game = game;
		int midBottomY = 153;
		viewport = game.getport();
		world = new GameWorld(midBottomY, game);
		Gdx.input.setInputProcessor(new InputHandler(world));
		renderer = new GameRenderer(world, game);
	}

	@Override
	public void render(float delta) {
		runTime++;
		world.update(delta, Gdx.input.getAccelerometerX());  //updates the world
		renderer.render(delta, runTime);    //paints the updated world
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
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
		
	}
}
