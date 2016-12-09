package game.entities.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;


@SuppressWarnings("serial")
public class Container implements Serializable{
	private ArrayList<Item> contents;
	private Effect effect;
	private int quality;
	private static int maxSize = 1; //change later
	private static int minSize = 1;
	
	public Container()
	{
		this.contents = new ArrayList<Item>();
		this.effect = null;
		this.quality = Container.returnQuality(); //let quality go from 0-4
	}
	
	public void setContents(Container container)
	{
		this.contents = container.getContents();

	}
	
	//generates the contents of the container instance
	public void generateContents()
	{
		int selection;
		Random rn = new Random();
		int size = Container.minSize + rn.nextInt((Container.maxSize - Container.minSize) + 1); //number of possible outcomes
		ArrayList<Item> potentialOptions = Item.existingItems(this.quality); //get size of list of items
		if(potentialOptions.size() == 0)
		{
			return; //invalid quality passed in. check me if empty containers are found!
		}
		for(int i = 0; i < size; i++)
		{
			selection = rn.nextInt(potentialOptions.size()); //don't need to subtract since nextInt is exclusive
			this.contents.add(potentialOptions.get(selection)); //look up the item and add it to the list		
		}
	}
	
	public static int returnQuality() //generates the quality of a container or item
	{
		Random rd = new Random();
		int result = rd.nextInt(10);
		if(result < 4)
		{
			return 0;
		}
		else if(result < 6)
		{
			return 1;
		}
		else if(result < 7.5)
		{
			return 2;
		}
		else if(result < 9)
		{
			return 3;
		}
		else 
		{
			return 4;
		}
	}
	
	public int getQuality()
	{
		return this.quality;
	}
	
	public ArrayList<Item> getContents()
	{
		return this.contents;
	}
	
	public void setEffect(Effect effect)
	{
		this.effect = effect;
	}
	
	public Effect getEffect()
	{
		return this.effect;
	}
}
