package ch.ilikechickenwings.TXTRAP.Frames;

import ch.ilikechickenwings.TXTRAP.Console;
import ch.ilikechickenwings.TXTRAP.Frames.MainFrame;
import ch.ilikechickenwings.TXTRAP.Frames.Processable;
import ch.ilikechickenwings.TXTRAP.Frames.WorldFrame;
import ch.ilikechickenwings.TXTRAP.Entity.Player;

public class NewWorldFrame implements Processable {

	private MainFrame frame;
	
	private StringBuilder name;
	private StringBuilder gameClass;
	private StringBuilder saveName;
	
	public NewWorldFrame(MainFrame frame){
		this.frame=frame;
		Console.clearlog();
		Console.logSingleLine("You will create a new World, to load an old world write 'return'!",Console.startOutputComment);
		Console.log("Enter the name of your save (You have to remember this one!)",Console.startOutput);
		
		
		
	}
	
	
	@Override
	public void processInput(String[] s) {
		if(s[0].toLowerCase().equals("return")){
			frame.setProcessable(frame);
			Console.clearlog();
			Console.logSingleLine("'new game' for a new game or load for an old game",Console.startOutput);

		}else{
		
			
			if(saveName==null){
				
				saveName=new StringBuilder();
				for(int i=0;i<s.length;i++){
					if(i!=0){
						saveName.append(" ");
					}
					saveName.append(s[i]);
				}
				Console.log("");
				Console.log("Enter your name, warrior! ->But keep in mind, you can't use commands as your name!", Console.startOutput);
		}else if(name==null){
			name = new StringBuilder();
			for(int i=0;i<s.length;i++){
				if(i!=0){
					name.append(" ");
				}
				name.append(s[i]);
			}
			Console.log("");
			Console.log("Enter your class name, warrior",Console.startOutput);
		}else if(gameClass==null){
			gameClass=new StringBuilder();
			
			for(int i=0;i<s.length;i++){
				if(i!=0){
					gameClass.append(" ");
				}
				gameClass.append(s[i]);
			}
			Console.clearlog();
			Console.logSingleLine("Welcome to the new World, " + name.toString() + ", be prepard for a world full of adventures",Console.startOutput);
			
			WorldFrame f= new WorldFrame(frame);
			f.setPlayer(new Player(name.toString(),gameClass.toString()));
			frame.setProcessable(f);
			
		}

	}
	}
}
