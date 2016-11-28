package game.org.mapInfo;

import game.entities.characters.Action;
import game.entities.characters.Enemy;
import game.entities.characters.Player;
import game.entities.objects.Container;
import game.entities.objects.Item;
import game.entities.objects.Key;

public class testDriver {
	
	public static void main(String args[]) {
		
		Instance map = new Instance();
		
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
		
		Door doorA = new Door(false, 0, room1, room2);
		Door doorB = new Door(true, 420, room2, room3);
		Door doorC = new Door(false, 0, room3, room4);
		Door doorD = new Door(false, 0, room4, room5);
		Door doorE = new Door(true, 69, room5, room6);
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
		
		/*room1.printMoveOptions();
		room2.printMoveOptions();
		room3.printMoveOptions();
		room4.printMoveOptions();
		room5.printMoveOptions();
		room6.printMoveOptions();
		room7.printMoveOptions();
		room8.printMoveOptions();
		room9.printMoveOptions();
		room10.printMoveOptions();
		room11.printMoveOptions();
		room12.printMoveOptions();
		room13.printMoveOptions();*/
		
		Player player = new Player("Sam");
		player.setLocation(room1);
		
		Key key1 = new Key("Key of Azeroth", 69);
		Key key2 = new Key("Key of Burning", 420);
		player.addKey(key1);
		player.addKey(key2);
		
		Item.createItems(); //generate the items <DO THIS BEFORE ENEMIES SINCE ENEMIES DROP ITEMS>		
		Enemy.createEnemies(); //generate the enemies
		Action.setPlayer(player);
		Enemy BoopleSnoop = Enemy.retrieveEnemy("BoopleSnoop");
		Enemy PieceOfShitMarefat = Enemy.retrieveEnemy("Piece Of Shit Named Marefat");
		Enemy FluffyRabbit = Enemy.retrieveEnemy("Fluffy Rabbit");
		
		BoopleSnoop.setLocation(room3);
		PieceOfShitMarefat.setLocation(room5);
		FluffyRabbit.setLocation(room7);
		//System.out.println(room3.getEnemies().get(0).getName());
		
		Container container1 = new Container();
		container1.generateContents();
		room2.setContainer(container1);
		
		player.moveOptions();
		player.checkEnemies();
		player.move("e");
		player.checkEnemies();
		player.move("s");
		player.checkEnemies();
		player.move("s");
		player.checkEnemies();
		player.move("e");
		player.checkEnemies();
		player.move("e");
		player.checkEnemies();
		player.move("n");
		player.checkEnemies();
		player.move("n");
		player.checkEnemies();
		player.move("s");
		player.move("s");
		player.checkEnemies();
		player.move("n");
		player.checkEnemies();
		player.move("n");
		player.checkEnemies();
		System.out.println(player.getHealth());
		
		
		/*itemCharacterDriver.displayPlayerStats(player);
		
		if (player.getLocation() == BoopleSnoop.getLocation()) {
			System.out.println("\n" + player.getName() + " is going to war with " + BoopleSnoop.getName());
			if(BoopleSnoop.getDrops().getContents().size() != 0)
			{
				System.out.println(BoopleSnoop.getName() + " drops " + BoopleSnoop.getDrops().getContents().get(0).getname());
			}
			System.out.println("Test");
			Action.beginBattle(BoopleSnoop); //Alex fights against BoopleSnoop!
			itemCharacterDriver.displayPlayerStats(player);
			System.out.println("Alex has taken damage. He has health at " + player.getHealth());
			Equipment x = (Equipment) player.getInventory().get(0);
			System.out.println("Alex has decided to equip " + x.getname());
			player.getEquippedItems().add(x);

			//update Alex's stats
			player.setEffectStats(x.getStats().plus(player.getEffectStats())); //update effect stats
			player.getCurrentStats(); //update overall stats
			
			itemCharacterDriver.displayPlayerStats(player);
			
		}*/
		
		
		
	}
	
	

}
