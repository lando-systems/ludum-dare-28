package com.gamedev.ld28.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
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

  //Sample data- in future we'll read in from file
  //'- ' - nothing
  //'zd' - zombie
  //'x ' - stone
  //'o ' - barrel
  //'wd' - you/wizard
  //'*d' - direction: n(orth), e(ast) s(outh) w(est)
  
  
  protected String mapData =  //Note- make sure length of string/2 is a perfect square
    "x x x x x x x x x x "+
    "x znx x - - - - - x "+
    "x - x - zs- o - - x "+
    "x - - - - - - ze- x "+
    "x - - o - zw- - - x "+
    "x x zs- x - - - - x "+
    "x x x x x - x x x x "+
    "x q - - - o x x x x "+
    "x - - wn- - x x x x "+
    "x x x x x x x x x x ";
  
  /*
  protected String mapData =  //Note- make sure length of string/2 is a perfect square
		    "x x x x x x x x x x "+
		    "x - - - - - - - - x "+
		    "x - - - - - - - - x "+
		    "x - - - - - - - - x "+
		    "x - - - o - - - - x "+
		    "x - - - zn- - - - x "+
		    "x - - - - - - - - x "+
		    "x - - - - - - - - x "+
		    "x - - - - - - - - x "+
		    "x x x x x x x x x x ";
		    */

  public GamePlayScreen(Skeleton game)
  {
    super(game);
    camera = new OrthographicCamera(Config.screenWidth, Config.screenHeight);
    gameBoard = new Sprite(Assets.gameBoard);
    //sprite = new Sprite(Assets.titleBackground);
    //sprite.setSize(Config.screenWidth, Config.screenHeight);
    //sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
    //sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);

    currentLevel = new Level(mapData);
  }

  @Override
  public void update(float delta)
  {
    super.update(delta);

    if(Gdx.input.isKeyPressed(Keys.ESCAPE))
    {
      Gdx.app.exit();
    }

    Entity.ACTIONS action = null;
    if(Utils.isKeyJustPressed(Keys.W)) action = Entity.ACTIONS.FORWARD;
    if(Utils.isKeyJustPressed(Keys.A)) action = Entity.ACTIONS.TURN_CCW;
    if(Utils.isKeyJustPressed(Keys.S)) action = Entity.ACTIONS.BACK;
    if(Utils.isKeyJustPressed(Keys.D)) action = Entity.ACTIONS.TURN_CW;

    currentLevel.takeAction(action);

    if (Gdx.input.justTouched()) {
      //game.setScreen(game.screens.get("SomeOtherScreen"));
    }

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
