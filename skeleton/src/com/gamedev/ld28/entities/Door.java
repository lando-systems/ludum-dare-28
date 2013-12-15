package com.gamedev.ld28.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamedev.ld28.Assets;
import com.gamedev.ld28.Level;
import com.gamedev.ld28.utils.*;

public class Door extends Entity {
	
	public Door(Level level, int x, int y, char pairId) {
		
		super(level, x, y, Constants.NORTH);
		this.walkable = false;
		this.pairId = pairId;
		buildOnOffTiles(Assets.doorsheet);
	}
	
	@Override
	public void revert() {
		return;
	}
	
	public void open() {
		this.isOn = true;
		this.walkable = true;
	}
	
	public void close() {
		this.isOn = false;
		this.walkable = false;
	}
	
}
