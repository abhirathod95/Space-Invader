package com.falcon.starshipinvader;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.falcon.screens.GameScreen;
import com.falcon.zbhelpers.AssetLoader;

public class SIGame extends Game {

	@Override
	public void create() {
		Gdx.app.log("ZBGame", "created");
		AssetLoader.load(); 
		setScreen(new GameScreen());
	}
	
	@Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
