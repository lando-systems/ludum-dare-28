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
        	if(level.entityAtPosition(pairedEntity.getX(),pairedEntity.getY()) == null) 
        		e.moveTo(pairedEntity.getX(),pairedEntity.getY());
        }
}
