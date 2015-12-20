package com.brycemacinnis.bearwithme.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.brycemacinnis.bearwithme.BearWithMe;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new BearWithMe(), config);
		config.width = 800;
		config.height = 800;
		config.resizable = false;
	}
}
