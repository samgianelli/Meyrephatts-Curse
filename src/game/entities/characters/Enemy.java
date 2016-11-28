package game.entities.characters;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import game.entities.objects.Container;
import game.entities.objects.Effect;
import game.entities.objects.Item;

public class Enemy extends Fighter {
	private Container drops;
	private Effect effect;
	private static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
	
	public Enemy(String name) {
		this.name = name;
		this.drops = new Container();
		//this.effect = new Effect();
	}
	
	public static void createEnemies()
	{
		Enemy BoopleSnoop = new Enemy("BoopleSnoop");
		BoopleSnoop.drops.generateContents();
		enemies.add(BoopleSnoop); //test enemy
	}
	
	public static Enemy retrieveEnemy(String enemyName)
	{
		for(Enemy x : Enemy.enemies)
		{
			if(x.getName().equals(enemyName)) //found the matching enemy!
			{
				return x;
			}
		}
		return null; //enemy not found. throw an error.
	}
	
	
	public void generateDrop() {
		// call container method for generating item and set in container
		
	}
	
	public void awardDrop(Player player) {
		for(Item x : drops.getContents())
		{
			player.getInventory().add(x);
			JOptionPane.showMessageDialog(null, 
					player.getName() + " has received " + x.getName() + " for kicking the crap out of " + this.getName() + "!", 
					"Pick Up Reward", 
					JOptionPane.PLAIN_MESSAGE);			
		}
	}
	
	public void attackPlayer() {
		// apply effect to Enemy's Stats
		// reduce Player's health by proper amount
	}
	
	//public void flee() {
		// Why do Enemies have the ability to flee?
	//}
	
	public void setEffect(Effect effect) {
		this.effect = effect;
	}
	
	public Effect getEffect() {
		return this.effect;
	}
	
	public Container getDrops()
	{
		return this.drops;
	}

}
