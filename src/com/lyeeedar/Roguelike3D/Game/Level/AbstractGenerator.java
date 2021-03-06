/*******************************************************************************
 * Copyright (c) 2013 Philip Collin.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Philip Collin - initial API and implementation
 ******************************************************************************/
package com.lyeeedar.Roguelike3D.Game.Level;

import java.util.ArrayList;

import com.lyeeedar.Roguelike3D.Game.Level.XML.BiomeReader;
import com.lyeeedar.Utils.Bag;

/**
 * This interface models a level generator that accepts an array of tiles and returns a sorted ArrayList of DungeonRooms
 * @author Philip
 *
 */
public interface AbstractGenerator {
	
	public Bag<DungeonRoom> generate(BiomeReader biome);

}
