package ch.ilikechickenwings.TXTRAP;
import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;


public class CStyler implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color foreground;
	private Color background;
	private boolean bold;
	private boolean italic;
	private Font font;
	
	
	public CStyler(Color f, Color b, boolean bo, boolean it){
		setForeground(f);
		setBackground(b);
		setBold(bo);
		setItalic(it);
		setFont(new Font("Courier New", Font.PLAIN, 12));
	}
	
	
	/**
	 * @return the foreground
	 */
	public Color getForeground() {
		return foreground;
	}
	/**
	 * @param foreground the foreground to set
	 */
	public void setForeground(Color foreground) {
		this.foreground = foreground;
	}
	/**
	 * @return the background
	 */
	public Color getBackground() {
		return background;
	}
	/**
	 * @param background the background to set
	 */
	public void setBackground(Color background) {
		this.background = background;
	}
	/**
	 * @return the bold
	 */
	public boolean isBold() {
		return bold;
	}
	/**
	 * @param bold the bold to set
	 */
	public void setBold(boolean bold) {
		this.bold = bold;
	}
	/**
	 * @return the italic
	 */
	public boolean isItalic() {
		return italic;
	}
	/**
	 * @param italic the italic to set
	 */
	public void setItalic(boolean italic) {
		this.italic = italic;
	}


	public Font getFont() {
		return font;
	}

	public String getFontFamily(){
		return font.getFamily();
	}

	public void setFont(Font font) {
		this.font = font;
	}
	
	
	
	
}
