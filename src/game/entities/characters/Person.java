package game.entities.characters;

import game.entities.Entity;
import game.org.mapInfo.Room;

public abstract class Person extends Entity {
	protected String name;
	protected Room location;
	
	public Person() {
		this.name = "";
		this.location = null;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public abstract void setLocation(Room room);
	
	public Room getLocation() {
		return this.location;
	}
	

}
