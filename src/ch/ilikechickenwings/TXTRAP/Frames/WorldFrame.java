package ch.ilikechickenwings.TXTRAP.Frames;

import java.util.ArrayList;

import ch.ilikechickenwings.TXTRAP.City;
import ch.ilikechickenwings.TXTRAP.Console;
import ch.ilikechickenwings.TXTRAP.Frames.Processable;
import ch.ilikechickenwings.TXTRAP.Entity.Item;
import ch.ilikechickenwings.TXTRAP.Entity.Player;

public class WorldFrame implements Processable{
	
	/**
	 * ArrayList with city in the worlds
	 */
	public static ArrayList<City> cities = new ArrayList<City>();
	
	private Player player;
	private MainFrame mainFrame;
	
	

	
	/**
	 * Creates new World only with MainFrame
	 */
	public WorldFrame(MainFrame mF){
		setMainFrame(mF);
		createWorld();
		
		Console.log("Type help for available commands", Console.standartOutput);
	}
	
	



	/** 
	 * Creates new World with player and MainFrame
	 */
	public WorldFrame(Player player, MainFrame mF){
		this(mF);
		setPlayer(player);
		
		Console.log(player.getName()+" wakes up in " + player.getCity().getCityName(), Console.standartOutput);
		Console.log("Type help for available commands" , Console.standartOutput);
		
	}
	
	
	
	private void createWorld() {
		City city = new City("Tamariel");
		cities.add(city);
		city = new City("Bananistan");
		cities.add(city);
		city= new City("Eschenbach");
		cities.add(city);
		city=new City("Schmerikon");
		cities.add(city);
		city=new City("Ass");
		cities.add(city);
		
	}

	
	@Override
	public void processInput(String[] s) {
		
		switch (s[0].toLowerCase()){
			case "attack": 
					break;
			case "help":
				Console.log("Available commands: map ->Showes Cities "
						+ "\n goto <cityname> ->You go to the chosen city"
						+ "\n status ->Tells you how many lifes you have left"
						+ "\n inventory -> Shoes you your inventory", Console.standartOutput);
					break;
			case "goto":
				if(s.length>1){
				boolean notFound=true;
				int i=cities.size()-1;
				City c;
				while(notFound){
					c=(City) cities.get(i);
					if(c.getCityName().toLowerCase().equals(s[1])){
						notFound=false;
						}
						--i;
						if(!notFound){
							player.setCity(c);
							Console.log("You went to: " + c.getCityName(), Console.standartEvent);
						}else if(i<0){
							break;
						}
					

				}
				if(notFound){
					
					Console.log("Couldn't find you city", Console.errorOutput);
				}
				}
					break;
			case "map":
				Console.log("Cities here:", Console.standartOutput);
				City city;
				for(int in=0; in<cities.size();in++){
					city= (City)cities.get(in);
					Console.logSingleLine(" "+city.getCityName()+",",Console.standartListOutput);
				}
					break;
			case "mine":
					break;
			case "sleep":
					break;
			case "talk":
					break;
			case "status":
				Console.log("Your name is " +player.getName()+" the "+ player.getGameClass(),Console.standartOutput);
				Console.log("Health: ", Console.standartOutput);
				int h= (int)(player.getHealth()/player.getMaxHealth()*10);
				for(int i=0;i<10;i++){
					if(h>0){
						Console.logSingleLine("O");
						h--;
					}else{
						Console.logSingleLine("X");
					}
					
					
				}
				
				Console.logSingleLine(" --> "+Float.toString(player.getHealth())+"% life left", Console.standartOutput);
				
				Console.log("You are in the great "+ player.getCity().getCityName()+" at the moment", Console.standartOutput);
					break;
			case "inventory":
				Console.log("In your Inventory is: ", Console.standartOutput);
					Item it;
					if(player.getInventory().size()>0){
						for(int i2=0;i2<player.getInventory().size();i2++){
						it = (Item) player.getInventory().get(i2);
						Console.log("->"+Integer.toString(it.getQuantity())+"x "+it.getName(), Console.standartListOutput);
						}
					}else{
						Console.log("-->nothing<-- (poor fuck...)", Console.standartListOutput);
					}
				
					break;
			default: Console.log("Command not found",Console.errorOutput);
					break;

		}
			
		
	}
	
	
	

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}


	/**
	 * @return the mainFrame
	 */
	public MainFrame getMainFrame() {
		return mainFrame;
	}


	/**
	 * @param mainFrame the mainFrame to set
	 */
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}





	




}
