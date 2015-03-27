package com.falcon.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Scrollable {

	// Protected is similar to private, but allows inheritance by subclasses.
	protected Vector2 position, velocity, accel;
	protected int width;
	protected int height;
	protected boolean isScrolledDown;

	public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
		position = new Vector2(x, y);
		velocity = new Vector2(0, scrollSpeed);
		accel = new Vector2(0,7);
		this.width = width;
		this.height = height;
		isScrolledDown = false;
	}

	public void update(float delta) {
		velocity.add(accel.cpy().scl(delta));
		position.add(velocity.cpy().scl(delta));
		
		// If the Scrollable object is no longer visible:
		if (position.y > 204) {
			isScrolledDown = true;
		}
	}

	// Reset: Should Override in subclass for more specific behavior.
	public void reset(float newY) {
		position.y = newY;
		isScrolledDown = false;
	}

	public boolean isScrolledDown() {
		return isScrolledDown;
	}
	
	public void stop() {
	    velocity.y = 0;
	}
	
	// Getters for instance variables
	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
}
