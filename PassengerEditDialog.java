/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* PassengerEditDialog.java
* Description : class that create a dialog
* to edit passenger

* @author Ignaz Maula Ibrahim
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PassengerEditDialog extends JDialog 
implements ActionListener{
	//instance variable
	private JLabel name, numAdults, numChildren, bookingRef;
	private JTextField nameTF, 
	numAdultsTF, numChildrenTF, bookingRefTF;
	private JButton okButton, cancelButton, clearButton;

	private Passenger passenger;

	//custom font
	final Font calibri14 = new Font("Calibri", Font.PLAIN, 14);

	//constructor
	public PassengerEditDialog(JFrame parent, Passenger passenger) {
		super(parent, true);
		this.passenger = passenger;
		
		setTitle("Edit Passenger");
		setSize(300, 150);
		setLocation(500,300);

		//create label
		bookingRef = new JLabel("Booking Ref   ");
		bookingRef.setFont(calibri14);
		name = new JLabel("Passenger Name   ");
		name.setFont(calibri14);
		numAdults = new JLabel("Num of Adults   ");
		numAdults.setFont(calibri14);
		numChildren = new JLabel("Num of Children   ");
		numChildren.setFont(calibri14);

		//create text field
		bookingRefTF = new JTextField(15);
		String bookingRefString = 
		String.valueOf(passenger.getBookingRef());
		bookingRefTF.setText(bookingRefString);
		bookingRefTF.setEditable(false);
		nameTF = new JTextField(15);
		nameTF.setText(passenger.getName());
		numAdultsTF = new JTextField(15);
		String numAdultsString = 
		String.valueOf(passenger.getNumAdults());
		numAdultsTF.setText(numAdultsString);
		//allow to edit numAdults only if 
		//the passenger not have flight to book
		if(passenger.getFlightArray().size() != 0)
			numAdultsTF.setEditable(false);
		numChildrenTF = new JTextField(15);
		String numChildrenString =
		 String.valueOf(passenger.getNumChildren());
		numChildrenTF.setText(numChildrenString);
		//allow to edit numChildren only if 
		//the passenger not have flight to book
		if(passenger.getFlightArray().size() != 0)
			numChildrenTF.setEditable(false);
		
		//create button
		okButton = new JButton("OK");
		okButton.setFont(calibri14);
		okButton.setBackground(Color.WHITE);
		clearButton = new JButton("Clear");
		clearButton.setFont(calibri14);
		clearButton.setBackground(Color.WHITE);
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(calibri14);
		cancelButton.setBackground(Color.WHITE);

		//add component to panel
		JPanel panel1 = new JPanel(new GridLayout(0,1,0,8));
		panel1.add(bookingRef);
		panel1.add(name);
		panel1.add(numAdults);
		panel1.add(numChildren);

		JPanel panel2 = new JPanel(new GridLayout(0,1,0,4));
		panel2.add(bookingRefTF);
		panel2.add(nameTF);
		panel2.add(numAdultsTF);
		panel2.add(numChildrenTF);

		JPanel panel3 = new JPanel();
		panel3.add(panel1);
		panel3.add(panel2);

		JPanel panel4 = new JPanel();
		panel4.add(okButton);
		panel4.add(clearButton);
		panel4.add(cancelButton);

		getContentPane().add(panel3, "North");
		getContentPane().add(panel4, "Center");

		//add action listener
		okButton.addActionListener(this);
		clearButton.addActionListener(this);
		cancelButton.addActionListener(this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == okButton) {
			//get all information from the textfield
			String name = nameTF.getText().trim();
			String numAdultsString = numAdultsTF.getText().trim();
			String numChildrenString = numChildrenTF.getText().trim();
			
			//parse int
			int numAdults = Integer.parseInt(numAdultsString);
			int numChildren = Integer.parseInt(numChildrenString);

			//edit selected passenger with the given data
			passenger.setName(name);
			passenger.setNumAdults(numAdults);
			passenger.setNumChildren(numChildren);

			setVisible(false);
		}
		//reset all textfield
		else if(e.getSource() == clearButton) {
			nameTF.setText("");
			numAdultsTF.setText("");
			numChildrenTF.setText("");
		}
		else {
			setVisible(false);
		}	
	}
	public Passenger getPassenger() {
		return passenger;
	}
}