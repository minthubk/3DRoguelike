package com.lyeeedar.Roguelike3D.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;
import com.lyeeedar.Roguelike3D.Game.GameData.Damage_Type;
import com.lyeeedar.Roguelike3D.Game.GameData.Element;
import com.lyeeedar.Roguelike3D.Game.Item.Equipment_BODY;
import com.lyeeedar.Roguelike3D.Game.Item.Equipment_BOOTS;
import com.lyeeedar.Roguelike3D.Game.Item.Equipment_HAND;
import com.lyeeedar.Roguelike3D.Game.Item.Equipment_HEAD;
import com.lyeeedar.Roguelike3D.Game.Item.Equipment_LEGS;
import com.lyeeedar.Roguelike3D.Game.Item.Item;
import com.lyeeedar.Roguelike3D.Game.Item.Recipe;

public class GameStats {
	
	public static HashMap<String, Item> INVENTORY = new HashMap<String, Item>();
	
	public static int MAX_HEALTH;
	public static int HEALTH;
	public static int WEIGHT;
	public static int STRENGTH;
	public static HashMap<Element, Integer> ELE_DEF = new HashMap<Element, Integer>();
	public static HashMap<Damage_Type, Integer> DAM_DEF = new HashMap<Damage_Type, Integer>();
	public static String FACTION;
	
	public static Equipment_HEAD head;
	public static Equipment_BODY body;
	public static Equipment_LEGS legs;
	public static Equipment_BOOTS boots;
	public static Equipment_HAND l_hand;
	public static Equipment_HAND r_hand;
	
	public static TreeMultimap<Integer, Recipe> recipes = TreeMultimap.create();
	
	public static void addRecipe(Recipe recipe)
	{
		recipes.put(recipe.rarity, recipe);
	}
}