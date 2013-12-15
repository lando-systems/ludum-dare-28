package com.gamedev.ld28;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.gamedev.ld28.utils.*;
import com.gamedev.ld28.entities.*;
import com.gamedev.ld28.entities.Entity.ACTIONS;

public class Level {
	private String helpText = "WASD moves you and zombies. Escape will reset the level ";
	private String[] mapData = new String[] {
			// Level 1
			          "x x x x x x x x x x x x x x " 
					+ "x znx x - - - s - x x wsx x "
					+ "x - x - zs- o - - x x - x x "
					+ "x - ?a- - - - ze- x x - x x "
					+ "x - - o - zw- - - x x - x x "
					+ "x x zs- x - - ?a- x x - x x "
					+ "x x x x x x x x x x x - x x "
					+ "x q - - - - - - x x x - x x "
					+ "x - - - - - - - - - - - x x "
					+ "x x x x x x x x x x x x x x ",

			// Level 2
			          "x x x x x x x x x x x x x x " 
					+ "x znx x - - - - - x x q x x "
					+ "x - x - zs- o - - x x - x x "
					+ "x - - - - - - ze- x x - x x "
					+ "x - - o - zw- - - x x - x x "
					+ "x x zs- x - - - - x x - x x "
					+ "x x x x x x x x x x x zex x "
					+ "x ws- - - - - - x x x - x x "
					+ "x - - - - - - - - - - - x x "
					+ "x x x x x x x x x x x x x x ",

			// Level 3
			          "x x x x x x x x x x x x x x " 
					+ "x znx x - - - - - x x wsx x "
					+ "x - x - zs- o - - x x - x x "
					+ "x - - - - - - ze- x x - x x "
					+ "x - - o - zw- - - x x - x x "
					+ "x x zs- x - - - - x x - x x "
					+ "x x x x x x x x x x x - x x "
					+ "x q x x - x x - x x x - x x "
					+ "x - - zw- - - - zw- - - x x "
					+ "x x x x x x x x x x x x x x ",

			// Level 4
			          "x x x x x x x x x x x x x x " 
					+ "x q x x - - - - - x x - x x "
					+ "x - x - zs- o - - x x - x x "
					+ "x - - - - - - ze- x x - x x "
					+ "x - - o - zw- - - o - - - x "
					+ "x x zs- x - - - - x x - x x "
					+ "x x x x x x x x x x x - x x "
					+ "x wnx x - x x - x x x - x x "
					+ "x - - zw- - - - zw- - - x x "
					+ "x x x x x x x x x x x x x x ",

			// Level 5
			          "x x x x x x x x x x x x x x " 
					+ "x wsx x - - - - - x x x x x "
					+ "x - x - zs- o - - x x x x x "
					+ "x - x - - - - ze- x x x x x "
					+ "x - - o zw- - - - o - - x x "
					+ "x x x - x - - - - x x - x x "
					+ "x x x x x x x x x x x - x x "
					+ "x q x x - x x - x x x - x x "
					+ "x - - zw- - - - zw- - - x x "
					+ "x x x x x x x x x x x x x x ",

			// Level 6
			          "x x x x x x x x x x x x x x " 
					+ "x q ?ax - - - - - x x x x x "
					+ "x - x x zs- o - - x x x x x "
					+ "x - x - - - - ze- x x x x x "
					+ "x - o - zw- - - - o - - x x "
					+ "x x x - x - - - - x x - x x "
					+ "x - - - - - - - - - - - - x "
					+ "x we- - - - - - - - - - - x "
					+ "x - - zw- - - - zw- - ?a- x "
					+ "x x x x x x x x x x x x x x ",

			// Level 7
			          "x x x x x x x x x x x x x x " 
					+ "x x x x x x x x x x x x x x "
					+ "x x x x x x x x x x x x x x "
					+ "x x x x x x x x x x x x x x "
					+ "x x x x x - znx x x x x x x "
					+ "x x x x x wnzsq-x x x x x x "
					+ "x x x x x x x x x x x x x x "
					+ "x x x x x x x x x x x x x x "
					+ "x x x x x x x x x x x x x x "
					+ "x x x x x x x x x x x x x x ",

			// Level 8
			          "x x x x x x x x x x x x x x " 
					+ "x x x x x x x x x x x x x x "
					+ "x x x q x x x x x x x x x x "
					+ "x x x dax x x x x x x x x x "
					+ "x x - - x - sax x x x x x x "
					+ "x x wn- x zn- x x x x x x x "
					+ "x x x x x x x x x x x x x x "
					+ "x x x x x x x x x x x x x x "
					+ "x x x x x x x x x x x x x x "
					+ "x x x x x x x x x x x x x x ",
					
					// Level 9
			          "x x x x x x x x x x x x x x " 
					+ "x x x x x x x x x x x x x x "
					+ "x x x q x x x x x x x x x x "
					+ "x x x dax x x x x x x x x x "
					+ "x x - - x - - - x x x x x x "
					+ "x x wn- x zn- - x x x x x x "
					+ "x x x x x - o sax x x x x x "
					+ "x x x x x x x x x x x x x x "
					+ "x x x x x x x x x x x x x x "
					+ "x x x x x x x x x x x x x x ",
					
					// Level 10
			          "x x x x x x x x x x x x x x " 
					+ "x x x x x x x x x x x x x x "
					+ "x x x q x x x x x x x x x x "
					+ "x x x dax x x x x x x x x x "
					+ "x x - - x - - r x x x x x x "
					+ "x x wn- x zn- - x x x x x x "
					+ "x x x x x - - sax x x x x x "
					+ "x x x x x x x x x x x x x x "
					+ "x x x x x x x x x x x x x x "
					+ "x x x x x x x x x x x x x x ",
					
					// Level 11
			          "x x x x x x x x x x x x x x " 
					+ "x ws- - - - - - - - - - x x "
					+ "x x x x x x x x x x x znx x "
					+ "x x x x x x x x x x x znx x "
					+ "x x x x x x x x x x x - x x "
					+ "x x x x x x x x x x x - x x "
					+ "x x x x x x x x x x x - x x "
					+ "x x x x - x x - x r x - x x "
					+ "x q - - - - - - - - - - x x "
					+ "x x x x x x x x x x x x x x "

	};

