package game.org.mapInfo;

import java.io.Serializable;

public class Door implements Serializable {
	private boolean isLocked;
	private int keyID;
	private Room roomA;
	private Room roomB;
	
	public Door() {
		this.isLocked = true;
		this.keyID = 0;
		this.roomA = null;
		this.roomB = null;
		
	}
	
	public Door(boolean isLocked, int keyID, Room roomA, Room roomB) {
		this.isLocked = isLocked;
		this.keyID = keyID;
		this.roomA = roomA;
		this.roomB = roomB;
	}
	
	/*public boolean tryKey(Key key) {
		if (key.getID() == this.keyID) {
			this.unlockDoor();
		}
		else {
			System.out.println("This does not match the lock!");
		}
	}*/
	
	public void unlockDoor() {
		this.isLocked = false;
	}
	
	public void lockDoor() {   //probably not a necessary method
		this.isLocked = true;
	}
	
	public boolean getStatus() {
		return this.isLocked;
	}
	
	public void tryDoor() {
		
	}
	
	public Room enterRoom(Room currRoom){
		if (currRoom == roomA) {
			return roomB;
		}
		else {
			return roomA;
		}
	}
	
	public int getKeyID() {
		return this.keyID;
	}
	
	public void setKeyID(int key) {
		this.keyID = key;
	}

}
