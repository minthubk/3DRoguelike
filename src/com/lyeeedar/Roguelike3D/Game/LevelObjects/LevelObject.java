/*******************************************************************************
 * Copyright (c) 2012 Philip Collin.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Philip Collin - initial API and implementation
 ******************************************************************************/
package com.lyeeedar.Roguelike3D.Game.LevelObjects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.math.Vector3;
import com.lyeeedar.Roguelike3D.Game.GameData;
import com.lyeeedar.Roguelike3D.Game.GameObject;
import com.lyeeedar.Roguelike3D.Game.Actor.GameActor;
import com.lyeeedar.Roguelike3D.Game.Level.AbstractObject;
import com.lyeeedar.Roguelike3D.Game.Level.AbstractObject.ObjectType;
import com.lyeeedar.Roguelike3D.Game.Level.AbstractRoom;
import com.lyeeedar.Roguelike3D.Game.Level.Level;
import com.lyeeedar.Roguelike3D.Game.Level.LevelContainer;
import com.lyeeedar.Roguelike3D.Game.Level.XML.MonsterEvolver;
import com.lyeeedar.Roguelike3D.Graphics.Lights.PointLight;
import com.lyeeedar.Roguelike3D.Graphics.Models.Shapes;
import com.lyeeedar.Roguelike3D.Graphics.Models.VisibleObject;
import com.lyeeedar.Roguelike3D.Graphics.ParticleEffects.ParticleEmitter;

public abstract class LevelObject extends GameObject{
	
	public static final String MONSTER_TYPE = "monster_type";
	public static final String LEVEL = "level";
	
	boolean opaque = true;
	boolean solid = false;
	

	public final AbstractObject ao;
	
	public LevelObject(boolean visible, float x, float y, float z, AbstractObject ao)
	{
		super(Shapes.genCuboid(0.1f, 0.1f, 0.1f), Color.WHITE, "blank", x, y, z, ao.modelScale);
		this.visible = visible;
		this.ao = ao;
	}

	public LevelObject(VisibleObject vo, float x, float y, float z, AbstractObject ao) {
		super(vo, x, y, z, ao.modelScale);
		this.ao = ao;
	}
	
	public LevelObject(Mesh mesh, Color colour, String texture, float x, float y, float z, AbstractObject ao) {
		super(mesh, colour, texture, x, y, z, ao.modelScale);
		this.ao = ao;
	}

	public LevelObject(String model, Color colour, String texture, float x,	float y, float z, AbstractObject ao) {
		super(model, colour, texture, x, y, z, ao.modelScale);
		this.ao = ao;
	}
	
