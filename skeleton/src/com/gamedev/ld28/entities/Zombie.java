package com.gamedev.ld28.entities;
import com.gamedev.ld28.utils.*;
import com.gamedev.ld28.Level;
import com.gamedev.ld28.Assets;

public class Zombie extends Entity
{
        private Entity movedEntity;

	public Zombie(Level level, int x, int y, int dir)
        {
		super(level, x, y, dir);
		buildAnim(Assets.zombiesheet);
	}
	
	@Override
	public void takeAction(Entity.ACTIONS action)
    {
        if(action == Entity.ACTIONS.FORWARD || action == Entity.ACTIONS.BACK)
        {
        int hypotheticalX = this.x;
        int hypotheticalY = this.y;

        int positionChange = 1;
        if(this.dir >= Constants.SOUTH)   positionChange *= -1;
        if(action == Entity.ACTIONS.BACK) positionChange *= -1;
        if(this.dir % 2 == 0) hypotheticalY += positionChange;
        else                  hypotheticalX += positionChange;
                  
        movedEntity = level.entityAtPosition(hypotheticalX, hypotheticalY);
        if(movedEntity != null && movedEntity instanceof Barrel)
          movedEntity.bePushed(this.dir);
        else movedEntity = null;
        }

		super.moveAction(action);
	}

	@Override
	public void revert()
	{
		super.revert();
		if(movedEntity != null) movedEntity.revert();
	}
}
