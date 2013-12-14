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
import com.gamedev.ld28.Skeleton;

import com.gamedev.ld28.utils.*;
import com.gamedev.ld28.entities.*;

public class GamePlayScreen extends GameScreen
{
  private OrthographicCamera camera;
  private Sprite sprite;

  protected ArrayList<Entity> entities;

  //Sample data- in future we'll read in from file
  //'- ' - nothing
  //'zd' - zombie
  //'x ' - stone
  //'wd' - you/wizard
  //'*d' - direction: n(orth), e(ast) s(outh) w(est)
  protected String mapData =  //Note- make sure length of string/2 is a perfect square
    "x x x x x x x x x x "+
    "x znx x - - - - - x "+
    "x - x - zs- - - - x "+
    "x - - - - - - ze- x "+
    "x - - - - zw- - - x "+
    "x x zs- x - - - - x "+
    "x x x x x - x x x x "+
    "x - - - - - x x x x "+
    "x - - wn- - x x x x "+
    "x x x x x x x x x x ";

  public GamePlayScreen(Skeleton game)
  {
    super(game);
    camera = new OrthographicCamera(Config.screenWidth, Config.screenHeight);

    //sprite = new Sprite(Assets.titleBackground);
    //sprite.setSize(Config.screenWidth, Config.screenHeight);
    //sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
    //sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);

    entities = new ArrayList<Entity>();
    this.parseMapDataIntoEntities(mapData);
  }

  private void parseMapDataIntoEntities(String mapString)
  {
    int width;
    int height = width = (int)Math.sqrt(mapString.length()/2);
    int dir = 0;

    for(int y = 0; y < height; y++)
    {
      for(int x = 0; x < width; x++)
      {
        //Find direction
        switch(mapString.charAt(((y*width)+x)*2+1))
        {
          case 'n':
            dir = Constants.NORTH;
            break;
          case 's':
            dir = Constants.SOUTH;
            break;
          case 'e':
            dir = Constants.EAST;
            break;
          case 'w':
            dir = Constants.WEST;
            break;
          default:
            break;
        }
        

        //Find type
        switch(mapString.charAt(((y*width)+x)*2+1))
        {
          case '-':
            break;
          case 'x':
            entities.add(new Stone(x,y,dir));
            break;
          case 'z':
            entities.add(new Zombie(x,y,dir));
            break;
          case 'w':
            entities.add(new Wizard(x,y,dir));
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

    if(Gdx.input.isKeyPressed(Keys.ESCAPE))
    {
      Gdx.app.exit();
    }

    Entity.ACTIONS action = null;
    if(Utils.isKeyJustPressed(Keys.W)) action = Entity.ACTIONS.FORWARD;
    if(Utils.isKeyJustPressed(Keys.A)) action = Entity.ACTIONS.TURN_CCW;
    if(Utils.isKeyJustPressed(Keys.S)) action = Entity.ACTIONS.BACK;
    if(Utils.isKeyJustPressed(Keys.D)) action = Entity.ACTIONS.TURN_CW;

    if(action != null)
    {
      for(Entity entity : entities)
      {
    	  entity.takeAction(action);
      }
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
      //sprite.draw(Assets.batch);
      Assets.batch.end();

      Utils.drawText(Config.title, 20, 300, 48, 48, Color.RED, Utils.EStringJustify.CENTER);
    }


  @Override
    public void dispose() {
    }

  @Override
    public void resize(int width, int height) {}
  
  
  
	public void validateMap() {
		
		int i, j;
		Entity eA, eB;
		boolean isValid = false;
		validityLoop: while (!isValid) {
			isValid = true;
			for (i = 0; i < this.entities.size(); i++) {
				eA = this.entities.get(i);
				for (j = i + 1; j < this.entities.size(); j++) {
					eB = this.entities.get(j);
					if (eA.getX() == eB.getX() &&
						eA.getY() == eB.getY()) {
						eA.revert();
						eB.revert();
						isValid = false;
						continue validityLoop;
					}
				}
			}
		}
			
	}

}
