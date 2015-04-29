package com.falcon.gameobjects;

public class Background extends Scrollable{

	public Background(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
    }
	
	public void backgroundReady() {
		accel.y = 0;
	}
	
	public void backgroundGo() {
		accel.y = 7;
	}
	
	public void onRestart(float y, float scrollSpeed) {
        position.y = y;
        velocity.y = scrollSpeed;
    }
	
}
