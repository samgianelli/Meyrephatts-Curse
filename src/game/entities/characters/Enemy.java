package game.entities.characters;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import game.entities.objects.Container;
import game.entities.objects.Effect;
import game.entities.objects.Item;
import game.org.mapInfo.Room;

@SuppressWarnings("serial")
public class Enemy extends Fighter implements Serializable {
	private Container drops;
	private Effect effect;
	private String monsterSprite;
	private static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private int experienceLevel;
	
	public Enemy(String name) {
		this.name = name;
		this.drops = new Container();
		this.monsterSprite = null;
		this.experienceLevel = 0;
	}
	public Enemy()
	{
		
	}
	
	public static void createEnemies()
	{
		Enemy BoopleSnoop = new Enemy("BoopleSnoop");
		Stats BoopleStats = new Stats(50, 5, 5);
		BoopleSnoop.setBaseStats(BoopleStats);
		BoopleSnoop.setCurrentStats();
		BoopleSnoop.setHealth(BoopleSnoop.getCurrentStats().getMaxHealth());
		BoopleSnoop.drops.generateContents();
		enemies.add(BoopleSnoop); //test enemy
		BoopleSnoop.monsterSprite = new String("res/items/seal.jpg");
		BoopleSnoop.experienceLevel = 50;
		
		Enemy FluffyRabbit = new Enemy("FluffyRabbit");
		FluffyRabbit.drops.generateContents();
		enemies.add(FluffyRabbit);
		FluffyRabbit.monsterSprite = new String("res/npcs/sage.png"); //change later
		FluffyRabbit.experienceLevel = 25;
		
		Enemy Rat = new Enemy("Rat");
		Stats ratStats = new Stats(50, 5, 5);
		Rat.setBaseStats(ratStats);
		Rat.setCurrentStats();
		Rat.setHealth(Rat.getCurrentStats().getMaxHealth());
		Rat.drops.generateContents();
		enemies.add(Rat);
		Rat.monsterSprite = new String("res/monsters/rat.png");   // update later
		Rat.experienceLevel = 30;
		
		Enemy Goblin = new Enemy("Goblin");
		Stats goblinStats = new Stats(100, 5, 10);
		Goblin.setBaseStats(goblinStats);
		Goblin.setCurrentStats();
		Goblin.setHealth(Goblin.getCurrentStats().getMaxHealth());
		Goblin.drops.generateContents();
		enemies.add(Goblin);
		Goblin.monsterSprite = new String("res/monsters/rat.png");
		Goblin.experienceLevel = 40;
		
		Enemy Knight = new Enemy("Knight");
		Stats knightStats = new Stats(200, 10, 20);
		Knight.setBaseStats(knightStats);
		Knight.setCurrentStats();
		Knight.setHealth(Knight.getCurrentStats().getMaxHealth());
		Knight.drops.generateContents();
		enemies.add(Knight);
		Knight.monsterSprite = new String("res/monsters/knight.png");
		Knight.experienceLevel = 70;
		
		Enemy Dragon = new Enemy("Dragon");
		Stats dragonStats = new Stats(500, 50, 50);
		Dragon.setBaseStats(dragonStats);
		Dragon.setCurrentStats();
		Dragon.setHealth(Dragon.getCurrentStats().getMaxHealth());
		Dragon.drops.generateContents();
		enemies.add(Dragon);
		Dragon.monsterSprite = new String("res/monsters/dragon.png");
		Dragon.experienceLevel = 100;
	}
	
	public static Enemy retrieveEnemy(String enemyName)
	{
		for(Enemy x : Enemy.enemies)
		{
			if(x.getName().equals(enemyName)) //found the matching enemy! <TODO: MAKE ME A CLONING FUNCTION LIKE THE ITEM ONE>
			{
				return x;
			}
		}
		return null; //enemy not found. throw an error.
	}
	
	
	public String getImage()
	{
		return this.monsterSprite;
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
	
	public void setLocation(Room room) {
		this.location = room;
		room.addEnemy(this);
	}
	
	public void die() {
		location.removeEnemy(this);
	}
	
	public void setExperienceLevel(int lev)
	{
		this.experienceLevel = lev;
	}
	
	public int getExperienceLevel()
	{
		return this.experienceLevel;
	}
	
	public Enemy clone()
	{
		Enemy clone = new Enemy();
		clone.baseStats = this.baseStats;
		clone.currentHealth = this.currentHealth;
		clone.effectStats = this.effectStats;
		clone.drops = this.drops;
		clone.location = this.location;
		clone.alive = this.alive;
		clone.effect = this.effect;
		clone.experienceLevel = this.experienceLevel;
		clone.monsterSprite = this.monsterSprite;
		clone.name = this.name;
		return clone;
	}
			

}
