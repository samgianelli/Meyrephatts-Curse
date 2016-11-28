package game.org.mapInfo;

public class Wall {
	//protected String border;
	protected int xPos;
	protected int yPos;
	
	public Wall() {
		//this.border = "";
		this.xPos = 0;
		this.yPos = 0;
	}
	
	public Wall(/*String direction,*/ int xPos, int yPos) {
		//this.border = direction;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	//public void setBorder(String direction) {
	//	this.border = direction;
	//}
	
	//public String getBorder() {
	//	return this.border;
	//}
	
	public void rejectMovement() {
		
	}

}


// I dont think we need directionality with our walls, they can simply be four sided for a particular coordinate