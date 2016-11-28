package game.entities.characters;

public class Fighter extends Person {
	protected int currentHealth;
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
		// wut
		// I suppose this should cause sprite to disappear and generate drop for player
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
	
	public int getHealth() {
		return this.currentHealth;
	}
	
	public boolean getTurn() {
		return this.turn;
	}
	
	public boolean isPlayer()
	{
		return this instanceof Player;
	}
	
	public void setTurn(boolean status)
	{
		this.turn = status;
	}
	
	public boolean isTurn()
	{
		return this.turn;
	}
	
	public void takeAction(Action action) // fill me in later
	{
		
	}

}
