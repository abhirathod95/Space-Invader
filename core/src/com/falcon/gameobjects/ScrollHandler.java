package com.falcon.gameobjects;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;

public class ScrollHandler {

	// ScrollHandler will create all objects that we need.
	private Background background1, background2, background3;
	public ArrayList<Bomb> bombs;
	private Random r;
	private int numOfBombs;


	// ScrollHandler will use the constants below to determine
	// how fast we need to scroll 
	public static final int SCROLL_SPEED = 40;

	public ScrollHandler(int numOfBombs) {
		this.numOfBombs = numOfBombs;
		background1 = new Background(0, 0, 136, 204, SCROLL_SPEED);
		background2 = new Background(0, -136, 136, 204, SCROLL_SPEED);
		background3 = new Background(0, -272, 136, 204, SCROLL_SPEED);
		bombs = new ArrayList<Bomb>();
		for(int i = 0; i < this.numOfBombs; i++) {
			bombs.add(new Bomb(68, 68, 20, 20, SCROLL_SPEED));
		}
	}

	public boolean update(float delta) {

		// Update our objects
		background1.update(delta);
		background2.update(delta);
		background3.update(delta);

		for(ListIterator<Bomb> iter = bombs.listIterator(); iter.hasNext();) {
			iter.next().update(delta);
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

		// check if any bombs have been missed, if so end the game
		for(ListIterator<Bomb> iter = bombs.listIterator(); iter.hasNext();) {
			Bomb bomb = (Bomb) iter.next();
			if(bomb.isScrolledDown())
				return true;
		}
		
		return false;
		//checks if bombs are attacked
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

	// The getters for our five instance variables
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
}
