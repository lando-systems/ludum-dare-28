package com.gamedev.ld28;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.gamedev.ld28.screens.EndScreen;
import com.gamedev.ld28.screens.GamePlayScreen;
import com.gamedev.ld28.screens.GameScreen;
import com.gamedev.ld28.screens.TitleScreen;

public class Skeleton extends Game {

	public Map<String, GameScreen> screens;

	public Skeleton() {
		super();
		screens = new HashMap<String, GameScreen>();
	}

	@Override
	public void create() {
		Assets.load();
		screens.put("Title", new TitleScreen(this));
		screens.put("Game", new GamePlayScreen(this));
		screens.put("End", new EndScreen(this));
		setScreen(screens.get("Title"));
	}

	@Override
	public void dispose() {
		for (GameScreen screen : screens.values()) {
			screen.dispose();
		}
		Gdx.app.exit();
	}

}