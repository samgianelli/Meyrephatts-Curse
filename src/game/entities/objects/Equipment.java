package game.entities.objects;

import java.io.Serializable;

import game.entities.characters.*;


@SuppressWarnings("serial")
public class Equipment extends Item implements Serializable{
	
	//constructors are the same as item, these are just equippable lol
	private Stats equipmentBonus;

	public Equipment()
	{
		super();
	}
	public Equipment(int rarity, Effect effect, String name, Stats stats)
	{
		super(rarity, effect, name);
		this.equipmentBonus = stats;
	}
	
	public void toggleEquipped(Player p1)
	{
		if(p1.getEquippedItems().contains(this)) //already equipped this item!
		{
			p1.setEffectStats(p1.getEffectStats().minus(this.equipmentBonus)); //lower 
		}
		else //not equipped. Equip it!
		{
			p1.setEffectStats(p1.getEffectStats().plus(this.equipmentBonus)); //increase
		}
		p1.getEquippedItems().add(this);	
		p1.setCurrentStats(); //update the current stats after equipped stats are modified.
		
	}
	
	public Stats getStats()
	{
		return this.equipmentBonus;
	}


}
