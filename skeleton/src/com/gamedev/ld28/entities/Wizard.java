package com.gamedev.ld28.entities;

public class Wizard extends Entity {
	
	public Wizard(int x, int y, int dir) {
		super(x, y, dir);
	}
	
	@Override
	public void takeAction(Entity.ACTIONS action) {
		super.takeAction(action);
		super.standardMove(action);
	}

}
