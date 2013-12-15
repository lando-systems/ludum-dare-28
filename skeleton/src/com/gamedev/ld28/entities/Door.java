package com.gamedev.ld28.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamedev.ld28.Assets;
import com.gamedev.ld28.Level;
import com.gamedev.ld28.utils.*;

public class Door extends Entity {
	
	private static float ANIMATION_DURATION = 0.5f;
	private static int FRAME_COUNT = 4;
	
	private boolean isOpening = false;
	private boolean isClosing = false;
	
	private float doorAnimationTimer = 0f;
	
	
	
	public Door(Level level, int x, int y, char pairId) {
		
		super(level, x, y, Constants.NORTH);
		this.walkable = false;
		this.pairId = pairId;
		buildDoorTiles(Assets.doorsheet);
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
			if (!this.isOn) {
				this.isOpening = true;
				Assets.doorOpenSound.play();
			}
			this.isOn = true;
			this.walkable = true;
		}
		
	}
	
	public void close() {
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
					if (this.isOn) {
						this.isClosing = true;
						Assets.doorCloseSound.play();
					}
					this.isOn = false;
					this.walkable = false;
					
					return;
				}
			}
		}
		
	}
	
	private void buildDoorTiles(Texture textureSheet) {
		super.animTiles = new Sprite[1 * Door.FRAME_COUNT];
		for (int y = 0; y < Door.FRAME_COUNT; y++) {
			super.animTiles[y] = new Sprite(
					textureSheet, 0, y * Entity.TILE_SIZE, Entity.TILE_SIZE, Entity.TILE_SIZE);
		}
	}
	
	public void render(float dt) {

		Sprite tile;
		int frame;
		boolean animationComplete = false;
		
		
		// Check for Closed/Open
		if (this.isOn && !this.isOpening) {
			tile = this.animTiles[3];
		} else if (!this.isOn && !this.isClosing) {
			tile = this.animTiles[0];
		} else {
			// doorAnimationTimer MOD number of frames
			this.doorAnimationTimer += (Door.FRAME_COUNT / Door.ANIMATION_DURATION) * dt;
			this.doorAnimationTimer = Math.min(this.doorAnimationTimer, Door.FRAME_COUNT - 1);
			frame = (int)this.doorAnimationTimer;
			// Final frame?
			if (frame == Door.FRAME_COUNT - 1) {
				animationComplete = true;
			}
			if (this.isClosing) {
				// Reverse the frame (going backwards)
				frame = (Door.FRAME_COUNT - 1) - frame;
			}
			// Set the tile
			tile = this.animTiles[frame];
			// Was this the final frame of an animation?
			if (animationComplete) {
				if (this.isOpening) {
					this.isOn = true;
					this.isOpening = false;
				}
				if (this.isClosing) {
					this.isOn = false;
					this.isClosing = false;
				}
				// Zero the doorAnimationTimer
				this.doorAnimationTimer = 0f;
			}	
		}
		
		Utils.setScreenPosition(tile, this.x, this.y, 64, 64);
		tile.draw(Assets.batch);

	}
	
}
