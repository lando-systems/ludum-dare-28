package com.gamedev.ld28.client;

import com.gamedev.ld28.Skeleton;
import com.gamedev.ld28.utils.Config;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class GwtLauncher extends GwtApplication {
	@Override
	public GwtApplicationConfiguration getConfig () {
		GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(Config.screenWidth, Config.screenHeight);
		return cfg;
	}

	@Override
	public ApplicationListener getApplicationListener () {
		return new Skeleton();
	}
}