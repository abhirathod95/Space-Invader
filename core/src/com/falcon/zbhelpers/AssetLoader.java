package com.falcon.zbhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	public static Texture texture, texture2, texture3, bg1, bg2, bg3, logo, buttons;
	public static TextureAtlas objects;
	public static Animation bombAnimation, metAnimation, expAnimation;
	public static TextureRegion motherShip, jet, met1, met2, met3, met4, met5, met6;
	public static TextureRegion exp1, exp2, exp3, exp4, exp5, exp6, exp7, exp8, exp9;
	public static TextureRegion jetR1, jetL1, missile, laser;
	public static TextureRegion playButtonUp, playButtonDown;
	public static Sound explosion, shoot;
	public static BitmapFont font, shadow, whiteFont;
	public static Preferences prefs;


	public static void load() {

        logo = new Texture(Gdx.files.internal("data/logo.png"));
		
		explosion = Gdx.audio.newSound(Gdx.files.internal("data/sounds/explosion.wav"));
		shoot = Gdx.audio.newSound(Gdx.files.internal("data/sounds/shoot.wav"));
		
		texture = new Texture(Gdx.files.internal("data/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		texture2 = new Texture(Gdx.files.internal("data/texture2.png"));
		texture2.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		texture3 = new Texture(Gdx.files.internal("data/texture3.png"));
		texture3.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		buttons = new Texture(Gdx.files.internal("data/buttons.png"));
		buttons.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		objects = new TextureAtlas(Gdx.files.internal("data/skins/objects.pack"), false);
		
		jet = new TextureRegion(objects.findRegion("redfighter0005"));
		jet.flip(false, true);
		
		jetR1 = new TextureRegion(objects.findRegion("redfighter0001"));
		jetR1.flip(false, true);
		
		jetL1 = new TextureRegion(objects.findRegion("redfighter0009"));
		jetL1.flip(false, true);
		
		motherShip = new TextureRegion(objects.findRegion("RD1"));
		motherShip.flip(false, true);
		
		missile = new TextureRegion(objects.findRegion("missile"));
		missile.flip(false, true);
		
		laser = new TextureRegion(objects.findRegion("laser"));
		laser.flip(false, true);
		
		playButtonUp = new TextureRegion(buttons, 0, 0, 29, 16);
		playButtonDown = new TextureRegion(buttons, 58, 0, 29, 16);
		playButtonUp.flip(false, true);
        playButtonDown.flip(false, true);
        
		met1 = new TextureRegion(texture2, 0, 0, 273, 416);
		met1.flip(false, true);
		
		met2 = new TextureRegion(texture2, 0, 416, 273, 416);
		met2.flip(false, true);
		
		met3 = new TextureRegion(texture2, 0, 832, 273, 416);
		met3.flip(false, true);
		
		met4 = new TextureRegion(texture2, 0, 1248, 273, 416);
		met4.flip(false, true);
		
		met5 = new TextureRegion(texture2, 0, 1664, 273, 416);
		met5.flip(false, true);
		
		met6 = new TextureRegion(texture2, 0, 2080, 273, 416);
		met6.flip(false, true);
		
		exp1 = new TextureRegion(texture3, 0, 0, 420, 442);
		exp1.flip(false, true);
		
		exp2 = new TextureRegion(texture3, 420, 0, 460, 442);
		exp2.flip(false, true);
		
		exp3 = new TextureRegion(texture3, 880, 0, 462, 442);
		exp3.flip(false, true);
		
		exp4 = new TextureRegion(texture3, 0, 442, 470, 442);
		exp4.flip(false, true);
		
		exp5 = new TextureRegion(texture3, 470, 442, 505, 442);
		exp5.flip(false, true);
		
		exp6 = new TextureRegion(texture3, 975, 442, 465, 442);
		exp6.flip(false, true);
		
		exp7 = new TextureRegion(texture3, 0, 884, 470, 442);
		exp7.flip(false, true);

		exp8 = new TextureRegion(texture3, 470, 884, 460, 442);
		exp8.flip(false, true);
		
		exp9 = new TextureRegion(texture3, 930, 884, 460, 442);
		exp9.flip(false, true);
		
		bg1 = new Texture(Gdx.files.internal("data/galaxy1.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		bg2 = new Texture(Gdx.files.internal("data/galaxy2.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		bg3 = new Texture(Gdx.files.internal("data/galaxy3.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		TextureRegion[] metArray = { met1, met2, met3, met4, met5, met6};
		metAnimation = new Animation(5f, metArray);
		metAnimation.setPlayMode(Animation.PlayMode.LOOP);
			
		TextureRegion[] expArray = { exp1, exp2, exp3, exp4, exp5, exp6, exp7, exp8, exp9};
		expAnimation = new Animation(.1f, expArray);
		expAnimation.setPlayMode(Animation.PlayMode.NORMAL);
		
		font = new BitmapFont(Gdx.files.internal("data/fonts/text.fnt"));
		font.setScale(.25f, -.25f);
		whiteFont = new BitmapFont(Gdx.files.internal("data/fonts/whitetext.fnt"));
		whiteFont.setScale(.1f, -.1f);
		shadow = new BitmapFont(Gdx.files.internal("data/fonts/shadow.fnt"));
		shadow.setScale(.25f, -.25f);

	}
	
	public static void dispose() {
		// We must dispose when we are finished.
		bg1.dispose();
		bg2.dispose();
		bg3.dispose();
		objects.dispose();
		texture.dispose();
		texture2.dispose();
		texture3.dispose();
		logo.dispose();
		buttons.dispose();
		explosion.dispose();
		shoot.dispose();
		font.dispose();
		shadow.dispose();
	}

}