	private String[] mapDesc = new String[] {
	/* 1 */"Get to the next level ",
	/* 2 */"Oh noes there is a zombie in your way ",
	/* 3 */"Now there are two zombies in your way ",
	/* 4 */"You are not strong enough to move those barrels on your own ",
	/* 5 */"This one you can get stuck on ",
	/* 6 */"How did he get there ",
	/* 7 */"Close quarters ",
	/* 8 */"What does this do ",
	/* 9 */"If you liked it then you should have put a barrel on it ",
	/*10 */"Now we are out of sync ",
	/*11 */"Get them back in sync "};

	protected Wizard player;
	protected Exit exit;
	protected ArrayList<Entity> movingObjects;

	protected ArrayList<Entity> entities;
	protected int width;
	protected int height;
	protected int levelState; // 0 = begin, 1 = playing, 2 = end
	private int currentLevel = 8;

	public Level(int level) {

		
//		 // DEBUG LEVEL
//		 mapData[0] =
//		 "x x x x x x x x x x x x x x "+
//		 "x - - - - - - - - - - - - x "+
//		 "x - - - - - - - - - - - - x "+
//		 "x - - - - - - - - - - - - x "+
//		 "x - - - - - - wn- - - - - x "+
//		 "x - - - - - - - - - - - - x "+
//		 "x - - - - - - - - - - - - x "+
//		 "x - - - - - - - - - - - - x "+
//		 "x - - - - - - - - - - - - x "+
//		 "x x x x x x x x x x x x x x ";
//		 
//		 // DEBUG LEVEL
//		 mapData[0] =
//				 "x x x x x x x x x x x x x x "+
//				 "x - - - - - - - - - - - - x "+
//				 "x - - sa- - - - - - - - - x "+
//				 "x - - zn- - - wn- - - - - x "+
//				 "x - - - - - - - - - - - - x "+
//				 "x - - - - - - - - - - - - x "+
//				 "x - - da- da- - - - - - - x "+
//				 "x - - zn- - - zn- - - - - x "+
//				 "x - - - - zn- - - - - - - x "+
//				 "x x x x x x x x x x x x x x ";
		 
		
		

		// currentLevel = level;
		resetLevel();
	}

