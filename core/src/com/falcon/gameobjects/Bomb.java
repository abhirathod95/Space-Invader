package com.falcon.gameobjects;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Bomb extends Scrollable {

	private Random r;
	private Rectangle boundingRectangle;

	public Bomb(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
		r = new Random();
		boundingRectangle = new Rectangle();
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		boundingRectangle.set(position.x, position.y, 20, 20);
	}

	public void onRestart(float y, float scrollSpeed) {
		velocity.y = scrollSpeed;
		reset(y);
	}

	@Override
	public void reset(float newY) {
		// Call the reset method in the superclass (Scrollable)
		super.reset(newY);
		// Change the height to a random number
		position.x = r.nextInt(6) * 20;
	}

	public boolean collides(Jet jet) {
		return (Intersector.overlaps(jet.getBoundingCircle(), boundingRectangle));
	}

	public boolean collidesProjectile(Projectile laser) {
		return (Intersector.overlaps(laser.getBoundingRectangle(), boundingRectangle));
	}

	public Rectangle getBoundingRectangle() {
		return boundingRectangle;
	}
}