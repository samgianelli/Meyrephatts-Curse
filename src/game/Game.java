package game;

import game.entities.characters.Player;
import game.entities.objects.Item;

public class Game {
	private Player player;
	public String consoleOutput;
	
	// Temporary implementation for tests
	public Game() {
		player = new Player("Alex");
		Item.createItems();
		player.addItem(Item.existingItems(0).get(0));
		consoleOutput = new String();
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
}