	public void resetLevel() {
		entities = new ArrayList<Entity>();
		movingObjects = new ArrayList<Entity>();
		levelState = 1; // should be 0, but is just auto 1 for now until we have
						// rendering code for beginning and end

		this.parseMapDataIntoEntities(mapData[currentLevel]);
	}

	private void parseMapDataIntoEntities(String mapString) {
		int height = 10;
		int width = 14;
		int dir = 0;
		char arg = ' ';

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// Find argument
				arg = mapString.charAt(((y * width) + x) * 2 + 1);
				switch (arg) {
				case 'n':
					dir = Constants.NORTH;
					break;
				case 's':
					dir = Constants.SOUTH;
					break;
				case 'e':
					dir = Constants.EAST;
					break;
				case 'w':
					dir = Constants.WEST;
					break;
				default:
					break;
				}

				int yPos = (height - 1) - y;

				// Find type
				switch (mapString.charAt(((y * width) + x) * 2)) {
				case '-':
					break;
				case 'x':
					entities.add(new Stone(this, x, yPos));
					break;
				case 'z':
					Zombie z = new Zombie(this, x, yPos, dir);
					movingObjects.add(z);
					entities.add(z);
					break;
				case 'o':
					Barrel b = new Barrel(this, x, yPos);
					movingObjects.add(b);
					entities.add(b);
					break;
				case 'w':
					player = new Wizard(this, x, yPos, dir);
					entities.add(player);
					break;
				case '?':
					entities.add(new Teleporter(this, x, yPos, arg));
					break;
				case Constants.SWITCH:
					entities.add(new Switch(this, x, yPos, arg));
					break;
				case Constants.DOOR:
					entities.add(new Door(this, x, yPos, arg));
					break;
				case 'r':
					entities.add(new Rotater(this, x, yPos));
					break;
				case 'q':
					exit = new Exit(this, x, yPos);
					entities.add(exit);
					break;
				default:
					break;
				}
			}
		}

		this.pairEntities();
	}

	private void pairEntities() {
		int i, j;
		Entity eA, eB;
		iLoop: for (i = 0; i < entities.size(); i++) {
			eA = entities.get(i);
			if (eA.getPairId() == '0')
				continue iLoop;
			jLoop: for (j = i + 1; j < entities.size(); j++) {
				eB = entities.get(j);
				if (eB.getPairId() == '0')
					continue jLoop;
				// Both entities have a pairId
				if (eA.getPairId() == eB.getPairId()) {
					// Match!
					eA.pairWithEntity(eB);
				}
			}
		}
	}

	public void takeAction(Entity.ACTIONS action) {
		if (action != null) {
			for (Entity entity : entities)
				entity.saveState();
			for (Entity entity : entities)
				entity.takeAction(action);
			this.validateMap();
			if (action == Entity.ACTIONS.FORWARD
					|| action == Entity.ACTIONS.BACK)
				this.performInteractions();
		}
	}

	public Entity entityAtOldPosition(int x, int y) {
		for (int i = 0; i < entities.size(); i++)
			if (entities.get(i).getOldX() == x
					&& entities.get(i).getOldY() == y
					&& (entities.get(i) instanceof Zombie || 
						entities.get(i) instanceof Barrel || 
						entities.get(i) instanceof Wizard))
				return entities.get(i);
		return null;
	}
	
	public Entity entityAtPosition(int x, int y) {
		Entity e;
		for (int i = 0; i < entities.size(); i++)
			
			if (entities.get(i).getOldX() == x
					&& entities.get(i).getOldY() == y
					&& (entities.get(i) instanceof Zombie || 
						entities.get(i) instanceof Barrel || 
						entities.get(i) instanceof Wizard))
				return entities.get(i);
		return null;
	}

	public void validateMap()
	{
		int i, j;
		Entity eA, eB;

		// find/correct immediate conflicts
		boolean isValid = false;
		while (!isValid) {
			isValid = true;
			for (i = 0; i < this.entities.size(); i++) 
			{
				eA = this.entities.get(i);
				for (j = i + 1; j < this.entities.size(); j++)
				{
					eB = this.entities.get(j);
					if ((eA.overlaps(eB) || eA.passedThrough(eB))
							&& !(eA.walkable || eB.walkable)) 
					{
						eA.conflictNoted = true;
						eB.conflictNoted = true;
						isValid = false;
					}
				}
				if (!isValid) 
				{
					for (Entity entity : entities) 
					{
						if (entity.conflictNoted) 
						{
							entity.revert();
							entity.conflictNoted = false;
						}
					}
					continue;
				}
			}
		}
	}

	public void performInteractions()
	{
		boolean switchOverlap;
		// perform any inter-entity actions
		Entity eA, eB;
		for (int i = 0; i < this.entities.size(); i++)
		{
			eA = this.entities.get(i);
			switchOverlap = false;
			for(int j = 0; j < this.entities.size(); j++)
			{
				eB = this.entities.get(j);
				if(eA != eB && (eA.overlaps(eB) || eA.passedThrough(eB)) && !eB.conflictNoted)
				{
					if(eA instanceof Teleporter)
					{
						if (eB.getX() != eB.getOldX() || eB.getY() != eB.getOldY())
						{
							((Teleporter) eA).teleport(eB);
							eB.conflictNoted = true;
						}
					}
					if (eA instanceof Rotater && (eB.getX() != eB.getOldX() || eB.getY() != eB.getOldY())) {
						//TODO: add sounds here?
						eB.takeAction(ACTIONS.TURN_CW);
					}
					if (eA instanceof Exit && eB instanceof Wizard) {
						currentLevel = Math.min(currentLevel + 1,
								mapData.length - 1);
						resetLevel();
						return;
					}
				}

				// Tests for on/off objects
				if (eA != eB && eA.overlaps(eB)) {
					//
					if (eA instanceof Switch && !eB.walkable) {
						((Switch) eA).turnOn();
						switchOverlap = true;
					}
				}

			}

			if (eA instanceof Switch && !switchOverlap) {
				((Switch) eA).turnOff();
			}

		}
		for (Entity entity : entities)
			entity.conflictNoted = false;
	}

	public void render(float dt) {
		switch (levelState) {
		case 0:// level begin
			break;
		case 1:// level playing
			for (Entity entity : entities)
				entity.render(dt);
			break;
		case 2:// level end
			break;
		}

		for (Entity entity : movingObjects)
			entity.render(0);
		player.render(0); // I need to be here so i draw on top of things.

		Utils.drawShadowText(Assets.batch, "Level " + (currentLevel + 1),
				Config.screenHalfWidth - 40, Config.screenHalfHeight - 70, 30,
				30, Color.GRAY, Utils.EStringJustify.RIGHT);
		Utils.drawShadowText(Assets.batch, "Lives 1",
				Config.screenHalfWidth - 40, Config.screenHalfHeight - 90,
				Color.GRAY, Utils.EStringJustify.RIGHT);
		Utils.drawShadowText(Assets.batch, mapDesc[currentLevel],
				Config.screenHalfWidth - 40, Config.screenHalfHeight - 120, 20,
				20, Color.WHITE, Utils.EStringJustify.RIGHT);

		Utils.drawShadowText(Assets.batch, helpText,
				Config.screenHalfWidth - 40, 0, 20, 20, Color.YELLOW,
				Utils.EStringJustify.RIGHT);
	}

}
