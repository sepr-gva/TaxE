package com.seprgva.taxe.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.seprgva.taxe.TaxE;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "TaxE";
		config.width = 1000;
		config.height = 625;
		new LwjglApplication(new TaxE(), config);
	}
}
