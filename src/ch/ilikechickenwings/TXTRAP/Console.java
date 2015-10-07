package ch.ilikechickenwings.TXTRAP;

import java.awt.Color;

import ch.ilikechickenwings.TXTRAP.Interface.TTextPane;
import ch.ilikechickenwings.TXTRAP.CStyler;

public class Console {
	
	public static TTextPane tPane;
	
	public static CStyler standardOutput = new CStyler(Color.BLACK,Color.WHITE,true,false);
	public static CStyler standardListOutput = new CStyler(Color.BLACK,Color.WHITE,true,true);
	public static CStyler standardEvent = new CStyler(Color.WHITE,Color.BLACK,true,false);
	public static CStyler standardCommand = new CStyler(Color.BLACK,Color.WHITE,false,true);
	public static CStyler startOutput = new CStyler(Color.CYAN,Color.BLACK,true, false);
	public static CStyler startOutputComment =new CStyler(Color.RED,Color.BLACK,false, true);
	public static CStyler errorOutput =new CStyler(Color.RED,Color.WHITE,true, false);
	
	public Console(TTextPane tPane1){
		tPane=tPane1;
		
	}
	
	
	
	public static void log(String s){
		tPane.appendLine(s);
		tPane.setCaretPosition(tPane.getDocument().getLength());

		
		
	}
	
	public static void log(String s, CStyler c){
		tPane.appendLine(s,c);
		tPane.setCaretPosition(tPane.getDocument().getLength());

		
	}
	
	public static void logSingleLine(String s){
		tPane.append(s);
		tPane.setCaretPosition(tPane.getDocument().getLength());

		
		
	}
	
	public static void clearlog(){
		tPane.setText("");
		tPane.setCaretPosition(tPane.getDocument().getLength());

		
		
	}



	public static void logSingleLine(String string, CStyler styler) {
		tPane.append(string, styler);
		tPane.setCaretPosition(tPane.getDocument().getLength());
		
	}

}
