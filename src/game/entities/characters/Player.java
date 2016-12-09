package game.entities.characters;

import java.io.Serializable;
import java.util.ArrayList;

import game.GameConstants;
import game.entities.objects.Equipment;
import game.entities.objects.Item;
import game.org.mapInfo.Door;
import game.org.mapInfo.Room;

@SuppressWarnings("serial")
public class Player extends Fighter implements Serializable{
	private ArrayList<Item> inventory;
	private ArrayList<Equipment> equippedItems;
	private Integer experience;
	private Integer expToLevel;
	
	public Player() {
		this.inventory = new ArrayList<Item>();
		this.equippedItems = new ArrayList<Equipment>();
		this.experience = 0;
	}
	
	public Player(String name)
	{
		this();
		this.name = name;
	}
	

	public void useItem(Item item)
	{
		if(item instanceof Equipment)
		{
			this.setEffectStats(((Equipment) item).getStats().plus(this.getEffectStats()));
			this.setCurrentStats(); //update current stats also.
		}
		
	}
			
	
	
	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}
	
	public ArrayList<Item> getInventory() {
		return this.inventory;
	}
	
	public void setExpToLevel(Integer i) {
		System.out.println("Warning! This should never be called");
		expToLevel = i;
	}
	
	public Integer getExpToLevel() {
		return expToLevel;
	}
	
	public void dropItem(int index) {
		this.inventory.remove(this.inventory.get(index));
	}
	
	public void addItem(Item item) {
		inventory.add(item);
	}
	
	public void equipItem(Equipment item) {
		this.equippedItems.add(item);
		// apply equipped item's effects to player
	}
	
	public void unEquipItem(Equipment item) {
		this.equippedItems.remove(item);
		// remove status bonus from equipped item
		this.inventory.add(item);
	}
	
	public void gainExperience(int experience) {
		this.experience = this.experience + experience;
	}
	
	public void flee() {
		// end battle event / leave room
	}

	public ArrayList<Equipment> getEquippedItems() {
		return this.equippedItems;
	}
	
	public Integer getExperience()
	{
		return this.experience;
	}
	
	public void setLocation(Room room) {
		this.location = room;
	}
	
	//used for text based testing
	public void move(String move) {
		if(validMove(location.getDoor(move))) {
			if(!location.getDoor(move).getStatus()) {
				location = location.getDoor(move).enterRoom(location);
				System.out.println("You have moved " + move);
			}
			else {
				location = location.getDoor(move).enterRoom(location);
				System.out.println("You have moved " + move);

			}
		}
	}
	
	//used for testing
	public void moveOptions() {
		this.location.printMoveOptions();
	}
	
	//used for testing
	public boolean validMove(Door move) {
		if (move == null) {
			System.out.println("There is no door in that direction!");
			return false;
		}
		else {
			return true;
		}
	}
	

	
	public void checkEnemies() {
		if (location.getEnemies().size() != 0) {
			System.out.println("This room contains a " + location.getEnemies().get(0).getName() + "!");
			Action.beginBattle(location.getEnemies().get(0));
		}
	}
	
	public static void displayPlayerStats(Player p1)
	{
		System.out.println("Displaying the stats for player: " + p1.getName());
		System.out.println("---------------------------------");
		System.out.print("Current Level: ");
		System.out.println(p1.getCurrentStats().getLevel());
		System.out.print("Current Attack: ");
		System.out.println(p1.getCurrentStats().getAttack());
		System.out.print("Current Defense: ");
		System.out.println(p1.getCurrentStats().getDefense());
		System.out.print("Current Max Health: ");
		System.out.println(p1.getCurrentStats().getMaxHealth());
		System.out.println("Current experience at: " + p1.getExperience());
		System.out.println("Next level is at: ");
		System.out.println("---------------------------------");		
	}
	
	public void interact(Item item)
	{
		if(item instanceof Equipment)
		{
			((Equipment) item).toggleEquipped(this);
		}
		//add more items here!
	}

	
	//battle against the enemy. Add more functionality here later!
	public void interact(Enemy enemy)
	{
		while(enemy.getHealth() > 0 && this.getHealth() > 0)
		{
			this.dealDamage(10, enemy);
			enemy.dealDamage(2, this);			
		}
		if(this.getHealth() <= 0)
		{
			System.out.println("Oh dear! You are dead!");
			//reload last save file.
		}
		else
		{
			System.out.println("Player " + this.getName() + " defeated enemy " + enemy.getName());
			enemy.awardDrop(this); //give the enemy drops to the player.
		}
	}

	
	//player attacks all enemies in the room
	public void attack() //hit everything in the player's room
	{
		ArrayList<Enemy> enemyList = this.getLocation().getEnemies();
		for(Enemy x : enemyList)
		{
			if(x.getHealth() > 0)
			{
				x.setHealth(x.getHealth() - this.getCurrentStats().getAttack() * 10);
			}
		}
	}
	
	//player takes damage from  all enemies in the room
	public void receiveDamage()
	{
		ArrayList<Enemy> enemyList = this.getLocation().getEnemies();
		for(Enemy x : enemyList)
		{
			if(x.getHealth() > 0)
			{
				this.setHealth(this.getHealth() - x.getCurrentStats().getAttack());
			}
			if(this.getHealth() <= 0)
			{
				this.die(); //end of game!
			}
		}
	}
	
	//gets the experience value of the next level for the player
	public int playerPlusOne(int level)
	{
		if(level <= 1)
		{
			return GameConstants.Level2;
		}
		else if(level == 2)
		{
			return GameConstants.Level3;			
		}
		else
		{
			return 1000;
		}
	}			
	
	//check to see if the player leveled up from the last battle.
	public void checkLeveled()
	{
		
		if(this.getExperience() >= GameConstants.Level5)
		{
			this.getBaseStats().setLevel(5);
			this.getCurrentStats().setLevel(5);//update current stats too			
		}
		else if(this.getExperience() >= GameConstants.Level4)
		{
			this.getBaseStats().setLevel(4);
			this.getCurrentStats().setLevel(4);//update current stats too			
		}		
		else if(this.getExperience() >= GameConstants.Level3)
		{
			this.getBaseStats().setLevel(3);
			this.getCurrentStats().setLevel(3);//update current stats too			
		}		
		else if(this.getExperience() >= GameConstants.Level2)
		{
			this.getBaseStats().setLevel(2);
			this.getCurrentStats().setLevel(2);//update current stats too			
		}			
		else
		{
			this.getBaseStats().setLevel(1);
			this.getCurrentStats().setLevel(1);//update current stats too					
		}
		

	}
	

	
}

