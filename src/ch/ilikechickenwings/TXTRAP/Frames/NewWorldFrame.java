package ch.ilikechickenwings.TXTRAP.Frames;

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
		frame.log("You will create a new World, to load an old world write 'return'!");
		frame.log("Enter the name of your save (You have to remember this one!)");
		
		
		
	}
	
	
	@Override
	public void processInput(String[] s) {
		if(s[0].toLowerCase().equals("return")){
			frame.setProcessable(frame);
			frame.clearlog();
			frame.log("'new game' for a new game or load for an old game");

		}else{
		
			
			if(saveName==null){
				
				saveName=new StringBuilder();
				for(int i=0;i<s.length;i++){
					if(i!=0){
						saveName.append(" ");
					}
					saveName.append(s[i]);
				}
				frame.log("Enter your name, warrior! ->But keep in mind, you can't use commands as your name!");
		}else if(name==null){
			name = new StringBuilder();
			for(int i=0;i<s.length;i++){
				if(i!=0){
					name.append(" ");
				}
				name.append(s[i]);
			}
			frame.log("Enter your class name, warrior");
		}else if(gameClass==null){
			gameClass=new StringBuilder();
			
			for(int i=0;i<s.length;i++){
				if(i!=0){
					gameClass.append(" ");
				}
				gameClass.append(s[i]);
			}
			
			frame.log("Welcome to the new World, " + name.toString() + ", be prepard for a world full of adventures");
			
			WorldFrame f= new WorldFrame(frame);
			f.setPlayer(new Player(name.toString(),gameClass.toString()));
			frame.setProcessable(f);
			
		}

	}
	}
}
