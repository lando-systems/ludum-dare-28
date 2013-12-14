package com.gamedev.ld28.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Screen;
import com.gamedev.ld28.Skeleton;
import com.gamedev.ld28.entities.Entity;

public abstract class GameScreen implements Screen
{
	protected Skeleton game;
        protected ArrayList<Entity> entities;

	public GameScreen(Skeleton game)
        {
          this.game = game;
          entities = new ArrayList<Entity>();
        }

	public void update(float delta)
        {

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
