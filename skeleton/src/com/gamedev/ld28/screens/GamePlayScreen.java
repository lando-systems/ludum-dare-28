package com.gamedev.ld28.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamedev.ld28.Assets;
import com.gamedev.ld28.Skeleton;

import com.gamedev.ld28.utils.*;
import com.gamedev.ld28.entities.*;
import com.gamedev.ld28.Level;

public class GamePlayScreen extends GameScreen
{
  private OrthographicCamera camera;
  private Sprite gameBoard;
  private Level currentLevel;
  private float keyTimer = 0f;

  public GamePlayScreen(Skeleton game)
  {
    super(game);
    camera = new OrthographicCamera(Config.screenWidth, Config.screenHeight);
    gameBoard = new Sprite(Assets.gameBoard);
    //sprite = new Sprite(Assets.titleBackground);
    //sprite.setSize(Config.screenWidth, Config.screenHeight);
    //sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
    //sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);

    currentLevel = new Level(0, game);
  }

  @Override
  public void update(float delta)
  {
    super.update(delta);

    keyTimer -= delta;
    if (keyTimer <= 0 ) keyTimer = 0f;
    if(Gdx.input.isKeyPressed(Keys.ESCAPE))
    {
      //Gdx.app.exit();
    	currentLevel.resetLevel();
    }

    Entity.ACTIONS action = null;
    if (keyTimer <= 0f){
	    if(Utils.isKeyJustPressed(Keys.W)) action = Entity.ACTIONS.FORWARD;
	    if(Utils.isKeyJustPressed(Keys.A)) action = Entity.ACTIONS.TURN_CCW;
	    if(Utils.isKeyJustPressed(Keys.S)) action = Entity.ACTIONS.BACK;
	    if(Utils.isKeyJustPressed(Keys.D)) action = Entity.ACTIONS.TURN_CW;
    }
    if (action != null) {
    	System.out.println("action: "+action.toString());
    }
   
    if (action == Entity.ACTIONS.FORWARD || action == Entity.ACTIONS.BACK) keyTimer = Constants.MovementTime;
    currentLevel.takeAction(action);

    camera.update();
    
    Utils.updateInput();
  }

  @Override
    public void render(float delta)
    {
      update(delta);

      Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1.f);
      Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

      Assets.batch.setProjectionMatrix(camera.combined);
      Assets.batch.begin();
      Utils.setScreenPosition(gameBoard, 0, 0, Assets.gameBoard.getWidth(), Assets.gameBoard.getHeight());
      gameBoard.draw(Assets.batch);
      currentLevel.render(delta);
      Assets.batch.end();
    }

  @Override
    public void dispose() {
    }

  @Override
    public void resize(int width, int height) {}
}
