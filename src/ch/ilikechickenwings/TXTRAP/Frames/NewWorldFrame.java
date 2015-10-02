package ch.ilikechickenwings.TXTRAP.Frames;

import java.io.File;

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
	
	private static boolean exists=false;
	
	public NewWorldFrame(MainFrame frame){
		this.frame=frame;
		Console.clearlog();
		Console.logSingleLine("You will create a new World, to load an old world write 'return'!",Console.startOutputComment);
		Console.log("Enter the name of your save (Only one word and you have to remember this one!)",Console.startOutput);
		
		
		
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
				
					saveName.append(s[0]);
					File file0 = new File((new StringBuilder()).append(System.getProperty("user.home")).append("/.TXTRAP/".concat(saveName.toString().concat(".dat"))).toString());
			        if(file0.exists()){
			        	Console.log("World already exists, want to overwrite it? (yes/no)",Console.errorOutput);
			        	exists=true;
			        }else{
				
				Console.log("");
				Console.log("Enter your name, warrior! ->But keep in mind, you can't use commands as your name!", Console.startOutput);
				
			        }
		}else if(exists){
			if(s[0].toLowerCase().equals("yes")||s[0].toLowerCase().equals("y")){
				Console.log("");
				Console.log("Enter your name, warrior! ->But keep in mind, you can't use commands as your name!", Console.startOutput);	
				exists=false;
			}else if(s[0].toLowerCase().equals("no")||s[0].toLowerCase().equals("n")){
				saveName=null;
				exists=false;
				Console.log("Enter a new name for your world",Console.startOutput);
				
			}
			
			
			
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
			Console.logSingleLine("Welcome to the new World, " + name.toString() + ", be prepared for a world full of adventures",Console.startOutput);
			
			WorldFrame f= new WorldFrame(frame,saveName.toString());
			f.setPlayer(new Player(name.toString(),gameClass.toString(),f.getCities().get(0),null));
			frame.setProcessable(f);
			
		}

	}
	}
}
