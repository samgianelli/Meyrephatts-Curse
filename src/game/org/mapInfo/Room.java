package game.org.mapInfo;

import java.util.ArrayList;

import game.entities.objects.*;
import javafx.scene.image.Image;
import game.entities.characters.*;

import java.io.Serializable;

public class Room implements Serializable {
	
	private static ArrayList<Room> map = new ArrayList<Room>();
	private ArrayList<Enemy> enemies;
	private ArrayList<NPC> NPCs;
	private Container container;
	private Door north;
	private Door east;
	private Door south;
	private Door west;
	private String imagePath;
	
	
	public static void populateMap()
	{
		if(map.size() == 0)
		{
			Room room1  = new Room();		
			Room room2  = new Room();
			Room room3  = new Room();
			Room room4  = new Room();
			Room room5  = new Room();
			Room room6  = new Room();
			Room room7  = new Room();
			Room room8  = new Room();
			Room room9  = new Room();
			Room room10 = new Room();
			Room room11 = new Room();
			Room room12 = new Room();
			Room room13 = new Room();
			
			//add images to the room!
			room1.setImg("res/rooms/room1.png");
			room2.setImg("res/rooms/room2.png");
			room3.setImg("res/rooms/room3.png");
			room4.setImg("res/rooms/room4.png");
			room5.setImg("res/rooms/room5.png");
			room6.setImg("res/rooms/room6.png");
			room7.setImg("res/rooms/room7.png");
			room8.setImg("res/rooms/room8.png");
			room9.setImg("res/rooms/room9.png");
			room10.setImg("res/rooms/room10.png");
			room11.setImg("res/rooms/room11.png");
			room12.setImg("res/rooms/room12.png");
			room13.setImg("res/rooms/room13.png");
			
			map.add(room1);
			map.add(room2);
			map.add(room3);
			map.add(room4);
			map.add(room5);
			map.add(room6);
			map.add(room7);
			map.add(room8);
			map.add(room9);
			map.add(room10);
			map.add(room11);
			map.add(room12);
			map.add(room13);
		}
		else //map already exists. fix the load images since they're transient!
		{
			Room room1  = map.get(0);	
			Room room2  = map.get(1);		
			Room room3  = map.get(2);		
			Room room4  = map.get(3);		
			Room room5  = map.get(4);		
			Room room6  = map.get(5);		
			Room room7  = map.get(6);		
			Room room8  = map.get(7);		
			Room room9  = map.get(8);		
			Room room10  = map.get(9);		
			Room room11  = map.get(10);		
			Room room12  = map.get(11);
			Room room13  = map.get(12);		
			
			//add images to the room!
			room1.setImg("res/rooms/room1.png");
			room2.setImg("res/rooms/room2.png");
			room3.setImg("res/rooms/room3.png");
			room4.setImg("res/rooms/room4.png");
			room5.setImg("res/rooms/room5.png");
			room6.setImg("res/rooms/room6.png");
			room7.setImg("res/rooms/room7.png");
			room8.setImg("res/rooms/room8.png");
			room9.setImg("res/rooms/room9.png");
			room10.setImg("res/rooms/room10.png");
			room11.setImg("res/rooms/room11.png");
			room12.setImg("res/rooms/room12.png");
			room13.setImg("res/rooms/room13.png");
		}

	}
	

	public Room() {
		//this.loot = new ArrayList<Container>();
		this.enemies = new ArrayList<Enemy>();
		this.NPCs = new ArrayList<NPC>();
		this.container = null;
		this.north = null;
		this.east = null;
		this.south = null;
		this.west = null;
		this.imagePath = null;
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
	
	public void setImg(String path)
	{
		this.imagePath = path;
	}
	
	public String getImage()
	{
		return this.imagePath;
	}
	
	public static ArrayList<Room> getMap()
	{
		return map;
	}
	
}
