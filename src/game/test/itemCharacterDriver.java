package game.test;

import game.entities.characters.*;
import game.entities.objects.*;


/******************************************************************************************************************************************
 *	This driver is for testing to make sure that items are generated correctly, that characters and items interact as desired, and that   *
 * 	stats generated from items works as we expect. Note that at this time, we only have armor and weapon (two equipment slots).			  *
 *****************************************************************************************************************************************/

public class itemCharacterDriver {
	
	
	public static void main(String[] args)
	{		
		
		Item.createItems(); //generate the items <DO THIS BEFORE ENEMIES SINCE ENEMIES DROP ITEMS>		
		Enemy.createEnemies(); //generate the enemies
		Player Alex = new Player("Alex");
		Action.setPlayer(Alex); //add Alex to the action class as the main player.
		Enemy BoopleSnoop = Enemy.retrieveEnemy("BoopleSnoop");


		itemCharacterDriver.displayPlayerStats(Alex);
		
		System.out.println("\n" + Alex.getName() + " is going to war with " + BoopleSnoop.getName());
		if(BoopleSnoop.getDrops().getContents().size() != 0)
		{
			System.out.println(BoopleSnoop.getName() + " drops " + BoopleSnoop.getDrops().getContents().get(0).getname());
		}
		
		Action.beginBattle(BoopleSnoop); //Alex fights against BoopleSnoop!
		itemCharacterDriver.displayPlayerStats(Alex);
		System.out.println("Alex has taken damage. He has health at " + Alex.getHealth());
		Equipment x = (Equipment) Alex.getInventory().get(0);
		System.out.println("Alex has decided to equip " + x.getname());
		Alex.getEquippedItems().add(x);

		//update Alex's stats
		Alex.setEffectStats(x.getStats().plus(Alex.getEffectStats())); //update effect stats
		Alex.getCurrentStats(); //update overall stats
		
		itemCharacterDriver.displayPlayerStats(Alex);
		
	}
	
	// Literally just prints the player's stats to the console
	public static void displayPlayerStats(Player p1)
	{
		System.out.println("Displaying the stats for player: " + p1.getName());
		System.out.println("---------------------------------");
		System.out.print("Current Level: ");
		System.out.println(p1.getCurrentStats().getLevel());
		System.out.print("Current Attack: ");
		System.out.println(p1.getCurrentStats().getAttack());
		System.out.print("Current Defense: ");
		System.out.println(p1.getCurrentStats().getDefense());
		System.out.print("Current Max Health: ");
		System.out.println(p1.getCurrentStats().getMaxHealth());
		System.out.println("Current experience at: " + p1.getExperience());
		System.out.println("Next level is at: ");
		System.out.println("---------------------------------");		
	}
}
