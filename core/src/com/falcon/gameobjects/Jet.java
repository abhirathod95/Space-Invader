package com.falcon.gameobjects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.falcon.zbhelpers.AssetLoader;

public class Jet {
	private Vector2 position;
	private int width, height, deviceAngle;
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private Circle boundingCircle;


	public Jet(float x, float y, int width, int height) {
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
		boundingCircle = new Circle();
	}

	public void update(float delta) {
		if(position.x < 0)
			position.x = 0;
		else if(position.x > 111)
			position.x = 111;
		
		deviceAngle = Gdx.input.getRotation();
		
		if(deviceAngle < 0)
			moveLeft();
		else if (deviceAngle > 0)
			moveRight();
		
		
		boundingCircle.set(position.x+13, position.y+13, 13);
	}

	public void shoot() {
		Projectile p = new Projectile((position.x + 10), position.y - 5);
		projectiles.add(p);
		AssetLoader.shoot.play();
	}

	public void moveLeft() {
		position.x -= 10;
	}

	public void moveRight() {
		position.x += 10;
	}
	
	public void clearAllProjectiles() {
		projectiles.clear();
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
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

	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

}
