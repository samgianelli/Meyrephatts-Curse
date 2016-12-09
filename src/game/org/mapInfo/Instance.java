package game.org.mapInfo;

import java.io.Serializable;
import java.util.ArrayList;

import game.entities.characters.*;
import game.entities.objects.*;
import javafx.scene.image.Image;

@SuppressWarnings("serial")
public class Instance implements Serializable {
	private ArrayList<Person> people;
	private ArrayList<Room> rooms;
	private ArrayList<Container> prizes;
	private Player player;
	

	
	public Instance(Player player) {
		this.player = player;
		this.populate(player); //test!
	}

	public Instance(ArrayList<Person> people, ArrayList<Container> containers, ArrayList<Room> rooms) {
		this.people = people;
		this.prizes = containers;
		this.rooms = rooms;
		this.player = null;
	}
	
	
	public void populate(Player player)
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

		
		Door doorA = new Door(false, 0, room1, room2);
		Door doorB = new Door(true, 0, room2, room3);
		Door doorC = new Door(false, 0, room3, room4);
		Door doorD = new Door(false, 0, room4, room5);
		Door doorE = new Door(true, 0, room5, room6);
		Door doorF = new Door(false, 0, room5, room7);
		Door doorG = new Door(false, 0, room5, room8);
		Door doorH = new Door(false, 0, room8, room9);
		Door doorI = new Door(false, 0, room9, room10);
		Door doorJ = new Door(false, 0, room9, room11);
		Door doorK = new Door(false, 0, room9, room12);
		Door doorL = new Door(false, 0, room12, room13);
		
		room1.setDoors(null, null, doorA, null);
		room2.setDoors(doorA, null, doorB, null);
		room3.setDoors(doorB, doorC, null, null);
		room4.setDoors(null, doorD, null, doorC);
		room5.setDoors(doorE, doorG, doorF, doorD);
		room6.setDoors(null, null, doorE, null);
		room7.setDoors(doorF, null, null, null);
		room8.setDoors(null, doorH, null, doorG);
		room9.setDoors(doorI, doorJ, doorK, doorH);
		room10.setDoors(null, null, doorI, null);
		room11.setDoors(null, null, null, doorJ);
		room12.setDoors(doorK, null, doorL, null);
		room13.setDoors(doorL, null, null, null);
		
		
		player.setLocation(room1);
		
		Item.createItems(); //generate the items <DO THIS BEFORE ENEMIES SINCE ENEMIES DROP ITEMS>		
		Enemy.createEnemies(); //generate the enemies
		//Action.setPlayer(player);
		
		Enemy enemy1 = Enemy.retrieveEnemy("Rat").clone();
		Enemy enemy2 = Enemy.retrieveEnemy("Rat").clone();

		Enemy enemy3 = Enemy.retrieveEnemy("Knight").clone();
		Enemy enemy8 = Enemy.retrieveEnemy("Knight").clone();
		Enemy enemy4 = Enemy.retrieveEnemy("Rat").clone();
		Enemy enemy5 = Enemy.retrieveEnemy("Goblin").clone();
		Enemy enemy6 = Enemy.retrieveEnemy("Goblin").clone();
		Enemy enemy7 = Enemy.retrieveEnemy("Dragon").clone();
		
		
		enemy1.setLocation(room2); 
		enemy2.setLocation(room5);
		enemy3.setLocation(room3);
		enemy4.setLocation(room3);
		enemy5.setLocation(room9);
		enemy6.setLocation(room9);
		enemy7.setLocation(room13);
		enemy8.setLocation(room10);
		
		//enemy4.setLocation(room2);
		//System.out.println(room3.getEnemies().get(0).getName());
		
		
		//////////////// Create Inputs to main GAME /////////////////////////
		Container container1 = new Container();
		Container container2 = new Container();
		Container container3 = new Container();
		Container container4 = new Container();
		container1.generateContents();
		container2.generateContents();
		container3.generateContents();
		container4.generateContents();
		room2.setContainer(container1);
		room4.setContainer(container2);
		room7.setContainer(container3);
		room11.setContainer(container4);
		
		ArrayList<Room> labyrinth = new ArrayList<Room>();
		labyrinth.add(room1);
		labyrinth.add(room2);
		labyrinth.add(room3);
		labyrinth.add(room4);
		labyrinth.add(room5);
		labyrinth.add(room6);
		labyrinth.add(room7);
		labyrinth.add(room8);
		labyrinth.add(room9);
		labyrinth.add(room10);
		labyrinth.add(room11);
		labyrinth.add(room12);
		labyrinth.add(room13);
		this.rooms = labyrinth;
		
		ArrayList<Person> people = new ArrayList<Person>();
		people.add(enemy1);
		people.add(enemy2);
		people.add(enemy3);
		this.people = people;
		
		ArrayList<Container> containers = new ArrayList<Container>();
		containers.add(container1);
		this.prizes = containers;
		////////////////////////////////////////////////////////////////
		
	}
	
	public ArrayList<Person> getPeople() {
		return this.people;
	}
	
	public ArrayList<Container> getPrizes() {
		return this.prizes;
	}
	
	public ArrayList<Room> getRooms() {
		return this.rooms;
	}
	
	public void addPerson(Person person) {
		this.people.add(person);
	}
	
	public void addPrize(Container prize) {
		this.prizes.add(prize);
	}
	
	public void addRoom(Room room) {
		this.rooms.add(room);
	}
	
	public Player getPlayer()
	{
		return this.player;
	}
	
}
