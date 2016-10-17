package ch.ilikechickenwings.TXTRAP.Places;



import ch.ilikechickenwings.TXTRAP.Console;
import ch.ilikechickenwings.TXTRAP.Entity.Human;
import ch.ilikechickenwings.TXTRAP.Entity.Player;
import ch.ilikechickenwings.TXTRAP.Entity.Whore;
import ch.ilikechickenwings.TXTRAP.Frames.WorldFrame;

public class Whorehouse extends Place{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Whorehouse(WorldFrame wF) {
		setWorldFrame(wF);
		setName("Whorehouse");
	}

	@Override
	public void processInput(String[] s) {
		switch (s[0].toLowerCase()){
		case "help":
			Console.log("Available commands:"
					+"\n fuck <name of whore> -> shows the items to sell"
					+"\n showgirls -> shows the name of the girls"
			        +"\n leave -> return to the city");
			break;
		case "fuck":
			for(Human h: getHumans()){
				if(s[1].toLowerCase().equals(h.getName().toLowerCase())&&h instanceof Whore){
					Whore w = (Whore)h;
					Console.log("You fucked " +h.getName(),Console.standardEvent);
					w.setTimesHadSex(w.getTimesHadSex()+1);
					
				}else{
					
					Console.log("Whore not found", Console.errorOutput);
				}
				
			}
			
			break;
		case "showgirls":
			Console.log("There are following whores available: ",Console.standardOutput);
			for(Human h : getHumans()){
				if(h instanceof Whore){
					Console.logSingleLine(h.getName()+" ",Console.standardListOutput);
					
				}
				
			}
			
			break;
		case "talk":
			for(Human h : getHumans()){
				if(s[1].toLowerCase().equals(h.getName().toLowerCase())){
			Console.log(h.getResponseLine());
			break;
			}
				}
				
				break;
		case "leave":
			stopInteract(null);
			getWorldFrame().getPlayer().setPlace(null);
			Console.clearlog();
			Console.logSingleLine("You left the whorehouse",Console.standardEvent);
			break;
		default:
			Console.log("Command not found",Console.errorOutput);
			break;
			
		}
		
	}

	@Override
	public void interact(Player player) {
		getWorldFrame().getMainFrame().setProcessable(this);
		Console.clearlog();
		Console.logSingleLine("Welcome to the whorehouse, write 'help' for more information");
		
	}

	@Override
	public void stopInteract(Player player) {
		getWorldFrame().getMainFrame().setProcessable(getWorldFrame());
		
	}

}
