package game.entities.objects;

//okay, let's be real, we don't need to extend item here lol =)

public class Key{
	
	private String name;
	private int keyID;
	
	public Key() {
		this.name = "";
		this.keyID = 0;
	}
	
	public Key(String name, int id) {
		this.name = name;
		this.keyID = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getID() {
		return this.keyID;
	}
	
	public void setID(int id) {
		this.keyID = id;
	}
	/*
	private Door unlocks;
	
	public Key(Door unlockMe)
	{
		this.unlocks = unlockMe;
	}
	
	public boolean correctKeyDoorPair(Door door)
	{
		if(door.equals(this.unlocks))
		{
			return true; //same door! wow! unlock it!
		}
		else
		{
			return false;
		}
	}
	
	public void useKey(Door door)
	{
		if(correcyKeyDoorPair(door))
		{
			door.unlockDoor();
		}
		else
		{
			//used for debugging, but maybe add a GUI error. #SWINGLIFE
			System.out.println("Wrong key on that door, friend");
		}
	}
	*/
	
}
