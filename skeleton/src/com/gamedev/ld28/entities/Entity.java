package com.gamedev.ld28.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamedev.ld28.Assets;
import com.gamedev.ld28.Level;
import com.gamedev.ld28.utils.*;

public class Entity
{
  protected int x;
  protected int y;
  protected int dir;
  protected int oldX;
  protected int oldY;
  protected int oldDir;
  public boolean shouldRevert;
  
  public boolean walkable;
  public boolean externallyMovable;
  protected Sprite sprite;
  protected Sprite[] animTiles;

  protected Level level;

  private int TILE_SIZE = 64;
  private float animationTimer = 0f;

  public static enum ACTIONS
  {
    FORWARD, BACK, TURN_CCW, TURN_CW
  }

  public Entity(Level level, int x, int y, int dir)
  {
    this.x = x;
    this.y = y;
    this.dir = dir;
    this.shouldRevert = false;

    walkable = false;
    externallyMovable = false;

    this.level = level;
   }

  protected void buildAnim(Texture textureSheet)
  {
    animTiles = new Sprite[4 * 4]; 
    for (int y = 0; y < 4; y++)
    {
      for (int x = 0; x < 4; x++)
      {
        animTiles[x + (y * 4)] = new Sprite(textureSheet, x*TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
      }
    }
  }

  public int getX()
  {
    return this.x;
  }
  public int getY()
  {
    return this.y;
  }
  public int getDir()
  {
    return this.dir;
  }
  public int getOldX()
  {
    return this.oldX;
  }
  public int getOldY()
  {
    return this.oldY;
  }
  public int getOldDir()
  {
    return this.oldDir;
  }

  public void saveState()
  {
    // Record the current state
    this.oldX = this.x;
    this.oldY = this.y;
    this.oldDir = this.dir;
  }

  protected void moveAction(Entity.ACTIONS action)
  {
    switch(action)
    {
      case TURN_CW:
        this.dir = (this.dir+1)%4;
        break;
      case TURN_CCW:
        this.dir = (this.dir+3)%4;
        break;
      case BACK:
        this.moveDir((this.dir+2)%4);
        break;
      case FORWARD:
        this.moveDir(this.dir);
        break;
      default:
        break;
    }
  }
  protected void moveDir(int dir)
  {
    int positionChange = 1;
    if(dir >= Constants.SOUTH)
      positionChange *= -1;
      
    if(dir % 2 == 0) this.y += positionChange;
    else             this.x += positionChange;
  }

  protected void bePushed(int dir) {}
  public void takeAction(Entity.ACTIONS action) {}

  public void revert()
  {
    this.x = this.oldX;
    this.y = this.oldY;
    this.dir = this.oldDir;
    this.shouldRevert = false;
  }

  public void render(float dt)
  {
	  animationTimer += 2.0f * dt;
	  if (animationTimer >= 4.0f) animationTimer -= 4.0f;
    Sprite tile = sprite;
    if(animTiles != null)
      tile = animTiles[(dir * 4) + (int)animationTimer];
    if(tile != null)
    {
      Utils.setScreenPosition(tile, x, y, 64, 64);
      tile.draw(Assets.batch);
    }
  }

  public boolean overlaps(Entity entity)
  {
    if(this.x == entity.getX() && this.y == entity.getY())
      return true;
    else
      return false;
  }

  public boolean passedThrough(Entity entity)
  {
    if (this.x == entity.getOldX() &&
        this.y == entity.getOldY() &&
        this.oldX == entity.getX() &&
        this.oldY == entity.getY())
      return true;
    else
      return false;
  }
}
