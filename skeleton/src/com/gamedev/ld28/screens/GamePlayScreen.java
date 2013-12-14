package com.gamedev.ld28.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamedev.ld28.Assets;
import com.gamedev.ld28.Config;
import com.gamedev.ld28.Skeleton;
import com.gamedev.ld28.Utils;
import com.gamedev.ld28.Utils.EStringJustify;

import com.gamedev.ld28.entities.Entity;

public class GamePlayScreen extends GameScreen
{
  private OrthographicCamera camera;
  private Sprite sprite;

  protected Skeleton game;
  protected ArrayList<Entity> entities;

  //Sample data- in future we'll read in from file
  //'- ' - nothing
  //'zd' - zombie
  //'x ' - stone
  //'wd' - you/wizard
  //'*d' - direction: n(orth), e(ast) s(outh) w(est)
  protected String mapData =  //Note- make sure length of string/2 is a perfect square
    "- - - - - - - - - - "+
    "- znx x - - - - - x "+
    "- - x - zs- - - - x "+
    "- - - - - - - ze- - "+
    "- - - - - zw- - - - "+
    "- x zs- x - - - - - "+
    "- x x x x - x x x - "+
    "- - - - - - x x x - "+
    "- - - wn- - x x x - "+
    "- - - - - - - - - - ";

  public GamePlayScreen(Skeleton game)
  {
    super(game);
    camera = new OrthographicCamera(Config.screenWidth, Config.screenHeight);

    //sprite = new Sprite(Assets.titleBackground);
    //sprite.setSize(Config.screenWidth, Config.screenHeight);
    //sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
    //sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);

    entities = new ArrayList<Entity>();
  }

  private void parseMapDataIntoEntities(String mapString)
  {
    int width;
    int height = width = (int)Math.sqrt(mapString.length()/2);

    for(int i = 0; i < height; i++)
    {
      for(int j = 0; j < width; j++)
      {
        //Find direction
        //int dir;
        switch(mapString.charAt((i*width)+j)*2+1)
        {
          case 'n':
            //dir = north;
            break;
          case 's':
            //dir = south;
            break;
          case 'e':
            //dir = east;
            break;
          case 'w':
            //dir = west;
            break;
          default:
            break;
        }

        //Find type
        switch(mapString.charAt((i*width)+j)*2)
        {
          case '-':
            break;
          case 'x':
            //entities.add(new Stone(j,i));
            break;
          case 'z':
            //entities.add(new Zombie(j,i,dir));
            break;
          case 'w':
            //entities.add(new Wizard(j,i,dir));
            break;
          default:
            break;
        }
      }
    }
  }

  @Override
  public void update(float delta)
  {
    super.update(delta);

    if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
      Gdx.app.exit();
    }

    if (Gdx.input.justTouched()) {
      //game.setScreen(game.screens.get("SomeOtherScreen"));
    }

    camera.update();
  }

  @Override
    public void render(float delta)
    {
      update(delta);

      Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1.f);
      Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

      Assets.batch.setProjectionMatrix(camera.combined);
      Assets.batch.begin();
      sprite.draw(Assets.batch);
      Assets.batch.end();

      Utils.drawText(Config.title, 20, 300, 48, 48, Color.RED, EStringJustify.CENTER);
    }


  @Override
    public void dispose() {
    }

  @Override
    public void resize(int width, int height) {}

}