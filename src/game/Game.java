package game;

import game.entities.characters.Player;
import game.entities.objects.Item;
import game.org.mapInfo.Instance;

public class Game {
	private Player player;
	private Instance instance;
	public String consoleOutput;
	
	// Temporary implementation for tests
	public Game() {
		player = new Player("Alex");
		instance = new Instance();
		
		Item.createItems();
		player.addItem(Item.existingItems(0).get(0));
		consoleOutput = new String();
	}
	
	public Instance getInstance() {
		return instance;
	}

	public void setInstance(Instance instance) {
		this.instance = instance;
	}

	public Player getPlayer() {
		return this.player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
}