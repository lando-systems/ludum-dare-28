package com.gamedev.ld28;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.gamedev.ld28.utils.*;
import com.gamedev.ld28.entities.*;

public class Level
{
	private String helpText = "Arrows move you and zombies. Escape will reset the level ";
	private String[] mapData = new String[] {
			"x x x x x x x x x x x x x x "+
		    "x znx x - - - - - x x wsx x "+
		    "x - x - zs- o - - x x - x x "+
		    "x - - - - - - ze- x x - x x "+
		    "x - - o - zw- - - x x - x x "+
		    "x x zs- x - - - - x x - x x "+
		    "x x x x x x x x x x x - x x "+
		    "x q - - - - - - x x x - x x "+
		    "x - - - - - - - - - - - x x "+
		    "x x x x x x x x x x x x x x ", 
		    								 
			"x x x x x x x x x x x x x x "+
			"x znx x - - - - - x x wsx x "+
			"x - x - zs- o - - x x - x x "+
			"x - - - - - - ze- x x - x x "+
			"x - - o - zw- - - x x - x x "+
			"x x zs- x - - - - x x - x x "+
			"x x x x x x x x x x x - x x "+
			"x q - - - - - - x x x - x x "+
			"x - - - - - - - ze- - - x x "+
			"x x x x x x x x x x x x x x ",
			
			"x x x x x x x x x x x x x x "+
			"x znx x - - - - - x x wsx x "+
			"x - x - zs- o - - x x - x x "+
			"x - - - - - - ze- x x - x x "+
			"x - - o - zw- - - x x - x x "+
			"x x zs- x - - - - x x - x x "+
			"x x x x x x x x x x x - x x "+
			"x q x x - x x - x x x - x x "+
			"x - - zw- - - - zw- - - x x "+
			"x x x x x x x x x x x x x x "};
	
	private String[] mapDesc = new String[] {
	"Get to the next level ",
	"Oh noes there is a zombie in your way ",
	"Now there are two zombies in your way ",
	"You are not strong enough to move those barrels on your own "
	};
	
	protected Wizard player;
	protected Exit exit;
	protected ArrayList<Entity> movingObjects;

  protected ArrayList<Entity> entities;
  protected int width;
  protected int height;
  protected int levelState; //0 = begin, 1 = playing, 2 = end
  private int currentLevel = 0;

  public Level(String mapData)
  {
	  resetLevel();
  }
  
  public void resetLevel(){
	    entities = new ArrayList<Entity>();
	    movingObjects = new ArrayList<Entity>();
	    levelState = 1; //should be 0, but is just auto 1 for now until we have rendering code for beginning and end

	    this.parseMapDataIntoEntities(mapData[currentLevel]);
  }

  private void parseMapDataIntoEntities(String mapString)
  {
    int height = 10;
    int width = 14;
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
        	  Zombie z = new Zombie(this,x,(height-1)-y,dir);
        	  movingObjects.add(z);
        	  entities.add(z);
            break;
          case 'o':
        	  Barrel b = new Barrel(this,x,(height-1)-y);
        	  movingObjects.add(b);
        	  entities.add(b);
            break;
          case 'w':
        	  player = new Wizard(this,x,(height-1)-y,dir);
              entities.add(player);
              break;
          case 'q': 
        	  exit = new Exit(this,x,(height-1)-y); 
        	  entities.add(exit);
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
    if (exit.getX() == player.getX() && exit.getY() == player.getY()){
    	currentLevel = Math.min(currentLevel+1, mapData.length-1);
    	
    	resetLevel();
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
    
    for(Entity entity: movingObjects)
        entity.render(0);
    player.render(0); // I need to be here so i draw on top of things.
    
    Utils.drawShadowText(Assets.batch, "Level " + (currentLevel+1), Config.screenHalfWidth - 40, Config.screenHalfHeight - 70, 30,30, Color.GRAY, Utils.EStringJustify.RIGHT);
    Utils.drawShadowText(Assets.batch, "Lives 1", Config.screenHalfWidth - 40, Config.screenHalfHeight - 90, Color.GRAY, Utils.EStringJustify.RIGHT);
    Utils.drawShadowText(Assets.batch, mapDesc[currentLevel], Config.screenHalfWidth - 40, Config.screenHalfHeight - 120, 20,20, Color.GRAY, Utils.EStringJustify.RIGHT);
    
    Utils.drawShadowText(Assets.batch, helpText, Config.screenHalfWidth - 40, 0, 20,20, Color.GRAY, Utils.EStringJustify.RIGHT);
  }
  
}

