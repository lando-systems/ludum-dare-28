package com.gamedev.ld28.entities;

public class Entity {
	private int x;
	private int y;
	private int dir;
	private int oldX;
	private int oldY;
	private int oldDir;
	
	public boolean canMove(int dir){
		return false;
	}
	
	public boolean handleInput(){
		return true;
	}
}
