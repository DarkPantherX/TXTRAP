package ch.ilikechickenwings.TXTRAP.Frames;

import ch.ilikechickenwings.TXTRAP.Console;

public class LoginFrame implements Processable{
	
	OnlineFrame oF;
	
	public LoginFrame(OnlineFrame onlineFrame) {
		oF=onlineFrame;
		Console.log("Enter your ip followed by port <ip>:<port>");
	}

	@Override
	public void processInput(String[] s) {
		if(s[0].contains(":")){
		String s1[]= s[0].split(":");
		oF.ip=s1[0];
		oF.port=Integer.parseInt(s1[1]);
		oF.getOnline();
		oF.getMainFrame().setProcessable(oF);
		}
	}
	
	
}
