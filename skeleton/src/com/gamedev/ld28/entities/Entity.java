package com.gamedev.ld28.entities;

import com.gamedev.ld28.utils.Constants;

public class Entity {
	
	protected int x;
	protected int y;
	protected int dir;
	protected int oldX;
	protected int oldY;
	protected int oldDir;
	
	public static enum ACTIONS {
		FORWARD, BACK, TURN_CCW, TURN_CW
	}
	
	public Entity(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public int getX
	
	
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
	
}
