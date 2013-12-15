package com.gamedev.ld28.entities;

import com.gamedev.ld28.Assets;

public class Zombie extends Entity {
	
	public Zombie(int x, int y, int dir) {
		super(x, y, dir);
		buildAnim(Assets.zombiesheet);
	}
	
	@Override
	public void takeAction(Entity.ACTIONS action) {
		super.saveState();
		super.moveStandard(action);
	}

}
