package game.entities.objects;

import game.entities.characters.Stats;

public class Effect {
	private String description; //print to screen if the item is examined
	private Stats skill; //skill to be boosted or lowered
	int duration;
	
	public Effect(String description, Stats skills, int duration) //called by other effect constructors
	{
		this.description = description;
		this.skill = skills;
		this.duration = duration;
	}
	
	public void setDescription(String desc)
	{
		this.description = desc;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public void displayDescription()
	{
		System.out.println(this.description); //show the description on the screen
	}
			
	public Stats getSkill() //attack, defense, or health
	{
		return this.skill;
	}
	
	public void setSkill(Stats skill)
	{
		this.skill = skill;
	}
	
	public int getDuration()
	{
		return this.duration;
	}
	
	public void setDuration(int duration)
	{
		this.duration = duration;
	}
	
	public boolean isPersistent()
	{
		if(this.duration > 0)
		{
			return true; 
		}
		return false;
	}
	
	
}
