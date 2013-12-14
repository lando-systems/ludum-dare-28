package com.gamedev.ld28.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamedev.ld28.Assets;
import com.gamedev.ld28.utils.Constants;
import com.gamedev.ld28.utils.Utils;

public class Entity {
	
	protected int x;
	protected int y;
	protected int dir;
	protected int oldX;
	protected int oldY;
	protected int oldDir;
	protected Sprite sprite;
	
	public static enum ACTIONS {
		FORWARD, BACK, TURN_CCW, TURN_CW
	}
	
	public Entity(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		sprite = new Sprite(Assets.zombie);
	}
	
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public int getDir() {
		return this.dir;
	}
	
	
	public void takeAction(Entity.ACTIONS action) {
		// Record the current state
		this.oldX = this.x;
		this.oldY = this.y;
		this.oldDir = this.dir;
	}
	
	protected void standardMove(Entity.ACTIONS action) {
		
		int positionChange = 1;
		
		switch (action) {
		
			case TURN_CW:
				this.dir = (this.dir + 1) % 4;
				break;
			case TURN_CCW:
				this.dir = (this.dir - 1) % 4;
				break;
				
			case BACK:
				positionChange *= -1;
			case FORWARD:
			default:
				if (this.dir >= Constants.SOUTH) {
					positionChange *= -1;
				}
				if (this.dir % 2 == 0) {
					this.y += positionChange;
				} else {
					this.x += positionChange;
				}
				
				break;
				
		}
	}
	
	public void revert() {
		this.x = this.oldX;
		this.y = this.oldY;
		this.dir = this.oldDir;
	}
	
	public void render(){
		Utils.setScreenPosition(sprite, x, y, 64, 64);
		
		sprite.draw(Assets.batch);
	}
	
}
