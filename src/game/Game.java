package game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import game.entities.characters.Player;
import game.org.mapInfo.Instance;

@SuppressWarnings("serial")
public class Game implements Serializable{
	private Player player;
	private Instance instance;
	public String consoleOutput;
	
	
	// Temporary implementation for tests
	public Game(String name) {
		player = new Player(name);
		instance = new Instance(player);
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
	
	
	
	
	//methods for serialization (used in load/save methods)
	//////////////////////////////////////////////////////////////////////
	public static void saveData(Game game) { //save data (serialization)
		FileOutputStream fileOut = null;
		ObjectOutputStream objOut= null;

		try 
		{
			fileOut = new FileOutputStream( "Game.ser" );	
			objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(game);
			objOut.close();
			fileOut.close();
	     }	
		
		catch(IOException i)
	    {
			i.printStackTrace();
	    }		
	}

	public static Game loadData() { //load data (serialization)
		FileInputStream fileIn = null;
		ObjectInputStream objIn = null;
		Game game=null;
			
		try
		{
			fileIn = new FileInputStream("Game.ser");
			objIn = new ObjectInputStream(fileIn);
			game = (Game) objIn.readObject();
			objIn.close();
			fileIn.close();
		}
		catch(IOException i)
		{
			i.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}  
		return game;
	}	
	
	
}