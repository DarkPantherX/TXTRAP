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
	}

	@Override
	public void processInput(String[] s) {
		switch (s[0].toLowerCase()){
		case "help":
			Console.log("Available commands:"
					+"\n fuck <name of whore> -> shows the items to sell"
					+"\n showgirls -> shows the name of the girls"
					+"\n sell <item name> <optional: quantity> -> shows the items to sell"
					+"\n stop -> Stops the interaction");
			break;
		case "fuck":
			for(int i=0;i<getHumans().size();i++){
				Human h = (Human) getHumans().get(i);
				if(s[1].toLowerCase().equals(h.getName())||h instanceof Whore){
					Whore w = (Whore)h;
					Console.log("You fucked " +h.getName(),Console.standartEvent);
					w.setTimesHadSex(w.getTimesHadSex()+1);
					
				}
				
			}
			
			break;
		case "talk":
			for(int i=0;i<getHumans().size();i++){
				Human h = (Human) getHumans().get(i);
				if(s[1].toLowerCase().equals(h.getName())||h instanceof Whore){
			Whore w= (Whore) h;
			Console.log(w.getResponseLine());
			}
				}
				
				break;
		case "stop":
			stopInteract(null);
			break;
		default:
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
