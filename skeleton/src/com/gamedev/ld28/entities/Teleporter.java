package com.gamedev.ld28.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.gamedev.ld28.Assets;
import com.gamedev.ld28.Level;
import com.gamedev.ld28.utils.*;

public class Teleporter extends Entity
{
	public Teleporter(Level level, int x, int y, char id) {
		super(level, x, y, Constants.NORTH);
        this.walkable = true;
		sprite = new Sprite(Assets.teleport);
		buildAnim(Assets.teleport_anim);
        this.pairId = id;
	}

    public void teleport(Entity e)
    {
    	// There can only be one paired teleporter
    	if (this.pairedEntities.size() == 0) {
    		// No paired teleporter
    		return;
    	}
    	Teleporter t = (Teleporter)this.pairedEntities.get(0);
    	Assets.teleportSound.play();
//    	if(level.entityAtOldPosition(t.getX(),t.getY()) == null) 
//    		e.moveTo(t.getX(),t.getY());
    	ArrayList<Entity> entitiesOnOtherTeleporter = level.getEntitesAtPosition(t.getX(), t.getY());
    	Entity otherEntity;
    	// For each
    	for (int i = 0; i < entitiesOnOtherTeleporter.size(); i++) {
    		otherEntity = entitiesOnOtherTeleporter.get(i);
    		if (this != otherEntity &&
    			!otherEntity.walkable) {
    			// Something unwalkable is currently on the other Teleporter
    			return;
    		}	
    	}
    	// Move the entity
    	e.moveTo(t.getX(), t.getY());
    }
        
    @Override
    public void pairWithEntity(Entity entity) {
    	// Teleporters can only pair with 1 other teleporter
    	if (entity instanceof Teleporter) {
    		if (this.pairedEntities.size() == 0) {
    			this.pairedEntities.add(entity);
    		} else {
        		this.pairedEntities.set(0, entity);
    		}
    		if (entity.pairedEntities.size() == 0) {
    			entity.pairedEntities.add(this);
    		} else {
    			entity.pairedEntities.set(0, this);
    		}
    	}
    	
    }
}
