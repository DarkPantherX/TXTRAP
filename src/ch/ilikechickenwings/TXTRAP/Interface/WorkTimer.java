package ch.ilikechickenwings.TXTRAP.Interface;

import java.io.Serializable;

import ch.ilikechickenwings.TXTRAP.Console;
import ch.ilikechickenwings.TXTRAP.Entity.Item;
import ch.ilikechickenwings.TXTRAP.Entity.Player;
import ch.ilikechickenwings.TXTRAP.Frames.Processable;
import ch.ilikechickenwings.TXTRAP.Frames.WorldFrame;

public class WorkTimer implements Runnable, Processable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WorldFrame worldFrame;
	private Player player;
	private int hours;
	private long beginTime;
	private boolean running=true;
	
	
	
	public WorkTimer(Player player1, int hours1, WorldFrame wF){
		this.player=player1;
		this.hours=hours1;
		this.beginTime=wF.getTime();
		this.worldFrame=wF;
		worldFrame.getMainFrame().setProcessable(this);
		new Thread(this).start();
		Console.clearlog();
		Console.logSingleLine("You are now working " + hours +" hour(s)",Console.standardEvent);
		Console.log("Type help for more informations",Console.standardOutput);
		
	}
	
	
	@Override
	public void run() {
		
		while(running){
			if(beginTime+(hours*60)<worldFrame.getTime()){
				boolean done=false;
				for(Item i : player.getInventory()){
					if(i.getName().toLowerCase().equals("gold")){
						i.setQuantity(i.getQuantity()+100*hours);
						done=true;
					}
					
				}
				if(!done){
					player.getInventory().add(new Item("Gold",100*hours,1));
					
				}
				worldFrame.getMainFrame().setProcessable(worldFrame);
				running=false;
				Console.clearlog();
				Console.logSingleLine("You got " +hours*100+" Gold for your work",Console.standardEvent);
			}else{
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		
	}


	@Override
	public void processInput(String[] s) {
		switch (s[0].toLowerCase()){
		case "help":
			Console.log("You are working right now, you can't do anything right now. You can stop working but you won't get any gold for your work. Use 'stop' to stop working. Use 'status' to see how long you have left.",Console.standardOutput);
			break;
		case "stop":
			Console.clearlog();
			Console.logSingleLine("You stoped working before the work ended",Console.standardEvent);
			running=false;
			worldFrame.getMainFrame().setProcessable(worldFrame);
			break;
		case "status":
			Console.log("Work hours left: " + (beginTime+(hours*60)-worldFrame.getTime())+ " from "+ hours*60+" minutes",Console.standardOutput);
			break;
		default:
			Console.log("Command not found",Console.errorOutput);
			break;
		
		
		}
		
	}

}
