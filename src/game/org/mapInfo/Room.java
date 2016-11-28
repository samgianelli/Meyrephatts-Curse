package game.org.mapInfo;

import java.util.ArrayList;

import game.entities.objects.*;
import game.entities.characters.*;

import java.io.Serializable;

public class Room implements Serializable {
	
	//private ArryaList<Container> loot;
	private ArrayList<Enemy> enemies;
	private ArrayList<NPC> NPCs;
	private Container container;
	private Door north;
	private Door east;
	private Door south;
	private Door west;
	
	public Room() {
		//this.loot = new ArrayList<Container>();
		this.enemies = new ArrayList<Enemy>();
		this.NPCs = new ArrayList<NPC>();
		this.container = null;
		this.north = null;
		this.east = null;
		this.south = null;
		this.west = null;
	}
	
	public Room(/*ArrayList<Container> loot,*/ ArrayList<Enemy> enemies,
			Door north, Door east, Door south, Door west) {
		//this.loot = loot;
		this.enemies = enemies;
		this.north = north;
		this.east = east;
		this.south = south;
		this.west = west;
	}
	
	public void setDoors(Door north, Door east, Door south, Door west) {
		this.north = north;
		this.east = east;
		this.south = south;
		this.west = west;
	}
	
	public void printMoveOptions() {
		if (this.north != null) {
			if (this.north.getStatus()) {
				System.out.println("There is a locked door to the North");
			}
			else {
				System.out.println("There is an unlocked door to the North");
			}
		}
		if (this.east != null) {
			if (this.east.getStatus()) {
				System.out.println("There is a locked door to the East");
			}
			else {
				System.out.println("There is an unlocked door to the East");
			}
		}
		if (this.south != null) {
			if (this.south.getStatus()) {
				System.out.println("There is a locked door to the South");
			}
			else {
				System.out.println("There is an unlocked door to the South");
			}
		}
		if (this.west != null) {
			if (this.west.getStatus()) {
				System.out.println("There is a locked door to the West");
			}
			else {
				System.out.println("There is an unlocked door to the West");
			}
		}
		System.out.println();
	}
	
	public Door getDoor(String dir) {
		switch(dir) {
		case "n":
			return this.north;
		case "e":
			return this.east;
		case "s":
			return this.south;
		case "w":
			return this.west;
		}
		return null;
	}
	
	public ArrayList<Enemy> getEnemies() {
		return this.enemies;
	}
	
	public void addEnemy(Enemy enemy) {
		this.enemies.add(enemy);
	}
	
	public void removeEnemy(Enemy enemy) {
		this.enemies.remove(enemy);
	}
	
	public ArrayList<NPC> getNPCs() {
		return this.NPCs;
	}
	
	public void addNPC(NPC npc) {
		this.NPCs.add(npc);
	}
	
	public void removeNPC(NPC npc) {
		this.NPCs.remove(npc);
	}
	
	public void setContainer(Container container) {
		this.container = container;
	}
	
	public Container getContainer() {
		return this.container;
	}
	
	public ArrayList<Item> pickUpContainer() {
		ArrayList<Item> pickup = container.getContents();
		this.container = null;
		return pickup;
		
	}
}
