package game.entities.characters;

public abstract class Person {
	protected String name;
	//protected Instance location;
	
	public Person() {
		this.name = "";
		//this.location = new Instance();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	/*
	public void setLocation(Instance location) {
		this.location = location;
	}
	
	public Instance getLocation() {
		return this.location;
	}
	*/

}
