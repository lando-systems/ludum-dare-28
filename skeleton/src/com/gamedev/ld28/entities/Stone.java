package com.gamedev.ld28.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamedev.ld28.Assets;
import com.gamedev.ld28.Level;
import com.gamedev.ld28.utils.*;

public class Stone extends Entity {
	
	public Stone(Level level, int x, int y) {
		super(level, x, y, Constants.NORTH);
		sprite = new Sprite(Assets.stone);
	}
	
	@Override
	public void revert() {
		return;
	}
	
}
