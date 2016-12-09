package game.entities.characters;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Stats implements Serializable {
	private Integer level;
	private Integer maxHealth;
	private Integer attack;
	private Integer defense;
	
	public Stats(boolean isBase) {
		if(isBase) //player's base stats
		{
			this.setLevel(1);
			this.setMaxHealth(100);
			this.setAttack(10);
			this.setDefense(10);			
		}
		else //effects or other offsets to health
		{
			this.setLevel(0);
			this.setMaxHealth(0);
			this.setAttack(0);
			this.setDefense(0);
		}
	}
	
	public Stats(int healthBonus, int attackBonus, int defenseBonus)
	{
		this.maxHealth = healthBonus;
		this.attack = attackBonus;
		this.defense = defenseBonus;
	}
	

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Integer getMaxHealth() {
		return this.maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public Integer getAttack() {
		return this.attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public Integer getDefense() {
		return this.defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}		
	
	//adds the attack, defense, and health boosts of this to s1 & returns the result. Used for effects.
	public Stats plus(Stats s1)
	{
		Stats totalStats = new Stats(false);
		totalStats.setMaxHealth(s1.getMaxHealth() + this.getMaxHealth());
		totalStats.setDefense(s1.getDefense() + this.getDefense());
		totalStats.setAttack(s1.getAttack() + this.getAttack());
		return totalStats;
	}
	
	//subtract s1 away from this
	public Stats minus(Stats s1)
	{
		Stats totalStats = new Stats(false);
		totalStats.setMaxHealth(this.getMaxHealth() - s1.getMaxHealth());
		totalStats.setDefense(this.getDefense() - s1.getDefense());
		totalStats.setAttack(this.getAttack() - s1.getAttack());
		return totalStats;	
	}
	
}
