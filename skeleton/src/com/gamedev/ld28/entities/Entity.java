package com.gamedev.ld28.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamedev.ld28.Assets;
import com.gamedev.ld28.utils.Constants;
import com.gamedev.ld28.utils.Utils;

public class Entity
{
  protected int x;
  protected int y;
  protected int dir;
  protected int oldX;
  protected int oldY;
  protected int oldDir;
  protected Sprite sprite;
  protected Sprite[] animTiles;
  private int TILE_SIZE = 64;
  private float animationTimer = 0f;

  public static enum ACTIONS
  {
    FORWARD, BACK, TURN_CCW, TURN_CW
  }

  public Entity(int x, int y, int dir)
  {
    this.x = x;
    this.y = y;
    this.dir = dir;
    this.saveState();
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

  protected void saveState()
  {
    // Record the current state
    this.oldX = this.x;
    this.oldY = this.y;
    this.oldDir = this.dir;
  }

  protected void doAction(Entity.ACTIONS action)
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

  protected void movePush(int dir) {}
  public void takeAction(Entity.ACTIONS action) {}

  public void revert()
  {
    this.x = this.oldX;
    this.y = this.oldY;
    this.dir = this.oldDir;
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

