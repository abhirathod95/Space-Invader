package com.falcon.gameobjects;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;


public class ScrollHandler {

	// ScrollHandler will create all objects that we need.
	private Background background1, background2, background3;
	public ArrayList<Bomb> bombs;
	private int numOfBombs;
	private boolean scrolledOut;
	private Random r;


	// ScrollHandler will use the constants below to determine
	// how fast we need to scroll 
	private final static int SCROLL_SPEED = 70;

	public ScrollHandler(int numOfBombs) {
		this.numOfBombs = numOfBombs;
		background1 = new Background(0, 0, 136, 204, SCROLL_SPEED);
		background2 = new Background(0, -136, 136, 204, SCROLL_SPEED);
		background3 = new Background(0, -272, 136, 204, SCROLL_SPEED);
		bombs = new ArrayList<Bomb>();
		r = new Random();
		for(int i = 0; i < this.numOfBombs; i++) {
			bombs.add(new Bomb(r.nextInt(6) * 20, -1 * r.nextInt(10) * 20, 20, 20, SCROLL_SPEED));
		}
		scrolledOut = false;
	}

	public void update(float delta) {

		// Update our objects
		background1.update(delta);
		background2.update(delta);
		background3.update(delta);

		// update bombs and check if any bombs have been missed, if so end the game
		for(ListIterator<Bomb> iter = bombs.listIterator(); iter.hasNext();) {
			Bomb bomb = iter.next();
			if(bomb.isScrolledDown())
				scrolledOut = true;
			bomb.update(delta);
		}

		// Check if any of the backgrounds are scrolled down,
		// and reset accordingly
		if (background1.isScrolledDown()) {
			background1.reset(-272);
		} else if (background2.isScrolledDown()) {
			background2.reset(-272);

		} else if (background3.isScrolledDown()) {
			background3.reset(-272);
		}
	}

	//stop the scrolling if collision occurs
	public void stop() {
		background1.stop();
		background2.stop();
		background3.stop();
		for(ListIterator<Bomb> iter = bombs.listIterator(); iter.hasNext();) {
			iter.next().stop();
		}
	}

	// Return true if ANY bombs hits the jet.
	public boolean collides(Jet jet) {
		for(ListIterator<Bomb> iter = bombs.listIterator(); iter.hasNext();) {
			if(iter.next().collides(jet)) {
				return true;
			}
		}
		return false;	
	}

	public void onRestart() {
		background1.onRestart(0, SCROLL_SPEED);
		background2.onRestart(-136, SCROLL_SPEED);
		background3.onRestart(-272, SCROLL_SPEED);
		for(ListIterator<Bomb> iter = bombs.listIterator(); iter.hasNext();) {
			Bomb bomb = iter.next();
			bomb.onRestart(-1 * r.nextInt(10) * 20, SCROLL_SPEED);
		}
		scrolledOut = false;
	}

	// The getters for the variables
	public Background getBackground1() {
		return background1;
	}

	public Background getBackground2() {
		return background2;
	}

	public Background getBackground3() {
		return background3;
	}

	public Bomb getBomb(int index) {
		return bombs.get(index);
	}

	public boolean scrolledOut() {
		return scrolledOut;
	}
	
}
