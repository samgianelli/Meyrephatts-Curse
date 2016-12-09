package game.entities.characters;

import game.MainApp;
import game.org.mapInfo.*;

public abstract class Fighter extends Person {
	protected Integer currentHealth;
	protected boolean turn;
	protected boolean alive;
	protected Stats baseStats;
	protected Stats effectStats;
	protected Stats currentStats; //baseStats + effectStats
	
	public Fighter() {
		this.turn = false;
		this.alive = true;
		this.baseStats = new Stats(true);
		this.effectStats = new Stats(false);
		this.currentStats = baseStats.plus(effectStats); //should not have an effect immediately, but left this way in case we add one.
		this.currentHealth = this.baseStats.getMaxHealth(); //set to high at the beginning of the game!
	}
	
	public void takeDamage(int damage) {
		this.currentHealth = this.currentHealth - damage;
	}
	
	public void dealDamage(int damage, Fighter target) {
		target.takeDamage(damage);
	}
	
	public boolean isAlive() {
		if (this.currentHealth <= 0) {
			this.alive = false;
			return false;
		}
		else {
			this.alive = true;
			return true;
		}
	}
	
	public void die() {
		MainApp.showDeathScreen();
	}
	
	public Stats getBaseStats() {
		return this.baseStats;
	}
	
	public void setBaseStats(Stats newStats) {
		this.baseStats = newStats;
	}
	
	public Stats getEffectStats() {
		return this.effectStats;
	}
	
	public void setEffectStats(Stats newStats) {
		this.effectStats = newStats;
	}
	
	public Stats getCurrentStats(){
		return this.effectStats.plus(this.baseStats);
	}
	
	public void setCurrentStats(){
		this.currentStats = this.effectStats.plus(this.baseStats);
	}
	
	public void setHealth(int health) {
		this.currentHealth = health;
	}
	
	public Integer getHealth() {
		return this.currentHealth;
	}
	
	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	
	public boolean getTurn() {
		return this.turn;
	}
	
	public abstract void setLocation(Room room);

}
