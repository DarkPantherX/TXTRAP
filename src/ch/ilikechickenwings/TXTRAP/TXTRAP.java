package ch.ilikechickenwings.TXTRAP;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class TXTRAP implements ActionListener{
	
	public JFrame frame;
	public JTextArea tArea;
	public JTextField tField;
	public JButton but;
	
	public static final String gameName="TXTRAP";
	
	TXTRAP(){
		frame= new JFrame(gameName);
		frame.setSize(500, 500);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(1,2));
		pane.setSize(500,400);
		
		but = new JButton("Enter");
		but.setSize(20, 20);
		but.addActionListener(this);
		
		tArea= new JTextArea("Let's go");
		tArea.setEditable(false);
		tArea.setSize(500, 200);
		
		tField = new JTextField();
		tField.addActionListener(this);
		
		pane.add(tField);
		pane.add(but);
		
		frame.add(tArea, BorderLayout.CENTER);
		frame.add(pane, BorderLayout.SOUTH);
		
		frame.setVisible(true);
		
		
		
		
	}
	
	
	
	public static void main(String[] args) {
		new TXTRAP();

	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(but)||e.getSource().equals(tField)){
			
			if(!tField.getText().trim().equals("")){
			
			tArea.append("\n"+tField.getText());
			tArea.setCaretPosition(tArea.getDocument().getLength());
			
			tField.setText("");
			}
		}
	}
	

}
