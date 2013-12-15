package com.gamedev.ld28.entities;

import com.gamedev.ld28.Assets;
import com.gamedev.ld28.Level;
import com.gamedev.ld28.utils.*;

public class Switch extends Entity {
	
	public Switch(Level level, int x, int y, char pairId) {
		
		super(level, x, y, Constants.NORTH);
		this.walkable = true;
		this.pairId = pairId;
		buildOnOffTiles(Assets.switchsheet);
	}
	
	@Override
	public void revert() {
		return;
	}
	
	public void turnOn() {
		if (this.isOn)
			return;
		this.isOn = true;
		if (this.pairedEntity != null) {
			if (this.pairedEntity instanceof Door) {
				((Door) this.pairedEntity).open();
			}
		}
	}
	
	public void turnOff() {
		this.isOn = false;
		if (this.pairedEntity != null) {
			if (this.pairedEntity instanceof Door) {
				((Door) this.pairedEntity).close();
			}
		}
	}
	
}
