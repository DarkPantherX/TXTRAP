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
					+"\n showgirls -> shows the name of the girls");
			break;
		case "fuck":
			for(int i=0;i<getHumans().size();i++){
				Human h = (Human) getHumans().get(i);
				if(s[1].toLowerCase().equals(h.getName().toLowerCase())&&h instanceof Whore){
					Whore w = (Whore)h;
					Console.log("You fucked " +h.getName(),Console.standartEvent);
					w.setTimesHadSex(w.getTimesHadSex()+1);
					
				}else{
					
					Console.log("Whore not found", Console.errorOutput);
				}
				
			}
			
			break;
		case "showgirls":
			Console.log("There are following whores available: ",Console.standartOutput);
			for(int i=0;i<getHumans().size();i++){
				Human h = (Human) getHumans().get(i);
				if(h instanceof Whore){
					Console.logSingleLine(h.getName()+" ",Console.standartListOutput);
					
				}
				
			}
			
			break;
		case "talk":
			for(int i=0;i<getHumans().size();i++){
				Human h = (Human) getHumans().get(i);
				if(s[1].toLowerCase().equals(h.getName().toLowerCase())&&h instanceof Whore){
			Whore w= (Whore) h;
			Console.log(w.getResponseLine());
			}
				}
				
				break;
		case "leave":
			stopInteract(null);
			getWorldFrame().getPlayer().setPlace(null);
			Console.clearlog();
			Console.logSingleLine("You left the whorehouse",Console.standartEvent);
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
