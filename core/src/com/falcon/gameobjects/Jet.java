package com.falcon.gameobjects;

import java.util.ArrayList;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.falcon.gameworld.GameWorld;
import com.falcon.zbhelpers.AssetLoader;

public class Jet {
	private Vector2 position;
	public Vector2 velocity;
	private int width, height;
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private Circle boundingCircle;
	private boolean isAlive;
	private GameWorld myWorld;


	public Jet(GameWorld myWorld, float x, float y, int width, int height) {
		this.myWorld = myWorld;
		this.width = width;
		this.height = height;
		velocity = new Vector2();
		position = new Vector2(x, y);
		boundingCircle = new Circle();
		isAlive = true;
	}

	public void update(float delta) {
		if(isAlive)
			position.add(velocity.cpy().scl(delta));
		if(position.x < 0)
			position.x = 0;
		else if(position.x > 136 - width)
			position.x = 136 - width;

		boundingCircle.set(position.x+17, position.y+17, 15);
	}

	public void updateReady(float runTime) {
	}

	public void shoot() {
		if(isAlive && projectiles.size() < 10) {
			Projectile p = new Projectile((position.x + 10), position.y - 5);
			projectiles.add(p);
			AssetLoader.shoot.play();
		}
	}

	public void moveLeft() {
		if(isAlive && myWorld.isRunning()) 
			position.x -= 10;
	}

	public void moveRight() {
		if(isAlive && myWorld.isRunning()) 
			position.x += 10;
	}

	public void clearAllProjectiles() {
		projectiles.clear();
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void die() {
		isAlive = false;
	}

	public void onRestart(int y) {
		position.x = 56;
		position.y = y;
		isAlive = true;
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
