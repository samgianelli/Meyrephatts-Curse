package game.org.mapInfo;

import java.io.Serializable;
import java.util.ArrayList;

import game.entities.characters.*;

public class Instance implements Serializable {
	//private final int xBound = 10;  // NEED TO AGREE ON HOW TO CREATE THE MAP / CONCEPTUALIZE THE MAP
	//private final int yBound = 10;
	//private Object map[][];            // CURRENTLY THE MAP IS A 2D ARRAY OF OBJECTS
	//private int xCoord;  // of the player
	//private int yCoord;
	private ArrayList<Person> people;
	//private ArrayList<Container> prizes;
	private ArrayList<Room> rooms;
	private ArrayList<Door> doors;
	//private ArrayList<Wall> edges;
	
	public Instance() {
		//this.map = new Object[xBound][yBound];
		//this.xCoord = 4;  // arbitrary for now, can change later
		//this.yCoord = 4;
		this.people = new ArrayList<Person>();
		//this.prizes = new ArrayList<Container>();  // add back in later
		this.rooms = new ArrayList<Room>();
		this.doors = new ArrayList<Door>();
		//this.edges = new ArrayList<Wall>();
		
	}
	
	/*public void updateCoordinates(int x, int y) {
		this.xCoord = x;
		this.yCoord = y;
	}*/
	
	public ArrayList<Person> getPeople() {
		return this.people;
	}
	
	//public ArrayList<Container> getPrizes() {
	//	return this.prizes;
	//}
	
	public ArrayList<Room> getRooms() {
		return this.rooms;
	}
	
	public ArrayList<Door> getDoors() {
		return this.doors;
	}
	
	public void addPerson(Person person) {
		this.people.add(person);
	}
	
	//public void addPrize(Container prize) {
	//	this.prizes.add(prize);
	//}
	
	public void addRoom(Room room) {
		this.rooms.add(room);
	}
	
	public void addDoor(Door door) {
		this.doors.add(door);
	}
	
	/*public ArrayList<Wall> getEdges() {
		return this.edges;
	}*/
	
	/*public boolean move(String direction) {
		direction = direction.toLowerCase();
		int newX = xCoord;
		int newY = yCoord;
		
		switch (direction) {
			case "north":
				newY = newY + 1;
				break;
			case "east":
				newX = newX + 1;
				break;
			case "south":
				newY = newY - 1;
				break;
			case "west":
				newX = newX - 1;
				break;
		}
		
		if(newX >= xBound | newX < 0 | newY >= yBound | newY < 0) {
			System.out.println("Cannot move.  You have reached the boundary of the dungeon!");
			return false;
		}
		else if (this.map[newX][newY].getClass() == Wall.class) {
			System.out.println("Cannot move, there is a wall that way.");
			return false;
		}
		else if (this.map[newX][newY].getClass() == Door.class) {
			// prompt to use key
			// if no key then do not update move and return false
			// else update coords and return true
		}
		else {
			this.xCoord = newX;
			this.yCoord = newY;
			return true;
		}
			
		
	}*/
	
	public void invalidateMove() {
		
	}
}
