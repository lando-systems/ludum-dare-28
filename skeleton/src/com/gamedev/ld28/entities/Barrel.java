package com.gamedev.ld28.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamedev.ld28.Assets;
import com.gamedev.ld28.utils.Constants;

public class Barrel extends Entity
{
  public Barrel(int x, int y)
  {
    super(x, y, Constants.NORTH);
    sprite = new Sprite(Assets.barrel);
  }

  @Override
  protected void movePush(int dir)
  {
    super.moveDir(dir);
  }

  public void push(int dir)
  {
    super.saveState();
    super.movePush(dir);
  }
}
