package com.gamedev.ld28.entities;

import com.gamedev.ld28.Assets;

public class Wizard extends Entity {
	
	public Wizard(int x, int y, int dir) {
		super(x, y, dir);
		buildAnim(Assets.wizardsheet);
	}
	
	@Override
	public void takeAction(Entity.ACTIONS action) {
		super.saveState();
		super.doAction(action);
	}

}
