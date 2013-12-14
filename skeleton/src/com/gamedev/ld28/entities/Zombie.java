package com.gamedev.ld28.entities;

public class Zombie extends Entity {
	
	public Zombie(int x, int y, int dir) {
		super(x, y, dir);
	}
	
	@Override
	public void takeAction(Entity.ACTIONS action) {
		super.takeAction(action);
		super.standardMove(action);
	}

}
