package game.entities.characters;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import game.entities.objects.Item;
import game.org.mapInfo.Room;

public class NPC extends Person {
	private String advice;
	private ArrayList<Item> inventory;
	
	public NPC() {
		this.advice = "";
		this.inventory = new ArrayList<Item>();
	}
	
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	
	public String getAdvice() {
		return this.advice;
	}
	
	public void displayAdvice() {
		JOptionPane.showMessageDialog(null, 
				this.advice, 
				super.name + "'s Advice" , 
				JOptionPane.PLAIN_MESSAGE);
	}
	
	public ArrayList<Item> getInventory() {
		return this.inventory;
	}
	
	public void addItems(Item item) {
		this.inventory.add(item);
	}
	
	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = new ArrayList<Item>(inventory);
	}
	
	public void setLocation(Room room) {
		this.location = room;
		room.addNPC(this);
	}
	
}
