package ch.ilikechickenwings.TXTRAP;

import java.awt.Color;

import ch.ilikechickenwings.TXTRAP.Interface.TTextPane;
import ch.ilikechickenwings.TXTRAP.CStyler;

public class Console {
	
	public static TTextPane tPane;
	
	public static CStyler standartOutput = new CStyler(Color.BLACK,Color.WHITE,true,false);
	public static CStyler standartListOutput = new CStyler(Color.BLACK,Color.WHITE,true,true);
	public static CStyler standartEvent = new CStyler(Color.WHITE,Color.BLACK,true,false);
	public static CStyler standartCommand = new CStyler(Color.BLACK,Color.WHITE,false,true);
	public static CStyler startOutput = new CStyler(Color.CYAN,Color.BLACK,true, true);
	public static CStyler startOutputComment =new CStyler(Color.RED,Color.BLACK,false, true);
	public static CStyler errorOutput =new CStyler(Color.RED,Color.WHITE,true, false);
	
	public Console(TTextPane tPane1){
		tPane=tPane1;
		
	}
	
	
	
	public static void log(String s){
		tPane.appendLine(s);
		
		
	}
	
	public static void log(String s, CStyler c){
		tPane.appendLine(s,c);
		
	}
	
	public static void logSingleLine(String s){
		tPane.append(s);
		
		
	}
	
	public static void clearlog(){
		tPane.setText("");
		
		
	}



	public static void logSingleLine(String string, CStyler styler) {
		tPane.append(string, styler);
		
	}

}
