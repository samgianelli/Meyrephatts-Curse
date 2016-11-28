package game.entities.characters;

import java.util.ArrayList;
import game.entities.objects.*;

public class Player extends Fighter {
	private ArrayList<Item> inventory;
	private ArrayList<Equipment> equippedItems;
	private double experience;
	
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
	
	public void gainExperience(double experience) {
		this.experience = this.experience + experience;
	}
	
	public void flee() {
		// end battle event / leave room
	}

	public ArrayList<Equipment> getEquippedItems() {
		return this.equippedItems;
	}
	
	public double getExperience()
	{
		return this.experience;
	}
	
	

}
