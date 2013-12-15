package com.gamedev.ld28;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.gamedev.ld28.utils.*;
import com.gamedev.ld28.entities.*;

public class Level
{
  protected ArrayList<Entity> entities;
  protected int width;
  protected int height;
  protected int levelState; //0 = begin, 1 = playing, 2 = end

  public Level(String mapData)
  {
    entities = new ArrayList<Entity>();
    levelState = 1; //should be 0, but is just auto 1 for now until we have rendering code for beginning and end

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
        switch(mapString.charAt(((y*width)+x)*2))
        {
          case '-':
            break;
          case 'x':
            entities.add(new Stone(this,x,(height-1)-y));
            break;
          case 'z':
            entities.add(new Zombie(this,x,(height-1)-y,dir));
            break;
          case 'o':
            entities.add(new Barrel(this,x,(height-1)-y));
            break;
          case 'w':
              entities.add(new Wizard(this,x,(height-1)-y,dir));
              break;
          case 'q': 
        	  entities.add(new Exit(this,x,(height-1)-y));
          default:
            break;
        }
      }
    }
  }

  public void takeAction(Entity.ACTIONS action)
  {
    if(action != null)
    {
      for(Entity entity : entities)
    	entity.saveState();
      for(Entity entity : entities)
        entity.takeAction(action);
      this.validateMap();
    }
  }

  public Entity entityAtPosition(int x, int y)
  {
    for(int i = 0; i < entities.size(); i++)
      if(entities.get(i).getOldX() == x && entities.get(i).getOldY() == y)
        return entities.get(i);
    return null;
  }

  public void validateMap()
  {
    int i, j;
    Entity eA, eB;
    boolean isValid = false;
    while(!isValid)
    {
      isValid = true;
      for (i = 0; i < this.entities.size(); i++)
      {
        eA = this.entities.get(i);
        for (j = i + 1; j < this.entities.size(); j++)
        {
          eB = this.entities.get(j);
          if ((eA.overlaps(eB) || eA.passedThrough(eB)) && !(eA.walkable || eB.walkable))
          {
        	  eA.shouldRevert = true;
        	  eB.shouldRevert = true;
              isValid = false;
          }
        }
        if(!isValid)
        {
        	for(Entity entity : entities)
        		if(entity.shouldRevert) entity.revert();
        	continue;
        }
      }
    }
  }

  public void render(float dt)
  {
    switch(levelState)
    {
      case 0://level begin
        break;
      case 1://level playing
        for(Entity entity: entities)
          entity.render(dt);
        break;
      case 2://level end
        break;
    }
    Utils.drawShadowText(Assets.batch, "Level 1", Config.screenHalfWidth - 40, Config.screenHalfHeight - 70, 30,30, Color.GRAY, Utils.EStringJustify.RIGHT);
    Utils.drawShadowText(Assets.batch, "Lives 1", Config.screenHalfWidth - 40, Config.screenHalfHeight - 90, Color.GRAY, Utils.EStringJustify.RIGHT);
    Utils.drawShadowText(Assets.batch, "This is a really long sentance that needs to be wrapped", Config.screenHalfWidth - 40, Config.screenHalfHeight - 120, 20,20, Color.GRAY, Utils.EStringJustify.RIGHT);
  }
  
}

