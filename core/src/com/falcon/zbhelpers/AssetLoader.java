package com.falcon.zbhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	public static Texture texture, bg1, bg2, bg3;
	public static Animation bombAnimation;
	public static TextureRegion jet, bomb, blinkingBomb;
	public static Sound explosion, shoot;

	public static void load() {

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

	}

	public static void dispose() {
		// We must dispose of the texture when we are finished.
		texture.dispose();
	}

}
