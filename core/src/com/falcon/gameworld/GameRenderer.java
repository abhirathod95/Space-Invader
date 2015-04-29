package com.falcon.gameworld;

import java.util.ArrayList;
import java.util.ListIterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.falcon.gameobjects.Background;
import com.falcon.gameobjects.Met;
import com.falcon.gameobjects.Jet;
import com.falcon.gameobjects.MotherShip;
import com.falcon.gameobjects.Projectile;
import com.falcon.gameobjects.ScrollHandler;
import com.falcon.zbhelpers.AssetLoader;
import com.falcon.starshipinvader.SIGame;

public class GameRenderer {

	private SIGame game;
	private GameWorld myWorld;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch batcher;

	private float sum;

	//game objects
	private Jet jet;
	private MotherShip ship;
	private ScrollHandler scroller;
	private Background background1, background2, background3;
	private ArrayList<Projectile> projectiles; 
	private ArrayList<Met> mets;

	//game assets
	private TextureRegion jetImage, ready, motherShip;
	private Texture background1Image, background2Image, background3Image;
	private Animation metAnimation, expAnimation; 
	private TextureAtlas buttons;


	public GameRenderer(GameWorld world, SIGame game) {
		this.myWorld = world;
		this.game = game;
		scroller = world.getScroller();
		batcher = game.getBatch() ;
		this.shapeRenderer = game.getRenderer();
		sum = 0f;
		initGameObjects();
		initAssets();
	}


	private void initGameObjects() {
		jet = myWorld.getJet();
		ship = myWorld.getShip();
		mets = scroller.mets;
		background1 = scroller.getBackground1();
		background2 = scroller.getBackground2();
		background3 = scroller.getBackground3();
		projectiles = jet.getProjectiles();
	}

	private void initAssets() {
		jetImage = AssetLoader.jet;
		motherShip = AssetLoader.motherShip;
		background1Image = AssetLoader.bg1;
		background2Image = AssetLoader.bg2;
		background3Image = AssetLoader.bg3;
		metAnimation = AssetLoader.metAnimation;
		expAnimation = AssetLoader.expAnimation;
		buttons = new TextureAtlas(Gdx.files.internal("data/skins/buttons.pack"), false);	
		ready = new TextureRegion(buttons.findRegion("ready"));
		ready.flip(false, true);

	}

	private void drawMets(float runTime) {
		for(ListIterator<Met> iter = mets.listIterator(); iter.hasNext();) {
			Met met = (Met) iter.next();
			//			batcher.draw(metAnimation.getKeyFrame(runTime), met.getX(), met.getY(), 
			//					met.getWidth(), met.getHeight());
			if(!met.dead) 
				batcher.draw(AssetLoader.missile, met.getX(), met.getY(), met.getWidth(), met.getHeight());
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
			batcher.draw(AssetLoader.laser, p.getX(), p.getY(), p.getWidth(), p.getHeight());
		}
	}

	private void drawJet() {
		if(jet.isAlive() || !scroller.isHit()) {
			if(jet.velocity.x > 1) {
				batcher.draw(AssetLoader.jetL1, jet.getX(), jet.getY(), jet.getWidth(), jet.getHeight());
			}
			else if(jet.velocity.x < -1) {
				batcher.draw(AssetLoader.jetR1, jet.getX(), jet.getY(), jet.getWidth(), jet.getHeight());
			}
			else
				batcher.draw(jetImage, jet.getX(), jet.getY(), jet.getWidth(), jet.getHeight());
		}
	}

	private void drawMotherShip() {
		batcher.draw(motherShip, -10, 184, 160, 80);
	}

	private void drawExplosions(float delta) {
		if(myWorld.isIntersec()){
			sum += delta;
			if(scroller.getScrolledY() != 0) {
				batcher.draw(expAnimation.getKeyFrame(sum),
						scroller.getScrolledX() - 20, scroller.getScrolledY(), 50, 50);
			}
			if(scroller.isHit())
				batcher.draw(expAnimation.getKeyFrame(sum),
						jet.getX() , jet.getY(), 30, 30);
			if(expAnimation.isAnimationFinished((float) (sum)) || myWorld.isReady()){               
				myWorld.setIntersec();
				sum = 0;
			}
		}
	}

	private void drawReady() {
		batcher.draw(ready, 36, myWorld.getMidBottomY() - 50, 68, 14);
	}

	private void drawScore() {
		// Convert integer into String
		String score = myWorld.getScore() + "";

		// Draw shadow first
		AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(), (136 / 2)
				- (3 * score.length()), 12);
		// Draw text
		AssetLoader.font.draw(batcher, "" + myWorld.getScore(), (136 / 2)
				- (3 * score.length() - 1), 11);

	}

	public void render(float delta, float runTime) {		
		batcher.setProjectionMatrix(game.getCam().projection);
		batcher.setTransformMatrix(game.getCam().view);
		shapeRenderer.setProjectionMatrix(game.getCam().projection);
		shapeRenderer.setTransformMatrix(game.getCam().view);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Begin SpriteBatch
		batcher.begin();
		batcher.setColor(Color.WHITE);
		drawBackgrounds();
		
		//batcher.end();
		//draw bounding circles and rects for everything
		//		shapeRenderer.begin(ShapeType.Filled);
		//		for(ListIterator<Met> iter = mets.listIterator(); iter.hasNext();) {
		//			Met met = (Met) iter.next();
		//			shapeRenderer.rect(met.getBoundingRectangle().x, met.getBoundingRectangle().y, met.getBoundingRectangle().width, met.getBoundingRectangle().height);
		//		}
		//		shapeRenderer.circle(ship.getBoundingCircle().x, ship.getBoundingCircle().y, ship.getBoundingCircle().radius);
		//		shapeRenderer.circle(jet.getBoundingCircle().x, jet.getBoundingCircle().y, jet.getBoundingCircle().radius);
		//		shapeRenderer.end();
		//batcher.begin();
		
		if (myWorld.isRunning()) {
			drawMets(runTime);
			drawJet();
			drawMotherShip();
			drawExplosions(delta);
			drawScore();
		} else if (myWorld.isReady()) {
			drawJet();
			drawMotherShip();
			drawReady();
		} 
		drawProjectiles();
		batcher.end();	


	}
}