	public static LevelObject checkObject(AbstractObject ao, float x, float y, float z, Level level, LevelContainer lc, MonsterEvolver evolver)
	{
		LevelObject lo = null;
		
		if (ao.type == ObjectType.STATIC)
		{
			if (ao.visible)
			{
				String texture = ao.texture;
				Color colour = ao.colour;
				if (ao.modelType.equalsIgnoreCase("file"))
				{
					lo = new Static(ao.modelName, colour, texture, (ao.x)*10, 0, (ao.z)*10, ao);
				}
				else if (ao.modelType.equalsIgnoreCase("cube"))
				{
					Mesh mesh = Shapes.genCuboid(ao.modelDimensions[0], ao.modelDimensions[1], ao.modelDimensions[2]);
					lo = new Static(mesh, colour, texture, (ao.x)*10, 0, (ao.z)*10, ao);
				}
			}
			else
			{
				lo = new Static(false, (ao.x)*10, 0, (ao.z)*10, ao);
				
			}
		}
		else if (ao.type == ObjectType.DOOR_UNLOCKED)
		{
			lo = Door.create(ao, level, x, y, z);
		}
		else if (ao.type == ObjectType.FIRE_CAMP)
		{
			ParticleEmitter p = new ParticleEmitter(x-2.5f, y-2, z-2.5f, 5, 5, 5, 0.75f, 100);
			p.setDecal("data/textures/texf.png", new Vector3(0.0f, 2.0f, 0.0f), 2, Color.YELLOW, Color.RED, 1, 1, false);

			lo = new EmitterObject(Shapes.genCuboid(1, 1, 1), x, y, z, ao, p, false);
			lo.shortDesc = ao.shortDesc;
			lo.longDesc = ao.longDesc;
			
			Color start = Color.YELLOW;
			Color end = Color.RED;
			
			Color lightCol = new Color((start.r+end.r)/2f, (start.g+end.g)/2f, (start.b+end.b)/2f, 1.0f);
			
			PointLight pl = new PointLight(new Vector3(x, 5, z), lightCol, 0.01f, 2.0f);
			
			GameData.lightManager.addStaticLight(pl);
		}
		else if (ao.type == ObjectType.FIRE_TORCH)
		{
			ParticleEmitter p = new ParticleEmitter(x-0.3f, y+1.5f, z-0.3f, 1, 1, 1, 0.75f, 10);
			p.setDecal("data/textures/texf.png", new Vector3(0.0f, 2.0f, 0.0f), 0.5f, Color.YELLOW, Color.RED, 1, 1, false);

			lo = new EmitterObject(Shapes.genCuboid(0.5f, 3, 0.5f), new Color(0.8f, 0.6f, 0.4f, 1.0f), "texw", x, y, z, ao, p, true);
			lo.shortDesc = ao.shortDesc;
			lo.longDesc = ao.longDesc;
			
			Color start = Color.YELLOW;
			Color end = Color.RED;
			
			Color lightCol = new Color((start.r+end.r)/2f, (start.g+end.g)/2f, (start.b+end.b)/2f, 1.0f);
			
			PointLight pl = new PointLight(new Vector3(x, 5, z), lightCol, 0.01f, 2.0f);
			
			GameData.lightManager.addStaticLight(pl);
		}
		else if (ao.type == ObjectType.STAIR_UP)
		{
			String texture = ao.texture;
			Color colour = ao.colour;
			if (ao.modelType.equalsIgnoreCase("model"))
			{
				lo = new Stair(ao.modelName, colour, texture, (ao.x)*10, 0, (ao.z)*10, ao, GameData.createLevelUP(ao.meta.get(LEVEL)));
			}
			else if (ao.modelType.equalsIgnoreCase("cube"))
			{
				Mesh mesh = Shapes.genCuboid(ao.modelDimensions[0], ao.modelDimensions[1], ao.modelDimensions[2]);
				lo = new Stair(mesh, colour, texture, (ao.x)*10, 0, (ao.z)*10, ao, GameData.createLevelUP(ao.meta.get(LEVEL)));
			}
		}
		else if (ao.type == ObjectType.STAIR_DOWN)
		{
			String texture = ao.texture;
			Color colour = ao.colour;
			if (ao.modelType.equalsIgnoreCase("model"))
			{
				lo = new Stair(ao.modelName, colour, texture, (ao.x)*10, 0, (ao.z)*10, ao, GameData.createLevelDOWN(ao.meta.get(LEVEL)));
			}
			else if (ao.modelType.equalsIgnoreCase("cube"))
			{
				Mesh mesh = Shapes.genCuboid(ao.modelDimensions[0], ao.modelDimensions[1], ao.modelDimensions[2]);
				lo = new Stair(mesh, colour, texture, (ao.x)*10, 0, (ao.z)*10, ao, GameData.createLevelDOWN(ao.meta.get(LEVEL)));
			}
		}
		else if (ao.type == ObjectType.PLAYER_PLACER)
		{
			lo = new PlayerPlacer(false, (ao.x)*10, 0, (ao.z)*10, ao);
		}
		else if (ao.type == ObjectType.SPAWNER_0)
		{
			
			GameActor monster = evolver.getMonster(0);
			monster.positionAbsolutely((ao.x)*10, 0, (ao.z)*10);
			level.actors.add(monster);
		}
		else if (ao.type == ObjectType.SPAWNER_1)
		{
			
			GameActor monster = evolver.getMonster(1);
			monster.positionAbsolutely((ao.x)*10, 0, (ao.z)*10);
			level.actors.add(monster);
		}
		else if (ao.type == ObjectType.SPAWNER_2)
		{
			
			GameActor monster = evolver.getMonster(2);
			monster.positionAbsolutely((ao.x)*10, 0, (ao.z)*10);
			level.actors.add(monster);
		}
		else if (ao.type == ObjectType.SPAWNER_3)
		{
			
			GameActor monster = evolver.getMonster(3);
			monster.positionAbsolutely((ao.x)*10, 0, (ao.z)*10);
			level.actors.add(monster);
		}
		else if (ao.type == ObjectType.SPAWNER_4)
		{
			
			GameActor monster = evolver.getMonster(4);
			monster.positionAbsolutely((ao.x)*10, 0, (ao.z)*10);
			level.actors.add(monster);
		}
		else if (ao.type == ObjectType.SPAWNER_5)
		{
			
			GameActor monster = evolver.getMonster(5);
			monster.positionAbsolutely((ao.x)*10, 0, (ao.z)*10);
			level.actors.add(monster);
		}
		else if (ao.type == ObjectType.SPAWNER_6)
		{
			
			GameActor monster = evolver.getMonster(6);
			monster.positionAbsolutely((ao.x)*10, 0, (ao.z)*10);
			level.actors.add(monster);
		}
		else if (ao.type == ObjectType.SPAWNER_7)
		{
			
			GameActor monster = evolver.getMonster(7);
			monster.positionAbsolutely((ao.x)*10, 0, (ao.z)*10);
			level.actors.add(monster);
		}
		else if (ao.type == ObjectType.SPAWNER_8)
		{
			
			GameActor monster = evolver.getMonster(8);
			monster.positionAbsolutely((ao.x)*10, 0, (ao.z)*10);
			level.actors.add(monster);
		}
		else if (ao.type == ObjectType.SPAWNER_9)
		{
			
			GameActor monster = evolver.getMonster(9);
			monster.positionAbsolutely((ao.x)*10, 0, (ao.z)*10);
			level.actors.add(monster);
		}
		
		return lo;
	}

	@Override
	public void update(float delta) {
	}

	@Override
	public void draw(Camera cam) {
	}

	/**
	 * @return the opaque
	 */
	public boolean isOpaque() {
		return opaque;
	}

	/**
	 * @param opaque the opaque to set
	 */
	public void setOpaque(boolean opaque) {
		this.opaque = opaque;
	}

	/**
	 * @return the solid
	 */
	public boolean isSolid() {
		return solid;
	}

	/**
	 * @param solid the solid to set
	 */
	public void setSolid(boolean solid) {
		this.solid = solid;
	}
}
