package com.falcon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.falcon.gameworld.GameWorld;
import com.falcon.starshipinvader.SIGame;
import com.falcon.zbhelpers.AssetLoader;

public class EndingScreen implements Screen{

	final SIGame game;
	private Stage stage = new Stage(new StretchViewport(131, 204));
	private Table table = new Table();
	private GameWorld world;
	private TextureAtlas strings;
	private SpriteBatch batch;
	private StretchViewport viewport;
	private Image gameOver, highScore, retry, scoreBoard;

	public EndingScreen(final SIGame gam, GameWorld world) {
		game = gam;
		this.world = world;
		this.batch = game.getBatch();
		viewport = game.getport();

		strings = new TextureAtlas(Gdx.files.internal("data/skins/buttons.pack"), false);	
		gameOver = new Image(new TextureRegionDrawable(strings.findRegion("over")));
		highScore = new Image(new TextureRegionDrawable(strings.findRegion("highscore")));
		retry = new Image(new TextureRegionDrawable(strings.findRegion("retry")));
		scoreBoard = new Image(new TextureRegionDrawable(strings.findRegion("scoreboard")));
	}

	@Override
	public void show() {
		retry.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new GameScreen(game));
			}
		});

		//The elements are displayed in the order you add them.
		//The first appear on top, the last at the bottom.

		if(world.getScore() > (Integer) SIGame.saveManager.loadDataValue("highscore", Integer.class)) {
			table.add(highScore).size(73,17).padBottom(10).row();
			SIGame.saveManager.saveDataValue("highscore", world.getScore());
		}
		else
			table.add(gameOver).size(73, 17).padBottom(10).row();
		table.add(scoreBoard).size(85,60).padBottom(10).row();
		table.add(retry).size(73,17).padBottom(10).row();
		table.setFillParent(true);
		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(AssetLoader.bg1, 0, 0, 160, 1570);
		batch.end();
		stage.act();
		stage.draw();
		batch.begin();
		drawScores();
		batch.end();
	}
	
	public void drawScores() {
		int length = ("" + world.getScore()).length();

		AssetLoader.whiteFont.draw(batch, "" + world.getScore(), 69 - length, 89);

		int highScore = SIGame.saveManager.loadDataValue("highscore", Integer.class);
		int length2 = ("" + highScore).length();
		AssetLoader.whiteFont.draw(batch, "" + highScore, 68 - length2, 114);
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
		viewport.update(width, height, true);


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
	public void hide() {
		dispose();

	}

	@Override
	public void dispose() {
		strings.dispose();
		stage.dispose();
	}

}
