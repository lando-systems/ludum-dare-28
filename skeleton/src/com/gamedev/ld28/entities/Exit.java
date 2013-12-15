package com.gamedev.ld28.entities;
import com.gamedev.ld28.Level;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamedev.ld28.Assets;
import com.gamedev.ld28.utils.Constants;

public class Exit extends Entity {

	public Exit(Level level, int x, int y) {
		super(level, x, y, Constants.NORTH);
		sprite = new Sprite(Assets.exit);
	}
	
	@Override
	public void revert() {
		return;
	}
}
