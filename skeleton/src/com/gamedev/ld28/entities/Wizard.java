package com.gamedev.ld28.entities;

import com.gamedev.ld28.Assets;

public class Wizard extends Entity {
	
	public Wizard(int x, int y, int dir) {
		super(x, y, dir);
		buildAnim(Assets.zombiesheet);
	}
	
	@Override
	public void takeAction(Entity.ACTIONS action) {
		super.takeAction(action);
		super.standardMove(action);
	}

}
