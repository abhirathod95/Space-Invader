package com.falcon.gameworld;

import java.util.ArrayList;
import java.util.ListIterator;

import com.falcon.gameobjects.Bomb;
import com.falcon.gameobjects.Jet;
import com.falcon.gameobjects.Projectile;
import com.falcon.gameobjects.ScrollHandler;
import com.falcon.zbhelpers.AssetLoader;

public class GameWorld {

	private Jet jet;
	private ScrollHandler scroller;
	private boolean isAlive, missedMine;
	private ArrayList<Projectile> projectiles;
	private int runTime;


	public static final int NUMOFBOMBS = 5;

	public GameWorld(int midBottomY) {
		jet = new Jet(56, midBottomY + 15, 25, 25);
		scroller = new ScrollHandler(NUMOFBOMBS);
		projectiles = jet.getProjectiles();
		isAlive = true;
	}

	public void update(float delta, float runTime) {
		jet.update(delta);
		missedMine = scroller.update(delta);
		for(ListIterator<Projectile> iter = projectiles.listIterator(); iter.hasNext();) {
			Projectile p = (Projectile) iter.next();
			if (p.isVisible() == true) {
				p.update(delta);
				for(int i = 0; i < NUMOFBOMBS; i++) {
					if(p.collides(scroller.getBomb(i))) {
						p.visible = false;
						scroller.getBomb(i).reset(-20);
					}
				}
			} else {
				iter.remove();
			}
		}
		if (isAlive && scroller.collides(jet) || isAlive && missedMine) {
			jet.clearAllProjectiles();
			scroller.stop();
			AssetLoader.explosion.play();
			isAlive = false;
		}
	}

	public Jet getJet() {
		return jet;
	}

	public ScrollHandler getScroller() {
		return scroller;
	}
}
