package ch.ilikechickenwings.TXTRAP.Frames;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import ch.ilikechickenwings.TXTRAP.City;
import ch.ilikechickenwings.TXTRAP.Command;
import ch.ilikechickenwings.TXTRAP.Console;
import ch.ilikechickenwings.TXTRAP.Frames.Processable;
import ch.ilikechickenwings.TXTRAP.Interface.WorkTimer;
import ch.ilikechickenwings.TXTRAP.Places.Market;
import ch.ilikechickenwings.TXTRAP.Places.Place;
import ch.ilikechickenwings.TXTRAP.Places.Whorehouse;
import ch.ilikechickenwings.TXTRAP.Entity.Bandit;
import ch.ilikechickenwings.TXTRAP.Entity.Whore;
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
	private ArrayList<Command> commands = new ArrayList<Command>();
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
		Console.log("Type help for available commands", Console.standardOutput);
	}
	
	



	/** 
	 * Creates new World with player and MainFrame
	 */
	public WorldFrame(Player player, MainFrame mF, String name1){
		this(mF,name1);
		setPlayer(player);

		
		Console.log(player.getName()+" wakes up in " + player.getCity().getCityName(), Console.standardOutput);
		Console.log("Type help for available commands" , Console.standardOutput);
		
	}
	
	
	
	private void createWorld() {
		
		commands.add(new Command("help", "command", "Shows all the commands in this place"));
		commands.add(new Command("map", "", "Shows the surrounding cities", "You can go to these cities by typing 'goto'"));
		commands.add(new Command("goto", "cityname", "Go to the chosen city","To see the available cities type 'lookaround'."));
		commands.add(new Command("status", "Your life situation"));
		commands.add(new Command("inventory", "Your inventory"));
		commands.add(new Command("time", "The current time"));
		commands.add(new Command("interact", "place", "Interact with a place in this city"));
		commands.add(new Command("work", "hours","Work as a stable boy for 100 gold per hour"));
		commands.add(new Command("save", "", "Saves the game"));
		commands.add(new Command("rite", "amount of health", "Sacrifice your health to gain gold from SATAN himself","To see your life enter 'status'."));
		// TODO: Lookaround.. and stuff
		
		City city=new City("Tamariel");
		Market m = new Market(this);
		m.getItems().add(new Item("Sword",0,50));
		m.getHumans().add(new Bandit(100, "Hungry Tom", city, "", m));
		city.getPlaces().add(m);
		cities.add(city);
		
		city = new City("Bananistan");
		m = new Market(this);
		m.getItems().add(new Item("banana",0,10));
		city.getPlaces().add(m);
		cities.add(city);
		
		city = new City("Eschenbach");
		Whorehouse h= new Whorehouse(this);
		Whore h1= new Whore(100, "Anna", city, "", h);
		h.getHumans().add(h1);
		city.getPlaces().add(h);
		cities.add(city);
		
	}

	
	@Override
	public void processInput(String[] s) {
		
		switch (s[0].toLowerCase()){
			case "attack":
					Console.log("You attacked: " + s[1]);
				
					break;
			case "help":
				if(s.length>1){ // Input of the form: help goto
					Command.printHelp(s[1],commands);
				}else{ // help
					Command.printHelp(commands);
				}
				
				/*Console.log("Available commands: \n map -> Shows cities "
						+ "\n goto <cityname> -> You go to the chosen city"
						+ "\n status -> Tells you how many lifes you have left"
						+ "\n inventory -> Shows you your inventory"
						+ "\n time -> Shows the current time"
						+ "\n interact <place> -> Interact with a place in this city"
						+ "\n work <hours> -> Work to get 100 gold per hour"
						+ "\n save -> Saves the game"
						+ "\n rite <amount of health> -> Sacrifice a bit of your health to gain gold from SATAN!", Console.standardOutput);
				*/
					break;
			case "goto":
				if(s.length>1){
					for(City city : cities){
						if(city.getCityName().toLowerCase().equals(s[1].toLowerCase())){
							player.setCity(city);
							Console.log("You went to: " + city.getCityName(), Console.standardEvent);
						}
					}
				}
				break;
			case "map":
				Console.log("Cities here:", Console.standardOutput);
				City city;
				for(int in=0; in<cities.size();in++){
					city= (City)cities.get(in);
					Console.logSingleLine(" "+city.getCityName()+",",Console.standardListOutput);
				}
					break;
			case "mine":
					break;
			case "sleep":
					break;
			
			case "status":
				Console.log("Your name is " +player.getName()+" the "+ player.getGameClass(),Console.standardOutput);
				Console.log("Health: ", Console.standardOutput);
				int h1= (int)(player.getHealth()/player.getMaxHealth()*10);
				
				for(int i1=0;i1<10;i1++){
					if(h1>0){
						Console.logSingleLine("O");
						h1--;
						
					}else{
						Console.logSingleLine("X");
					}
				}
				
				Console.logSingleLine(" --> "+Float.toString(player.getHealth())+"% life left", Console.standardOutput);
				
				Console.log("You are in the great "+ player.getCity().getCityName()+" at the moment", Console.standardOutput);
				break;
			case "inventory":
				Console.log("In your Inventory is: ", Console.standardOutput);

					if(player.getInventory().size()>0){
						for(Item mm : player.getInventory()){
						if(mm.getQuantity()==0){
							player.getInventory().remove(mm);
						}else{
						Console.log("->"+Integer.toString(mm.getQuantity())+"x "+mm.getName(), Console.standardListOutput);
						}
						}
					}else{
						Console.log("-->nothing<-- (poor as fuck...)", Console.standardListOutput);
					}
				
					break;
			case "time":
					int minTime= (int) time%60;
					int hourTime=(int) (time/60)%24;
					int dayTime=(int)((time/60)/24)%30;
					int monthTime=(int)(((time/60)/24)/30)%12;
					long yearTime=(long)(((time/60)/24)/30)/12;
					
					Console.log("It is: ",Console.standardOutput);
					if(hourTime/10==0){Console.logSingleLine(Integer.toString(0),Console.standardListOutput);}
					Console.logSingleLine(hourTime+":",Console.standardListOutput);
					if(minTime/10==0){Console.logSingleLine(Integer.toString(0),Console.standardListOutput);}
					Console.logSingleLine(minTime+ " - ",Console.standardListOutput);
					Console.logSingleLine("On the ",Console.standardOutput);Console.logSingleLine(Integer.toString(dayTime+1),Console.standardListOutput);Console.logSingleLine("th day ",Console.standardOutput);
					Console.logSingleLine("on the ",Console.standardOutput);Console.logSingleLine(Integer.toString(monthTime+1),Console.standardListOutput);Console.logSingleLine("th month ",Console.standardOutput);
					Console.logSingleLine("in the ",Console.standardOutput);Console.logSingleLine(Integer.toString((int)yearTime+1),Console.standardListOutput);Console.logSingleLine("th year ",Console.standardOutput);
					
					
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
			    
			    Console.log("Game saved",Console.standardEvent);;
			    break;
			    
			case "interact":
			if(s.length>1){
				Place p1=null;
				for(int i3=0;i3<player.getCity().getPlaces().size();i3++){
					
					Place p = (Place) player.getCity().getPlaces().get(i3);
					System.out.println(p);
					if (s[1].toLowerCase().equals(p.getName().toLowerCase())){
						p1=p;
					}
					
				if(p1!=null){
					player.setPlace(p1);
					player.getPlace().interact(player);
					
					}else{
						Console.log("Place not found",Console.errorOutput);
						
					}
				}
				}else{
					Console.log("use: interact <place>",Console.errorOutput);
				}
				break;
			case "work":
				if(s.length==2){
					new WorkTimer(player,Integer.parseInt(s[1]),this);
				}else{
					Console.log("Command was used wrong: work <hours>");
				}
				break;
			case "rite":
				if(s.length==2 ){
					int a = Integer.parseInt(s[1]);
					if(player.getHealth()-a*10<0){
						Console.log("You cannot sacrifice that amount of health. ");
					}else{
					player.getDamaged(10*a);
					boolean done=false;
					for(Item ite : player.getInventory()){
						if(ite.getName().toLowerCase().equals("gold")){
							ite.setQuantity(ite.getQuantity()+100*a);
							Console.log("You sacrificed "+a*10+"% of your health to gain "+a*100+" gold.");
							done=true;
							break;
						}
						
					}
					if(!done){
					
						Item as = new Item("Gold",100*a,1);
						player.getInventory().add(as);
						Console.log("You sacrificed "+a*10+"% of your health to gain "+a*100+" gold.");

					}
					}
					}else{
					Console.log("Command was used wrong: rite <amount of health>");	
				}
				break;
			default: Console.log("Command not found",Console.errorOutput);
					break;

		}
			
		
	}
	
	
	
	
	public void loadedGame() {
		Console.clearlog();
		Console.logSingleLine("Loaded", Console.standardEvent);
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
	 * @return the time
	 */
	public long getTime() {
		return time;
	}





	/**
	 * @param time the time to set
	 */
	public void setTime(long time) {
		this.time = time;
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
