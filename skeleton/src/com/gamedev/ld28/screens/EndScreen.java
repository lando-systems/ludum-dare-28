package com.gamedev.ld28.screens;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamedev.ld28.Assets;
import com.gamedev.ld28.utils.*;
import com.gamedev.ld28.Skeleton;


public class EndScreen extends GameScreen {

	private OrthographicCamera camera;
	private Sprite sprite;


	public EndScreen(Skeleton game) {
		super(game);

		camera = new OrthographicCamera(Config.screenWidth, Config.screenHeight);

		sprite = new Sprite(Assets.endGameBackground);
		sprite.setSize(Config.screenWidth, Config.screenHeight);
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
		//TODO: uncomment me when we want music again
		//Utils.PlayMusic(Assets.titleMusic);
	}

	@Override
	public void update(float delta) {
		super.update(delta);

//		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
//			Gdx.app.exit();
//		}
//
//		if (Gdx.input.justTouched()) {
//			game.setScreen(game.screens.get("Game"));
//		}
//
//		camera.update();
//		flashTimer += delta;
//		if (flashTimer > 2.0f) flashTimer -=2.0f;
	}

	@Override
	public void render(float delta) {
		update(delta);

		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1.f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		Assets.batch.setProjectionMatrix(camera.combined);
		Assets.batch.begin();
		sprite.draw(Assets.batch);
		Assets.batch.end();
		
//		Utils.drawText(Config.title, 0, 300, 40, 40, Color.RED, Utils.EStringJustify.CENTER);
//		if (flashTimer < 1.0f){
//			Utils.drawText("Click to Start", 0, 0, 48, 48, Color.RED, Utils.EStringJustify.CENTER);
//		}
	}

	@Override
	public void dispose() {
	}

	@Override
	public void resize(int width, int height) {}

}