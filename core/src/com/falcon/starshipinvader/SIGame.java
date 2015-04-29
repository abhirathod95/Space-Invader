package com.falcon.starshipinvader;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.falcon.screens.SplashScreen;
import com.falcon.zbhelpers.AssetLoader;
import com.falcon.zbhelpers.SaveManager;

public class SIGame extends Game {
	
	public static SaveManager saveManager; 
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch batch;
	private StretchViewport viewport;

	@Override
	public void create() {
		saveManager = new SaveManager(true);
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		cam = new OrthographicCamera();
		cam.setToOrtho(true);
		viewport = new StretchViewport(136, 204, cam);
		AssetLoader.load(); 
		setScreen(new SplashScreen(this));
		if( SIGame.saveManager.loadDataValue("highscore", Integer.class) == null)
			SIGame.saveManager.saveDataValue("highscore", 0);

	}

	@Override
	public void resize (int width, int height) {
		viewport.update(width, height, true);
	}

	public SpriteBatch getBatch() {
		return this.batch;
	}

	public ShapeRenderer getRenderer() {
		return this.shapeRenderer;
	}

	public OrthographicCamera getCam() {
		return cam;
	}

	public StretchViewport getport() {
		return viewport;
	}

	@Override
	public void dispose() {
		batch.dispose();
		shapeRenderer.dispose();
		AssetLoader.dispose();
		super.dispose();
	}
}
