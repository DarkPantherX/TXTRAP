package ch.ilikechickenwings.TXTRAP.Frames;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import ch.ilikechickenwings.TXTRAP.City;
import ch.ilikechickenwings.TXTRAP.Console;
import ch.ilikechickenwings.TXTRAP.Frames.Processable;
import ch.ilikechickenwings.TXTRAP.Entity.Item;
import ch.ilikechickenwings.TXTRAP.Entity.Player;

public class WorldFrame implements Processable, Runnable, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * ArrayList with city in the worlds
	 */
	private ArrayList<City> cities = new ArrayList<City>();
	private boolean gameRunning=true;
	private String name;
	
	
	private Player player;
	private MainFrame mainFrame;
	
	/**
	 * Time for the game
	 */
	private long time=0;
	

	
	/**
	 * Creates new World only with MainFrame
	 */
	public WorldFrame(MainFrame mF, String name1){
		setMainFrame(mF);
		createWorld();
		new Thread(this).start();
		name=name1;
		Console.log("Type help for available commands", Console.standartOutput);
	}
	
	



	/** 
	 * Creates new World with player and MainFrame
	 */
	public WorldFrame(Player player, MainFrame mF, String name1){
		this(mF,name1);
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
					Console.log("You attacked: " + s[1]);
				
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
						Console.log("-->nothing<-- (poor as fuck...)", Console.standartListOutput);
					}
				
					break;
			case "time":
					int minTime= (int) time%60;
					int hourTime=(int) (time/60)%24;
					int dayTime=(int)((time/60)/24)%30;
					int monthTime=(int)(((time/60)/24)/30)%12;
					long yearTime=(long)(((time/60)/24)/30)/12;
					
					Console.log("It is: ",Console.standartOutput);
					if(hourTime/10==0){Console.logSingleLine(Integer.toString(0),Console.standartListOutput);}
					Console.logSingleLine(hourTime+":",Console.standartListOutput);
					if(minTime/10==0){Console.logSingleLine(Integer.toString(0),Console.standartListOutput);}
					Console.logSingleLine(minTime+ " - ",Console.standartListOutput);
					Console.logSingleLine("On the ",Console.standartOutput);Console.logSingleLine(Integer.toString(dayTime+1),Console.standartListOutput);Console.logSingleLine("th day ",Console.standartOutput);
					Console.logSingleLine("on the ",Console.standartOutput);Console.logSingleLine(Integer.toString(monthTime+1),Console.standartListOutput);Console.logSingleLine("th month ",Console.standartOutput);
					Console.logSingleLine("in the ",Console.standartOutput);Console.logSingleLine(Integer.toString((int)yearTime+1),Console.standartListOutput);Console.logSingleLine("th year ",Console.standartOutput);
					
					
					break;
					
			case "save":

				File file0 = new File((new StringBuilder()).append(System.getProperty("user.home")).append("/.TXTRAP/".concat(name.concat(".dat"))).toString());
		        if(!file0.exists())
		        {
		            File file1 = new File((new StringBuilder()).append(System.getProperty("user.home")).append("/.TXTRAP").toString());
		            file1.mkdir();
		        }
				
			  name.concat(".dat");
			   String s1 = new StringBuilder().append(System.getProperty("user.home")).append("/.TXTRAP/").append(name.concat(".dat")).toString();
			    try {
			        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(s1));
			        oos.writeObject(this);
			        oos.close();
			    } catch(Exception ex) {
			        ex.printStackTrace();
			    }
			    break;
			
			default: Console.log("Command not found",Console.errorOutput);
					break;

		}
			
		
	}
	
	
	
	
	public void loadedGame() {
		Console.clearlog();
		Console.logSingleLine("Loaded", Console.standartEvent);
		Console.log("Welcome back, "+player.getName()+" the " + player.getGameClass(), Console.startOutput);
		Console.log("You are in " +player.getCity().getCityName(), Console.startOutput);
		
	}

	
	
	

	@Override
	public void run() {

		long timeOld=System.currentTimeMillis();
		while(gameRunning){
		
			
			long timeNow=System.currentTimeMillis();
			
			if(timeOld+1000<timeNow){
					time=time+((timeNow/1000)-(timeOld/1000));
					timeOld=timeNow;
			}
			
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}




	
	

	/**
	 * @return the cities
	 */
	public ArrayList<City> getCities() {
		return cities;
	}





	/**
	 * @param cities the cities to set
	 */
	public void setCities(ArrayList<City> cities) {
		this.cities = cities;
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
