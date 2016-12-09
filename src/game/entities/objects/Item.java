package game.entities.objects;

import java.io.Serializable;
import java.util.ArrayList;

import game.entities.characters.Stats;

@SuppressWarnings("serial")
public abstract class Item implements Serializable {
	private static ArrayList<Item> very_common_items;
	private static ArrayList<Item> common_items;
	private static ArrayList<Item> normal_items;
	private static ArrayList<Item> rare_items;
	private static ArrayList<Item> very_rare_items;

	
	private int rarity;
	private Effect effect;
	private String name;
	private String image;
	
	Item() { //invalid rarity, negative duration (permanent)
		this(-1, new Effect("The effect of an unknown is not known in the known world.", new Stats(false), -1), "Unknown");
		this.image = null;
	}
	
	Item(int rarity, Effect effect, String name)
	{
		this.rarity = rarity;
		this.effect = effect;
		this.name = name;
		this.image = null;
	}
	
	
	public static void createItems() //just added one of each for testing
	{
		Item.very_common_items = new ArrayList<Item>();
		Item.common_items = new ArrayList<Item>();
		Item.normal_items = new ArrayList<Item>();
		Item.rare_items = new ArrayList<Item>();
		Item.very_rare_items = new ArrayList<Item>();
		
		
		Stats rustySwordStats = new Stats(0, 1, 0); //health, attack defense
		Stats ironBootsStats = new Stats(0, 0, 5);
		Stats axeStats = new Stats(0, 10, 0);
		Stats orbOfPowerStats = new Stats(5, 10, 5);
		Stats harpSealStats = new Stats(100, 100, 100);
		
		Item rustySword = new Equipment(0, new Effect("Absolutely terrible. It's a mystery that you would even want to try with this.", new Stats(false), -1), "Rusty Spork", rustySwordStats);
		Item ironBoots = new Equipment(1, new Effect("...They slow you down a lot.", new Stats(false), -1), "Iron Boots", ironBootsStats);
		Item axe = new Equipment(2, new Effect("The most deadly power of all.", new Stats(false), -1), "Axe", axeStats);
		Item orbOfPower  = new Equipment(3, new Effect("It looks valuable!", new Stats(false), -1), "Orb of Power", orbOfPowerStats);
		Item harpSeal = new Equipment(4, new Effect("Lovely!", new Stats(false), -1), "Harp Seal", harpSealStats);
		
		rustySword.image = new String("res/items/sword.png"); 
		ironBoots.image = new String("res/items/boots.png"); 
		axe.image = new String("res/items/axe.png"); 
		orbOfPower.image = new String("res/items/orb.png"); 
		harpSeal.image = new String("res/items/seal.jpg"); 
		
		//add the very common items
		
		Item.very_common_items.add(rustySword);
		
		//add the common items
		
		Item.very_common_items.add(ironBoots);
		
		//add the normal items
		
		Item.common_items.add(axe);
		
		//add the rare items
		
		Item.rare_items.add(orbOfPower);
		
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
	
	public String getImage()
	{
		return this.image;
	}
	
	public void setImage(String image)
	{
		this.image = image;
	}
	
			
	
}
