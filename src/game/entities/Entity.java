package game.entities;

import game.entities.characters.Action;
import game.entities.characters.Player;

public class Entity {
	private boolean turn;
	
	public boolean isPlayer()
	{
		return this instanceof Player;
	}
	
	public void setTurn(boolean status)
	{
		this.turn = status;
	}
	
	public boolean isTurn()
	{
		return this.turn;
	}
	
	public void takeAction(Action action) // fill me in later
	{
		
	}
}
