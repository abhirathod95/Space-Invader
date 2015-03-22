package com.falcon.StarshipInvader.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.falcon.starshipinvader.SIGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		  LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	        config.title = "Starship Invader";
	        config.width = 272;
	        config.height = 408;
	        new LwjglApplication(new SIGame(), config);
	}
}
