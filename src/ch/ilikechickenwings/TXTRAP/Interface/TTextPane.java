package ch.ilikechickenwings.TXTRAP.Interface;





import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import ch.ilikechickenwings.TXTRAP.CStyler;

public class TTextPane extends JTextPane{
	
	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Gets the styled Document of JTextPane to add Text/Colors
	 */
	private StyledDocument doc;
	
	/**
	 * Simple Attribute used in document style
	 */
	private SimpleAttributeSet keyWord;

	/**
	 * Calls the super constructor
	 */
	public TTextPane(String s){
		super();
		setText(s);
		
	}
	
	
	public TTextPane() {
		super();
		}


	/**Appends a given String s as a new line
	 * 
	 * @param s String s contain a string that should be appended to the JTextPane without any extras
	 */
	public void append(String s){
		

	//  Define a keyword attribute

	doc = this.getStyledDocument();
	

	//  Add some text

	try
	{
	    doc.insertString(doc.getLength(), s, null);
	}
	catch(Exception e) { System.out.println(e); }
		
	}

	
	public void append(String s, CStyler c){
		

	//  Define a keyword attribute

	doc = this.getStyledDocument();
	keyWord = new SimpleAttributeSet();
	StyleConstants.setForeground(keyWord, c.getForeground());
	StyleConstants.setBackground(keyWord, c.getBackground());
	StyleConstants.setBold(keyWord, c.isBold());
	StyleConstants.setItalic(keyWord, c.isItalic());

	//  Add some text

	try
	{
	    doc.insertString(doc.getLength(), s, keyWord);
	}
	catch(Exception e) { System.out.println(e); }
		
	}
	
	/** 
	 * @param s String s contain a string that should be appended to the JTextArea
	 */
	public void appendLine(String s){
		append("\n"+s);
		
	}
	
	public void appendLine(String s, CStyler c){
		append("\n"+s,c);
		
	}
	
	

}
