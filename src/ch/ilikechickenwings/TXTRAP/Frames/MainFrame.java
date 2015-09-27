package ch.ilikechickenwings.TXTRAP.Frames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import ch.ilikechickenwings.TXTRAP.Frames.NewWorldFrame;
import ch.ilikechickenwings.TXTRAP.Interface.TTextArea;

public class MainFrame implements ActionListener, Processable {

	
	//public variables: Don't need getters and setters to access
	
	/** This Variable contains the Main Frame for our game*/
	public JFrame frame;
	/**This is the lower text field, it is for our input and listens to the "Enter"-key 
	 * If you press the enter key, it will fire an ActionEvent, which is then given to the Listener in this Class and so to the actionPerformed(ActionEvent e) method
	 */
	public JTextField tField;
	/** This button is the only button on the user interface, it has the same AcitonListener and also fires an ActionEvent used in this class*/
	public JButton but;
	/** The name of the game */
	public static final String gameName = "TXTRAP";

	
	
	//private variables: Need getters and setter to access
	
	/** The World of the game*/
	private Processable processable;
	/**This is the upper TextArea, it acts like a console*/
	private TTextArea tArea;
	
	
	/**
	 * Main Class, with this class starts the frame and engine
	 */
	MainFrame() {

		frame = new JFrame(gameName);
		frame.setSize(600, 400);
		frame.setLayout(new GridBagLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		but = new JButton("Enter");
		but.addActionListener(this);

		tArea = new TTextArea("Let's go");
		tArea.setEditable(false);

		tArea.setLineWrap(true);
		tArea.setWrapStyleWord(true);

		JScrollPane sp = new JScrollPane(tArea,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		tField = new JTextField();
		tField.addActionListener(this);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 0.9;
		gbc.gridwidth = 2;
		frame.add(sp, gbc);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridy = 1;
		gbc.weightx = 0.9;
		gbc.weighty = 0.1;
		gbc.gridwidth = 1;
		frame.add(tField, gbc);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridy = 1;
		gbc.gridx = 1;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		frame.add(but, gbc);

		frame.setVisible(true);
		
		processable=this;
		
		log("Welcome in "+gameName+" a hard place for all kinds of folks!");
		log("Do you want to 'load' into a game or do you want a new game with 'new Game'? ");
		
	}
	
	
	
	public void log(String s){
		tArea.appendLine(s);
		
		
	}
	
	public void logSingleLine(String s){
		tArea.append(s);
		
		
	}
	
	public void clearlog(){
		tArea.setText("");
		
		
	}
	
	
	@Override
	public void processInput(String[] s) {
		
		switch (s[0].toLowerCase()){
			case "new": 
				if(s[1].toLowerCase().equals("game")){
				processable = new NewWorldFrame(this);
				}
					break;
			case "exit":
				System.exit(0);
					break;
			default: tArea.appendLine("Command not found");
					break;

		}
			
		
	}
	
	
	
	

	public static void main(String[] args) {
		new MainFrame();

	}

	@Override
	/** 
	 * actionPerformed method is called when an ActionEvent is fired, like pressing enter in the JTextField or pressing the enter button
	 * */
	public void actionPerformed(ActionEvent e) {
		

		if (e.getSource().equals(but) || e.getSource().equals(tField)) {

			if (!tField.getText().trim().equals("")) {

				tArea.append("\n" + tField.getText());
				tArea.setCaretPosition(tArea.getDocument().getLength());
				String s[]=tField.getText().split(" ");
				
				processable.processInput(s);
				
				tField.setText("");
			}
		}
		

	}
	
	


	/**
	 * @return the processable
	 */
	public Processable getProcessable() {
		return processable;
	}


	/**
	 * @param procesable the processable to set
	 */
	public void setProcessable(Processable processable) {
		this.processable = processable;
	}


	/**
	 * @return the tArea
	 */
	public TTextArea gettArea() {
		return tArea;
	}




	/**
	 * @param tArea the tArea to set
	 */
	public void settArea(TTextArea tArea) {
		this.tArea = tArea;
	}


}