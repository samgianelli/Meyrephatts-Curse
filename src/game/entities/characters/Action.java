package game.entities.characters;
//import org.mapInfo.*;

import game.entities.Entity;
import game.entities.objects.Item;

public class Action {
	
	protected static Player player; //user playing the game.
	
	public static void useItem(Item item) {
		
	}
	
	public static void changeMapCoordinates(int newX, int newY) {
		
	}
	
	public static void interactEntity(Entity entity) {
		
	}
	
	public static void setPlayer(Player p1)
	{
		Action.player = p1;
	}
	
	//player fights e1
	public static void beginBattle(Enemy e1)
	{
		while(e1.getHealth() > 0 && player.getHealth() > 0)
		{
			player.dealDamage(10, e1);
			e1.dealDamage(2, player);			
		}
		if(player.getHealth() <= 0)
		{
			System.out.println("Oh dear! You are dead!");
			//reload last save file.
		}
		else
		{
			System.out.println("Player " + player.getName() + " defeated enemy " + e1.getName());
			e1.awardDrop(player); //give the enemy drops to the player.
			
		}
	}
}
