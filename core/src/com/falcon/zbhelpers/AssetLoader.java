package com.falcon.zbhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	public static Texture texture, bg1, bg2, bg3, logo, playButtonUp, playButtonDown;
	public static Animation bombAnimation;
	public static TextureRegion jet, bomb, blinkingBomb;
	public static Sound explosion, shoot;
	public static BitmapFont font, shadow;
	public static Preferences prefs;


	public static void load() {
		
		// Create (or retrieve existing) preferences file
		prefs = Gdx.app.getPreferences("ZombieBird");

		// Provide default high score of 0
		if (!prefs.contains("highScore")) {
		    prefs.putInteger("highScore", 0);
		}
		
		playButtonUp = new Texture(Gdx.files.internal("data/unpressed play.png"));
        playButtonDown =  new Texture(Gdx.files.internal("data/play pressed.png"));

        logo = new Texture(Gdx.files.internal("data/logo.png"));
		
		explosion = Gdx.audio.newSound(Gdx.files.internal("data/explosion.wav"));
		shoot = Gdx.audio.newSound(Gdx.files.internal("data/shoot.wav"));
		
		texture = new Texture(Gdx.files.internal("data/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		jet = new TextureRegion(texture, 0, 0, 25, 25);
		jet.flip(false, true);

		blinkingBomb = new TextureRegion(texture, 25, 0, 20, 20);
		blinkingBomb.flip(false, true);
		
		bomb = new TextureRegion(texture, 45, 0, 20, 20);
		bomb.flip(false, true);
		
		bg1 = new Texture(Gdx.files.internal("data/background.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		bg2 = new Texture(Gdx.files.internal("data/background2.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		bg3 = new Texture(Gdx.files.internal("data/background3.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		TextureRegion[] bombArray = { bomb, blinkingBomb};
		bombAnimation = new Animation(15f, bombArray);
		bombAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
		font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
		font.setScale(.25f, -.25f);
		shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
		shadow.setScale(.25f, -.25f);

	}
	
	// Receives an integer and maps it to the String highScore in prefs
	public static void setHighScore(int val) {
	    prefs.putInteger("highScore", val);
	    prefs.flush();
	}

	// Retrieves the current high score
	public static int getHighScore() {
	    return prefs.getInteger("highScore");
	}

	public static void dispose() {
		// We must dispose when we are finished.
		bg1.dispose();
		bg2.dispose();
		bg3.dispose();
		texture.dispose();
		explosion.dispose();
		shoot.dispose();
		font.dispose();
		shadow.dispose();
	}

}
