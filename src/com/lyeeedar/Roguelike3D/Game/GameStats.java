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
package com.lyeeedar.Roguelike3D.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeMap;

import com.lyeeedar.Roguelike3D.Game.GameData.Damage_Type;
import com.lyeeedar.Roguelike3D.Game.GameData.Element;
import com.lyeeedar.Roguelike3D.Game.Actor.Player;
import com.lyeeedar.Roguelike3D.Game.Item.Component;
import com.lyeeedar.Roguelike3D.Game.Item.Equipment_BODY;
import com.lyeeedar.Roguelike3D.Game.Item.Equipment_BOOTS;
import com.lyeeedar.Roguelike3D.Game.Item.Equipment_HAND;
import com.lyeeedar.Roguelike3D.Game.Item.Equipment_HEAD;
import com.lyeeedar.Roguelike3D.Game.Item.Equipment_LEGS;
import com.lyeeedar.Roguelike3D.Game.Item.Equippable;
import com.lyeeedar.Roguelike3D.Game.Item.Item;
import com.lyeeedar.Roguelike3D.Game.Item.Item.Item_Type;
import com.lyeeedar.Roguelike3D.Game.Item.Recipe;

public class GameStats {
	
	public static int MAX_HEALTH;
	public static int HEALTH;
	public static int WEIGHT;
	public static int STRENGTH;
	public static HashMap<Element, Integer> ELE_DEF = new HashMap<Element, Integer>();
	public static HashMap<Damage_Type, Integer> DAM_DEF = new HashMap<Damage_Type, Integer>();
	public static ArrayList<String> FACTIONS;
	
	public enum Equipment_Slot {
		HEAD,
		BODY,
		LEGS,
		BOOTS,
		L_HAND,
		R_HAND
	}
	
	public static Equipment_HEAD head;
	public static Equipment_BODY body;
	public static Equipment_LEGS legs;
	public static Equipment_BOOTS boots;
	public static Equipment_HAND l_hand;
	public static Equipment_HAND r_hand;
	
//	public static TreeMultimap<Integer, Recipe> recipes = TreeMultimap.create();
//	public static TreeMultimap<Integer, Component> components = TreeMultimap.create();
//	public static TreeMultimap<Item_Type, Equippable> carryEquipment = TreeMultimap.create();
//	public static TreeMultimap<Item_Type, Equippable> baseEquipment = TreeMultimap.create();
//	
	public static void init()
	{
		MAX_HEALTH = HEALTH = 100;
		WEIGHT = 1;
		STRENGTH = 1;
		ELE_DEF = GameData.getElementMap();
		DAM_DEF = GameData.getDamageMap();
		FACTIONS = new ArrayList<String>();
		FACTIONS.add("PLAYER");
	}
	
	public static void setPlayerStats(Player player)
	{
		player.setStats(HEALTH, WEIGHT, STRENGTH, ELE_DEF, DAM_DEF, FACTIONS);
	}
	
	public static List<Recipe> getRecipes(int rarity)
	{
		return null;
	}
	
	public static List<Recipe> getAllRecipes()
	{
		return null;
	}
	
	public static List<Component> getAllComponents()
	{
		return null;
	}
	
	public static void addRecipe(Recipe recipe)
	{
		
	}
	
	public static void addComponent(Component c)
	{

	}
	
	public static void removeComponent(Component c, int amount)
	{

	}
	
	public static List<Equippable> getCarryEquipment (Item_Type type)
	{
		return null;
	}
	
	public static void addEquipmentCarry(Equippable e)
	{

	}
	
	public static void addEquipmentBase(Equippable e)
	{

	}
	
	public static void save(SaveGame save)
	{
		//save.setStats(MAX_HEALTH, HEALTH, WEIGHT, STRENGTH, ELE_DEF, DAM_DEF, FACTIONS, head, body, legs, boots, l_hand, r_hand, recipes, components, carryEquipment, baseEquipment);
	}
	
	public static void load(SaveGame save)
	{
		MAX_HEALTH = save.MAX_HEALTH;
		HEALTH = save.HEALTH;
		WEIGHT = save.WEIGHT;
		STRENGTH = save.STRENGTH;
		ELE_DEF = save.ELE_DEF;
		DAM_DEF = save.DAM_DEF;
		FACTIONS = save.FACTIONS;
		head = save.head;
		body = save.body;
		legs = save.legs;
		boots = save.boots;
		l_hand = save.l_hand;
		r_hand = save.r_hand;

	}
}
