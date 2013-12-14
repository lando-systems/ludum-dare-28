package com.gamedev.ld28.screens;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Screen;
import com.gamedev.ld28.Skeleton;
import com.gamedev.ld28.utils.Utils;

public abstract class GameScreen implements Screen {

	public static final TweenManager tweenMgr = new TweenManager();

	protected Skeleton game;

	public GameScreen(Skeleton game) { this.game = game; }

	public void update(float delta) {
		Utils.updateInput();
		tweenMgr.update(delta);
	}

	@Override
	public void show() {}

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

}