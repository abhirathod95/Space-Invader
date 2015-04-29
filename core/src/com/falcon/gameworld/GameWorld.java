package com.falcon.gameworld;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

import com.falcon.gameobjects.Jet;
import com.falcon.gameobjects.MotherShip;
import com.falcon.gameobjects.Projectile;
import com.falcon.gameobjects.ScrollHandler;
import com.falcon.screens.EndingScreen;
import com.falcon.starshipinvader.SIGame;
import com.falcon.zbhelpers.AssetLoader;

public class GameWorld {
	private SIGame game;

	private Jet jet;
	private ScrollHandler scroller;
	private ArrayList<Projectile> projectiles;
	private int score = 0;
	private float runTime = 0;
	private Random r;
	private GameState currentState;
	private MotherShip ship;
	private int midBottomY;
	private boolean intersec;

	public enum GameState {
		READY, RUNNING
	}	

	public static final int NUMOFBOMBS = 5;

	public GameWorld(int midBottomY, SIGame game) {
		this.game = game;
		currentState = GameState.READY;
		this.midBottomY = midBottomY;
		jet = new Jet(this, 56, midBottomY, 35, 30);
		ship = (new MotherShip(0, 184, 160, 80));
		scroller = new ScrollHandler(NUMOFBOMBS);
		projectiles = jet.getProjectiles();
		r = new Random();
		intersec = false;
	}

	public void update(float delta, float accelX) {
		runTime += delta;
		switch (currentState) {
		case READY:
			updateReady(delta);
			break;

		case RUNNING:
			updateRunning(delta, accelX);
			break;
		default:
			break;
		}
	}

	private void updateReady(float delta) {
		jet.updateReady(runTime);
		scroller.updateReady(delta);
	}

	public void updateRunning(float delta, float accelX) {
		// Add a delta cap so that if our game takes too long to update, 
		//we will not break our collision detection.
		if (delta > .15f) {
			delta = .15f;
		}

		if(jet.isAlive()) {
			if(accelX < -1 || accelX > 1)
				jet.velocity.x = -accelX * 60;
			else 
				jet.velocity.x = 0;
		}

		jet.update(delta);
		scroller.update(delta);
		for(ListIterator<Projectile> iter = projectiles.listIterator(); iter.hasNext();) {
			Projectile p = (Projectile) iter.next();
			if (p.isVisible() == true) {
				p.update(delta);
				for(int i = 0; i < NUMOFBOMBS; i++) {
					if(p.collides(scroller.getMet(i))) {
						addScore(1);
						p.visible = false;
						scroller.getMet(i).reset(-1 * r.nextInt(5) * 50);
					}
				}
			} else {
				iter.remove();
			}
		}
		if (jet.isAlive() && scroller.collides(jet, ship)) {
			intersec = true;
			jet.clearAllProjectiles();
			scroller.stop();
			AssetLoader.explosion.play();
			jet.die();
		}
		if(!intersec && !jet.isAlive()) {
			game.setScreen(new EndingScreen(game, this));
		}
	}

	public Jet getJet() {
		return jet;
	}

	public int getMidBottomY() {
		return midBottomY;
	}

	public ScrollHandler getScroller() {
		return scroller;
	}

	public int getScore() {
		return score;
	}

	public void addScore(int increment) {
		score += increment;
	}

	public boolean isIntersec() {
		return intersec;
	}

	public void setIntersec() {
		intersec = false;
	}

	public void ready() {
		currentState = GameState.READY;
	}

	public void start() {
		currentState = GameState.RUNNING;
	}


	public boolean isReady() {
		return currentState == GameState.READY;
	}

	public boolean isRunning() {
		return currentState == GameState.RUNNING;
	}

	public MotherShip getShip() {
		return ship;
	}

}
