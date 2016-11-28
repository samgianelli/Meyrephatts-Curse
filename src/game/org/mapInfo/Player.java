package game.org.mapInfo;

public class Player {
	
	private Room currRoom;
	
	public Player() {
		this.currRoom = null;
	}
	
	public Player(Room currRoom) {
		this.currRoom = currRoom;
	}
	
	public void move(String move) {
		if(validMove(currRoom.getDoor(move))) {
			if(!currRoom.getDoor(move).getStatus()) {
				currRoom = currRoom.getDoor(move).enterRoom(currRoom);
				System.out.println("You have moved " + move);
			}
			else {
				System.out.println("This door is locked and you do not have the proper key!");
			}
		}
	}
	
	public void moveOptions() {
		this.currRoom.printMoveOptions();
	}
	
	public boolean validMove(Door move) {
		if (move == null) {
			System.out.println("There is no door in that direction!");
			return false;
		}
		else {
			return true;
		}
	}
	
	/*public boolean tryKey(Door door) {
		for (Item item : inventory) {
			if (item == Key.class) {
				if (door.tryKey(item)) {
					return true;
				}
			}
		}
		return false;
	}*/
}