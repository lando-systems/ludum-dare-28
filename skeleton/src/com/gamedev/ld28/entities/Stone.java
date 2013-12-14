package com.gamedev.ld28.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamedev.ld28.Assets;

public class Stone extends Entity {
	
	public Stone(int x, int y, int dir) {
		super(x, y, dir);
		sprite = new Sprite(Assets.stone);
	}
	
}