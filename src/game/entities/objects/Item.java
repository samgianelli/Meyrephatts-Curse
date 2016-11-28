package game.entities.objects;

import java.util.ArrayList;
import game.entities.characters.Stats;

public abstract class Item {
	private static ArrayList<Item> very_common_items;
	private static ArrayList<Item> common_items;
	private static ArrayList<Item> normal_items;
	private static ArrayList<Item> rare_items;
	private static ArrayList<Item> very_rare_items;

	
	private int rarity;
	private Effect effect;
	private String name;
	
	Item() { //invalid rarity, negative duration (permanent)
		this(-1, new Effect("The effect of an unknown is not known in the known world.", new Stats(false), -1), "Unknown");
	}
	
	Item(int rarity, Effect effect, String name)
	{
		this.rarity = rarity;
		this.effect = effect;
		this.name = name;
	}
	
	
	public static void createItems() //just added one of each for testing
	{
		Item.very_common_items = new ArrayList<Item>();
		Item.common_items = new ArrayList<Item>();
		Item.normal_items = new ArrayList<Item>();
		Item.rare_items = new ArrayList<Item>();
		Item.very_rare_items = new ArrayList<Item>();
		
		
		Stats rustySporkStats = new Stats(0, 1, 0); //health, attack defense
		Stats basketballShortsStats = new Stats(0, 0, 5);
		Stats realityStats = new Stats(0, 10, 0);
		Stats benchPressStats = new Stats(5, 10, 5);
		Stats harpSealStats = new Stats(100, 100, 100);
		
		Item rustySpork = new Equipment(0, null, "Rusty Spork", rustySporkStats);
		Item basketballShorts = new Equipment(1, null, "Basketball Shorts", basketballShortsStats);
		Item reality = new Equipment(2, null, "Reality", realityStats);
		Item benchPress  = new Equipment(3, null, "Bench Press", benchPressStats);
		Item harpSeal = new Equipment(4, null, "Harp Seal", harpSealStats);
		
		
		//add the very common items
		
		Item.very_common_items.add(rustySpork);
		
		//add the common items
		
		Item.very_common_items.add(basketballShorts);
		
		//add the normal items
		
		Item.common_items.add(reality);
		
		//add the rare items
		
		Item.rare_items.add(benchPress);
		
		//add the very rare items
		Item.very_rare_items.add(harpSeal);
	}
	
	//return the list based on the quality given 
	public static ArrayList<Item> existingItems(int quality)
	{
		switch(quality)
		{
			case 0 : return Item.very_common_items;
			case 1 : return Item.common_items;
			case 2 : return Item.normal_items;
			case 3 : return Item.rare_items;
			case 4 : return Item.very_rare_items;
			default: return null;
		}
	}
	
	public void displayEffect()
	{
		this.effect.displayDescription();
	}
	
	public void setEffect(Effect effect)
	{
		this.effect = effect;
	}
	
	public Effect getEffect()
	{
		return this.effect;
	}
	
	public void setName(String newName)
	{
		this.name = newName;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setRarity() //randomly sets the rarity of an object, but shouldn't reaaaally be used if we use static arrayLists
	{
		this.rarity = Container.returnQuality();
	}
	
	public int getRarity()
	{
		return this.rarity;
	}
	
	
}
