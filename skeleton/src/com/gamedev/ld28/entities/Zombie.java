package com.gamedev.ld28.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamedev.ld28.Assets;

public class Zombie extends Entity {
	
	public Zombie(int x, int y, int dir) {
		super(x, y, dir);
		//sprite = new Sprite(Assets.stone);
		buildAnim(Assets.zombiesheet);
	}
	
	@Override
	public void takeAction(Entity.ACTIONS action) {
		super.takeAction(action);
		super.standardMove(action);
	}

}
