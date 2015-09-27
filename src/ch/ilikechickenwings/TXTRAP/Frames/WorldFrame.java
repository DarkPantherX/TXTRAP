package ch.ilikechickenwings.TXTRAP.Frames;

import java.util.ArrayList;

import ch.ilikechickenwings.TXTRAP.City;
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
		
		mainFrame.log("Type help for available commands");
	}
	
	



	/** 
	 * Creates new World with player and MainFrame
	 */
	public WorldFrame(Player player, MainFrame mF){
		this(mF);
		setPlayer(player);
		
		mainFrame.log(player.getName()+" wakes up in " + player.getCity().getCityName());
		mainFrame.log("Type help for available commands");
		
	}
	
	
	
	private void createWorld() {
		City city = new City("Tamariel");
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
				mainFrame.log("Available commands: map ->Showes Cities "
						+ "\n goto <cityname> ->You go to the chosen city"
						+ "\n status ->Tells you how many lifes you have left"
						+ "\n inventory -> Shoes you your inventory");
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
							mainFrame.log("You went to: " + c.getCityName());
						}else if(i<0){
							break;
						}
					

				}
				if(notFound){
					
					mainFrame.log("Couldn't find you city");
				}
				}
					break;
			case "map":
				mainFrame.log("Cities here:");
				City city;
				for(int in=0; in<cities.size();in++){
					city= (City)cities.get(in);
					mainFrame.logSingleLine(" "+city.getCityName()+",");
				}
					break;
			case "mine":
					break;
			case "sleep":
					break;
			case "talk":
					break;
			case "status":
				mainFrame.log("Health: ");
				int h= (int)(player.getHealth()/player.getMaxHealth()*10);
				for(int i=0;i<10;i++){
					if(h>0){
						mainFrame.logSingleLine("O");
						h--;
					}else{
						mainFrame.logSingleLine("X");
					}
					
					
				}
				
				
				mainFrame.log("You have "+Float.toString(player.getHealth())+"% life left");
				
				mainFrame.log("And you are in "+ player.getCity().getCityName()+" at the moment");
					break;
			case "inventory":
				mainFrame.log("In your Inventory is: ");
					Item it;
					if(player.getInventory().size()>0){
						for(int i2=0;i2<player.getInventory().size();i2++){
						it = (Item) player.getInventory().get(i2);
						mainFrame.log("->"+Integer.toString(it.getQuantity())+"x "+it.getName());
						}
					}else{
						mainFrame.log("-->nothing<-- (poor fuck...)");
					}
				
					break;
			default: mainFrame.gettArea().appendLine("Command not found");
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
