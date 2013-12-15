package com.gamedev.ld28.entities;
import com.gamedev.ld28.Level;
import com.gamedev.ld28.Assets;

public class Wizard extends Entity
{
	public Wizard(Level level, int x, int y, int dir) {
		super(level, x, y, dir);
		buildAnim(Assets.wizardsheet);
	}
	
	@Override
	public void takeAction(Entity.ACTIONS action) {
		super.moveAction(action);
	}

}
