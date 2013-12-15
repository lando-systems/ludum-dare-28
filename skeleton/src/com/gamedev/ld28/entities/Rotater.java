package com.gamedev.ld28.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamedev.ld28.Assets;
import com.gamedev.ld28.Level;
import com.gamedev.ld28.utils.Constants;

public class Rotater extends Entity {

	public Rotater(Level level, int x, int y) {
		super(level, x, y, Constants.NORTH);
        this.walkable = true;
		sprite = new Sprite(Assets.rotator);
		buildAnim(Assets.rotator);
	}

}
