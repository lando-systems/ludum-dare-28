package com.gamedev.ld28.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamedev.ld28.Assets;
import com.gamedev.ld28.Level;
import com.gamedev.ld28.utils.Constants;

public class Barrel extends Entity
{
	
  public Barrel(Level level, int x, int y)
  {
    super(level, x, y, Constants.NORTH);
    this.externallyMovable = true;
    sprite = new Sprite(Assets.barrel);
  }

  @Override
  public void bePushed(int dir)
  {
    super.moveDir(dir);
  }
}
