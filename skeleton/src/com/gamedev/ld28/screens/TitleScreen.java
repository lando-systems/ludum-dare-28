package com.gamedev.ld28.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamedev.ld28.Skeleton;

public class TitleScreen extends GameScreen {

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;

	public TitleScreen(Skeleton game) {
		super(game);

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();

		texture = new Texture(Gdx.files.internal("data/libgdx.png"));
		texture .setFilter(TextureFilter.Linear, TextureFilter.Linear);

		sprite = new Sprite(texture);
		sprite.setSize(1.0f, (h / w) * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
	}

	@Override
	public void update(float delta) {
		super.update(delta);

		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}

		if (Gdx.input.justTouched()) {
//			game.setScreen(game.screens.get("SomeOtherScreen"));
		}

		camera.update();
	}

	@Override
	public void render(float delta) {
		update(delta);

		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1.f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		sprite.draw(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

	@Override
	public void resize(int width, int height) {}

}