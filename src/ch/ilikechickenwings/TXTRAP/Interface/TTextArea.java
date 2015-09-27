package ch.ilikechickenwings.TXTRAP.Interface;

import javax.swing.JTextArea;

public class TTextArea extends JTextArea{
	
	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Calls the super constructor
	 */
	public TTextArea(String s){
		super(s);
	}
	
	/**Appends a given String s as a new line
	 * 
	 * @param s String s contain a string that should be appended to the JTextArea
	 */
	public void appendLine(String s){
		super.append("\n"+s);
		
	}

}
