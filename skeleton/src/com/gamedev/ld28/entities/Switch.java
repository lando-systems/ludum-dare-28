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
		// For each paired entity:
		Entity pE;
		for (int i = 0; i < this.pairedEntities.size(); i++) {
			pE = this.pairedEntities.get(i);
			// Is it a door?
			if (pE instanceof Door) {
				// Try to open the door.
				((Door) pE).open();
			}
			
		}
	}
	
	public void turnOff() {
		this.isOn = false;
		// For each paired entity:
		Entity pE;
		for (int i = 0; i < this.pairedEntities.size(); i++) {
			pE = this.pairedEntities.get(i);
			// Is it a door?
			if (pE instanceof Door) {
				// Try to close the door.
				((Door) pE).close();
			}
			
		}
		
		
	}
	
}
