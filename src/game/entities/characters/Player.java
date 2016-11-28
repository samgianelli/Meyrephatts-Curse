package game.entities.characters;

import java.util.ArrayList;

import game.entities.objects.Equipment;
import game.entities.objects.Item;
import game.entities.objects.Key;
import game.org.mapInfo.Door;
import game.org.mapInfo.Room;

public class Player extends Fighter {
	private ArrayList<Item> inventory;
	private ArrayList<Equipment> equippedItems;
	private ArrayList<Key> keys;
	private Integer experience;
	
	public Player() {
		this.inventory = new ArrayList<Item>();
		this.equippedItems = new ArrayList<Equipment>();
		this.keys = new ArrayList<Key>();
		this.experience = 0;
	}
	
	public Player(String name)
	{
		this();
		this.name = name;
	}
	
	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}
	
	public ArrayList<Item> getInventory() {
		return this.inventory;
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
	
	public void addKey(Key key) {
		this.keys.add(key);
	}
	
	public ArrayList<Key> getKeys() {
		return this.keys;
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
	
	public void move(String move) {
		if(validMove(location.getDoor(move))) {
			if(!location.getDoor(move).getStatus()) {
				location = location.getDoor(move).enterRoom(location);
				System.out.println("You have moved " + move);
			}
			else {
				if(tryKey(location.getDoor(move))) {
					location = location.getDoor(move).enterRoom(location);
					System.out.println("You have moved " + move);
				}
				else {
					System.out.println("This door is locked and you do not have the proper key!");
				}
			}
		}
	}
	
	public void moveOptions() {
		this.location.printMoveOptions();
	}
	
	public boolean validMove(Door move) {
		if (move == null) {
			System.out.println("There is no door in that direction!");
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean tryKey(Door door) {
		for (Key key : keys) {
			if (key.getID() == door.getKeyID()) {
				System.out.println("You unlocked the door with " + key.getName() + "!");
				door.unlockDoor();
				return true;
			}
		}
		return false;
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

}
