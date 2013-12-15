package com.gamedev.ld28.entities;

import com.badlogic.gdx.graphics.Texture;
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
	protected Sprite[] animTiles;
	private int TILE_SIZE = 64;
	
	public static enum ACTIONS {
		FORWARD, BACK, TURN_CCW, TURN_CW
	}
	
	public Entity(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		
	}
	
	protected void buildAnim(Texture textureSheet)
	{
		animTiles = new Sprite[4 * 4]; 
		for (int y = 0; y < 4; y++){
			for (int x = 0; x < 4; x++){
				animTiles[x + (y * 4)] = new Sprite(textureSheet, x*TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
			}
		}
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
	public int getOldX() {
		return this.oldX;
	}
	public int getOldY() {
		return this.oldY;
	}
	public int getOldDir() {
		return this.oldDir;
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
				this.dir ++;
				break;
			case TURN_CCW:
				this.dir --;
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
		if (dir < 0 ) dir += 4;
		if (dir > 3 ) dir -= 4;
	}
	
	public void revert() {
		this.x = this.oldX;
		this.y = this.oldY;
		this.dir = this.oldDir;
	}
	
	public void render(){
		
		Sprite tile = sprite;
		if (animTiles != null){
			tile = animTiles[dir * 4];
		}
		if (tile != null){
			Utils.setScreenPosition(tile, x, y, 64, 64);
		
			tile.draw(Assets.batch);
		}
	}
	
	public boolean overlaps(Entity entity) {
		if (this.x == entity.getX() &&
			this.y == entity.getY()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean passedThrough(Entity entity) {
		if (this.oldX == entity.getOldX() &&
			this.oldY == entity.getOldY()) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
