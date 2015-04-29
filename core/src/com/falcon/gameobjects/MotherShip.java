package com.falcon.gameobjects;

import com.badlogic.gdx.math.Circle;



public class MotherShip {
	
	private float x, y, width, height;
	private Circle boundingCircle;
	
	public MotherShip(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		boundingCircle = new Circle();
		boundingCircle.set(68, 285, 100);
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	
	public float getHeight() {
		return height;
	}
	
	public Circle getBoundingCircle() {
		return boundingCircle;
	}
}
