/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* PassengerAddDialog.java
* Description : class that create a dialog
* to add new passenger

* @author Ignaz Maula Ibrahim
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PassengerAddDialog extends JDialog 
implements ActionListener{
	//instance variable
	private JLabel name, numAdults, numChildren;
	private JTextField nameTF, numAdultsTF, numChildrenTF;
	private JButton okButton, cancelButton;

	private Passenger passenger;

	//custom font
	final Font calibri14 = new Font("Calibri", Font.PLAIN, 14);

	public PassengerAddDialog(JFrame parent) {
		super(parent, true);
		passenger = null;
		
		setTitle("Add New Passenger");
		setSize(300, 150);
		setLocation(500,300);

		//create label
		name = new JLabel("Passenger Name   ");
		name.setFont(calibri14);
		numAdults = new JLabel("Num of Adults   ");
		numAdults.setFont(calibri14);
		numChildren = new JLabel("Num of Children   ");
		numChildren.setFont(calibri14);

		//create text field
		nameTF = new JTextField(15);
		numAdultsTF = new JTextField(15);
		numChildrenTF = new JTextField(15);

		//create button
		okButton = new JButton("OK");
		okButton.setFont(calibri14);
		okButton.setBackground(Color.WHITE);
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(calibri14);
		cancelButton.setBackground(Color.WHITE);

		//add component to panel
		JPanel panel1 = new JPanel(new GridLayout(0,1,0,8));
		panel1.add(name);
		panel1.add(numAdults);
		panel1.add(numChildren);

		JPanel panel2 = new JPanel(new GridLayout(0,1,0,4));
		panel2.add(nameTF);
		panel2.add(numAdultsTF);
		panel2.add(numChildrenTF);

		JPanel panel3 = new JPanel();
		panel3.add(panel1);
		panel3.add(panel2);

		JPanel panel4 = new JPanel();
		panel4.add(okButton);
		panel4.add(cancelButton);

		getContentPane().add(panel3, "North");
		getContentPane().add(panel4, "Center");

		//add action listener
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == okButton) {
			//get all information from the textfield
			String name = nameTF.getText().trim();
			String numAdultsString = numAdultsTF.getText().trim();
			String numChildrenString = numChildrenTF.getText().trim();
			
			if(name.equals("") || numAdultsString.equals("") || numChildrenString.equals("")) {
				JOptionPane.showMessageDialog
				(this, "[Error] Please fill all field", 
				"Error Message", JOptionPane.ERROR_MESSAGE);
				return ;
			}
			//parse int
			int numAdults = 0;
			try {
			numAdults = Integer.parseInt(numAdultsString);
			}
			catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog
				(this, "[Error] Num of Adults/Num of Children \ngiven not an integer", "Error Message", JOptionPane.ERROR_MESSAGE);
				return ;
			}
			int numChildren = 0;
			try {
			numChildren = Integer.parseInt(numChildrenString);
			}
			catch(NumberFormatException nfe) {
				JOptionPane.showMessageDialog
				(this, "[Error] Num of Adults/Num of Children \ngiven not an integer", "Error Message", JOptionPane.ERROR_MESSAGE);
				return ;
			}
			if (numAdults < 0 || numChildren < 0) {
				JOptionPane.showMessageDialog
				(this, "[Error] Num of Adults/Num of Children \ngiven not a positive integer", "Error Message", JOptionPane.ERROR_MESSAGE);
				return ;
			}

			//create passenger with the given attributes
			passenger = new Passenger(name, numAdults, numChildren, 0, 0);

			setVisible(false);
		}
		else {
			passenger = null;
			setVisible(false);
		}	
	}
	public Passenger getPassenger() {
		return passenger;
	}
}