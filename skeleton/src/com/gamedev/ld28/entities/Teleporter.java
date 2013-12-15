package com.gamedev.ld28.entities;

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
    	
    	if(level.entityAtOldPosition(t.getX(),t.getY()) == null) 
    		e.moveTo(t.getX(),t.getY());
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
