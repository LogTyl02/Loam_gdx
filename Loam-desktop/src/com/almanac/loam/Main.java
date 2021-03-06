package com.almanac.loam;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Loam " + Loam.VERSION;
		cfg.useGL20 = true;
		cfg.width = 900;
		cfg.height = 600;
		
		new LwjglApplication(new Loam(), cfg);
	}
}
