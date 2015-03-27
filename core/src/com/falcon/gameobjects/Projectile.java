package com.falcon.gameobjects;

import java.util.ListIterator;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Projectile {
	private Vector2 position, velocity;
	public boolean visible;
	private Rectangle boundingRectangle;

	private static final int SPEEDY = -80;
	private static final int WIDTH = 5;
	private static final int HEIGHT = 5;

	public Projectile(float startX, float startY) {
		position = new Vector2(startX, startY);
		velocity = new Vector2(0,SPEEDY);
		visible = true;
		boundingRectangle = new Rectangle();
	}

	public void update(float delta){
		position.add(velocity.cpy().scl(delta));
		if (position.y < 110) {
			visible = false;
		}
		boundingRectangle.set(position.x, position.y, WIDTH, HEIGHT);
	}

	public boolean collides(Bomb bomb) {
		return (Intersector.overlaps(bomb.getBoundingRectangle(), boundingRectangle));
	}
	
	public float getX() {
		return position.x;
	}


	public float getY() {
		return position.y;
	}


	public int getSpeedY() {
		return SPEEDY;
	}

	public boolean isVisible() {
		return visible;
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public Rectangle getBoundingRectangle() {
		return boundingRectangle;
	}
}
