package com.falcon.gameworld;

import java.util.ArrayList;
import java.util.ListIterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.falcon.gameobjects.Background;
import com.falcon.gameobjects.Bomb;
import com.falcon.gameobjects.Jet;
import com.falcon.gameobjects.Projectile;
import com.falcon.gameobjects.ScrollHandler;
import com.falcon.zbhelpers.AssetLoader;

public class GameRenderer {

	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch batcher;
	private int gameHeight;

	private Jet jet;
	private ScrollHandler scroller;
	private Background background1, background2, background3;
	private TextureRegion jetImage;
	private Texture background1Image, background2Image, background3Image;
	private Animation bombAnimation; 
	private ArrayList<Projectile> projectiles; 
	private ArrayList<Bomb> bombs;

	public GameRenderer(GameWorld world, int gameHeight) {
		this.myWorld = world;
		this.gameHeight = gameHeight;
		scroller = world.getScroller();
		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, gameHeight);

		batcher = new SpriteBatch();
		// Attach batcher to camera
		batcher.setProjectionMatrix(cam.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);

		initGameObjects();
		initAssets();
	}

	private void initGameObjects() {
		jet = myWorld.getJet();
		bombs = scroller.bombs;
		background1 = scroller.getBackground1();
		background2 = scroller.getBackground2();
		background3 = scroller.getBackground3();
		projectiles = jet.getProjectiles();
	}

	private void initAssets() {
		jetImage = AssetLoader.jet;
		background1Image = AssetLoader.bg1;
		background2Image = AssetLoader.bg2;
		background3Image = AssetLoader.bg3;
		bombAnimation = AssetLoader.bombAnimation;
	}

	private void drawBombs(float runTime) {
		for(ListIterator<Bomb> iter = bombs.listIterator(); iter.hasNext();) {
			Bomb bomb = (Bomb) iter.next();
			batcher.draw(bombAnimation.getKeyFrame(runTime), bomb.getX(), bomb.getY(), 
					bomb.getWidth(), bomb.getHeight());
		}
	}

	private void drawBackgrounds() {
		batcher.draw(background1Image, background1.getX(), background1.getY(), background1.getWidth(), background1.getHeight());
		batcher.draw(background2Image, background2.getX(), background2.getY(), background2.getWidth(), background2.getHeight());
		batcher.draw(background3Image, background3.getX(), background3.getY(), background3.getWidth(), background3.getHeight());

	}

	private void drawProjectiles() {
		for(ListIterator<Projectile> iter = projectiles.listIterator(); iter.hasNext();) {
			Projectile p = (Projectile) iter.next();
			shapeRenderer.setColor(255 / 255.0f, 255 / 255.0f, 0 / 255.0f, 1);
			shapeRenderer.rect(p.getX(), p.getY(), p.getWidth(), p.getHeight());

			//			Draws  the bounding rectangles for the projectiles
			//			shapeRenderer.setColor(Color.GREEN);
			//			shapeRenderer.rect(p.getBoundingRectangle().x, p.getBoundingRectangle().y, p.getBoundingRectangle().width, p.getBoundingRectangle().height);
		}
	}

	public void render(float runTime) {		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Begin ShapeRenderer
		shapeRenderer.begin(ShapeType.Filled);

		// Draw Background color and projectiles
		shapeRenderer.setColor(0 / 255.0f, 0 / 255.0f, 0 / 255.0f, 1);
		shapeRenderer.rect(0, 0, 136, gameHeight);
		drawProjectiles();
		shapeRenderer.end();

		// Begin SpriteBatch
		batcher.begin();
		drawBackgrounds();
		drawBombs(runTime);
		batcher.draw(jetImage, jet.getX(), jet.getY(), jet.getWidth(), jet.getHeight());


		if (myWorld.isReady()) {
			// Draw shadow first
			AssetLoader.shadow.draw(batcher, "Tap to shoot", (136 / 2)
					- (60), 70);
			// Draw text
			AssetLoader.font.draw(batcher, "Tap to shoot", (136 / 2)
					- (60 - 1), 69);
			// Draw shadow first
			AssetLoader.shadow.draw(batcher, "Don't let the", (136 / 2)
					- (60), 99);
			// Draw text
			AssetLoader.font.draw(batcher, "Don't let the", (136 / 2)
					- (60 - 1), 100);
			// Draw shadow first
			AssetLoader.shadow.draw(batcher, "bombs pass!", (136 / 2)
					- (55), 120);
			// Draw text
			AssetLoader.font.draw(batcher, "bombs pass!", (136 / 2)
			        - (55 - 1), 121);
		} else {

			if (myWorld.isGameOver() || myWorld.isHighScore()) {
				if (myWorld.isGameOver()) {
					AssetLoader.shadow.draw(batcher, "Game Over", 25, 56);
					AssetLoader.font.draw(batcher, "Game Over", 24, 55);

					AssetLoader.shadow.draw(batcher, "High Score:", 23, 106);
					AssetLoader.font.draw(batcher, "High Score:", 22, 105);

					String highScore = AssetLoader.getHighScore() + "";

					// Draw shadow first
					AssetLoader.shadow.draw(batcher, highScore, (136 / 2)
							- (3 * highScore.length()), 128);
					// Draw text
					AssetLoader.font.draw(batcher, highScore, (136 / 2)
							- (3 * highScore.length() - 1), 127);
				} 
				else {
					AssetLoader.shadow.draw(batcher, "High Score!", 19, 56);
					AssetLoader.font.draw(batcher, "High Score!", 18, 55);
				}

				AssetLoader.shadow.draw(batcher, "Try again?", 23, 86);
				AssetLoader.font.draw(batcher, "Try again?", 24, 85);

				// Convert integer into String
				String score = myWorld.getScore() + "";

				// Draw shadow first
				AssetLoader.shadow.draw(batcher, score,
						(136 / 2) - (3 * score.length()), 12);
				// Draw text
				AssetLoader.font.draw(batcher, score,
						(136 / 2) - (3 * score.length() - 1), 11);

			}

			// Convert integer into String
			String score = myWorld.getScore() + "";

			// Draw shadow first
			AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(), (136 / 2)
					- (3 * score.length()), 12);
			// Draw text
			AssetLoader.font.draw(batcher, "" + myWorld.getScore(), (136 / 2)
					- (3 * score.length() - 1), 11);
		}
		batcher.end();
		shapeRenderer.begin(ShapeType.Filled);
		drawProjectiles();
		shapeRenderer.end();
	}
}
