package com.gamedev.ld28.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamedev.ld28.Assets;
import com.gamedev.ld28.Level;
import com.gamedev.ld28.utils.*;

public class Door extends Entity {
	
	public Door(Level level, int x, int y, char pairId) {
		
		super(level, x, y, Constants.NORTH);
		this.walkable = false;
		this.pairId = pairId;
		buildOnOffTiles(Assets.doorsheet);
	}
	
	@Override
	public void revert() {
		return;
	}
	
	public void open() {
		// Attempt to open the door.
		// The door only opens if all of its paired switches
		Entity pE;
		boolean allSwitchesAreOn = true;
		for (int i = 0; i < this.pairedEntities.size() && allSwitchesAreOn; i++) {
			pE = this.pairedEntities.get(i);
			if (pE instanceof Switch) {
				if (!pE.isOn) {
					allSwitchesAreOn = false;
				}
			}
		}
		// 
		if (allSwitchesAreOn) {
			this.isOn = true;
			this.walkable = true;
		}
		Assets.doorOpenSound.play();
		
	}
	
	public void close() {
		System.out.println("Door | close() | x='"+this.x+"', y='"+this.y+"'");
		int i;
		ArrayList<Entity> entitiesRightHere;
		// Only close the door if:
		// Nothing is on the door:
		entitiesRightHere = level.getEntitesAtPosition(x, y);
		for (i = 0; i < entitiesRightHere.size(); i++) {
			if (entitiesRightHere.get(i) != this) {
				// We've found something blocking the door.
				return;
			}
		}
		
    	// At least one of the paired switches is off.
    	Entity pE;
		for (i = 0; i < this.pairedEntities.size(); i++) {
			pE = this.pairedEntities.get(i);
			if (pE instanceof Switch) {
				if (!pE.isOn) {
					if (this.isOn) Assets.doorCloseSound.play();
					this.isOn = false;
					this.walkable = false;
					
					return;
				}
			}
		}
		
	}
	
}
