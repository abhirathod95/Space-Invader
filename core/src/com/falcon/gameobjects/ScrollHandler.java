package com.falcon.gameobjects;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

import com.falcon.gameobjects.Met;


public class ScrollHandler {

	// ScrollHandler will create all objects that we need.
	private Background background1, background2, background3;
	public ArrayList<Met> mets;
	private int numOfBombs;
	private boolean scrolledOut, isHit;
	private float scrolledX, scrolledY;
	private Random r;


	// ScrollHandler will use the constants below to determine
	// how fast we need to scroll 
	private final static int SCROLL_SPEED = 70;

	public ScrollHandler(int numOfBombs) {
		this.numOfBombs = numOfBombs;
		background1 = new Background(0, 0, 160, 1570, SCROLL_SPEED);
		background2 = new Background(0, -1570, 160, 1570, SCROLL_SPEED);
		background3 = new Background(0, -3140, 160, 1570, SCROLL_SPEED);
		mets = new ArrayList<Met>();
		r = new Random();
		for(int i = 0; i < this.numOfBombs; i++) {
			mets.add(new Met(r.nextInt(5) * 25, -1 * r.nextInt(5) * 50, 15, 40, SCROLL_SPEED));
		}
		scrolledOut = false;
	}
	
	public void updateReady(float delta) {
		background1.backgroundReady();
		background2.backgroundReady();
		background3.backgroundReady();
		
		background1.update(delta);
		background2.update(delta);
		background3.update(delta);
		
		if (background1.isScrolledDown()) {
			background1.reset(-3200);
		} else if (background2.isScrolledDown()) {
			background2.reset(-3200);

		} else if (background3.isScrolledDown()) {
			background3.reset(-3200);
		}
	}

	public void update(float delta) {
		background1.backgroundGo();
		background2.backgroundGo();
		background3.backgroundGo();
		
		
		// Update our objects
		background1.update(delta);
		background2.update(delta);
		background3.update(delta);

		// update mets and check if any mets have been missed, if so end the game
		for(ListIterator<Met> iter = mets.listIterator(); iter.hasNext();) {
			Met met = iter.next();
			met.update(delta);
		}

		// Check if any of the backgrounds are scrolled down,
		// and reset accordingly
		if (background1.isScrolledDown()) {
			background1.reset(-3140);
		} else if (background2.isScrolledDown()) {
			background2.reset(-3140);

		} else if (background3.isScrolledDown()) {
			background3.reset(-3140);
		}
	}

	//stop the scrolling if collision occurs
	public void stop() {
		background1.stop();
		background2.stop();
		background3.stop();
		for(ListIterator<Met> iter = mets.listIterator(); iter.hasNext();) {
			iter.next().stop();
		}
	}

	// Return true if ANY mets hits the jet.
	public boolean collides(Jet jet, MotherShip ship) {
		for(ListIterator<Met> iter = mets.listIterator(); iter.hasNext();) {
			Met met = iter.next();
			if(met.collides(jet)) {
				met.dead = true;
				isHit = true;
				return true;
			}
			if(met.collidesShip(ship)) {
				met.dead = true;
				scrolledOut = true;
				scrolledX = met.getX();
				scrolledY = met.getY();
				return true;
			}
		}
		return false;	
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
	
	public boolean isHit() {
		return isHit;
	}
	

	public Met getMet(int index) {
		return mets.get(index);
	}

	public boolean scrolledOut() {
		return scrolledOut;
	}
	
	public float getScrolledX() {
		return scrolledX;
	}
	
	public float getScrolledY() {
		return scrolledY;
	}	
}
