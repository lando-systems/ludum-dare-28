package com.gamedev.ld28.entities;

public class Zombie extends Entity {
	
	public Zombie(int x, int y, int dir) {
		super(x, y, dir);
	}
	
	private static final int NORTH = 0;
	private static final int EAST = 1;
	private static final int SOUTH = 2;
	private static final int WEST = 3;
	
	
	@Override
	public void takeAction(Entity.ACTIONS action) {
		super.takeAction(action);
		super.standardMove(action);
	}

}
