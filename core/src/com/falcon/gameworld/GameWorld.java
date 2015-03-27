package com.falcon.gameworld;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.falcon.gameobjects.Jet;
import com.falcon.gameobjects.Projectile;
import com.falcon.gameobjects.ScrollHandler;
import com.falcon.zbhelpers.AssetLoader;

public class GameWorld {

	private Jet jet;
	private ScrollHandler scroller;
	private ArrayList<Projectile> projectiles;
	private int score = 0;
	private Random r;
	private GameState currentState;
	private int midBottomY;

	public enum GameState {
		READY, RUNNING, GAMEOVER, HIGHSCORE
	}	

	public static final int NUMOFBOMBS = 5;

	public GameWorld(int midBottomY) {
		currentState = GameState.READY;
		this.midBottomY = midBottomY;
		jet = new Jet(this, 56, midBottomY + 15, 25, 25);
		scroller = new ScrollHandler(NUMOFBOMBS);
		projectiles = jet.getProjectiles();
		r = new Random();
	}

	public void update(float delta, float runTime, float accelX) {
		switch (currentState) {
		case READY:
			updateReady(delta);
			break;
		case RUNNING:
			updateRunning(delta, runTime, accelX);
			break;
		default:
			break;
		}

	}

	private void updateReady(float delta) {
		// Do nothing for now
	}

	public void updateRunning(float delta, float runTime, float accelX) {
		// Add a delta cap so that if our game takes too long to update, 
		//we will not break our collision detection.
		if (delta > .15f) {
			delta = .15f;
		}
		
		jet.velocity.x = -accelX * 60;
		
		jet.update(delta);
		scroller.update(delta);
		for(ListIterator<Projectile> iter = projectiles.listIterator(); iter.hasNext();) {
			Projectile p = (Projectile) iter.next();
			if (p.isVisible() == true) {
				p.update(delta);
				for(int i = 0; i < NUMOFBOMBS; i++) {
					if(p.collides(scroller.getBomb(i))) {
						addScore(1);
						p.visible = false;
						scroller.getBomb(i).reset(-1 * r.nextInt(10) * 20);
					}
				}
			} else {
				iter.remove();
			}
		}
		if (jet.isAlive() && scroller.collides(jet) || jet.isAlive() && scroller.scrolledOut()) {
			jet.clearAllProjectiles();
			scroller.stop();
			AssetLoader.explosion.play();
			jet.die();
			currentState = GameState.GAMEOVER;

			if (score > AssetLoader.getHighScore()) {
				AssetLoader.setHighScore(score);
				currentState = GameState.HIGHSCORE;
			}
		}
	}

	public Jet getJet() {
		return jet;
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

	public boolean isReady() {
		return currentState == GameState.READY;
	}

	public void start() {
		currentState = GameState.RUNNING;
	}

	public void restart() {
		score = 0;
		jet.onRestart(midBottomY + 15);
		scroller.onRestart();
		currentState = GameState.READY;
	}

	public boolean isGameOver() {
		return currentState == GameState.GAMEOVER;
	}

	public boolean isHighScore() {
		return currentState == GameState.HIGHSCORE;
	}

	public boolean isRunning() {
		return currentState == GameState.RUNNING;
	}
}
