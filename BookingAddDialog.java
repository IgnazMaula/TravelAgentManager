/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* BookingAddDialog.java
* Description : class that create a dialog
* for booking a flight

* @author Ignaz Maula Ibrahim
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BookingAddDialog 
extends JDialog implements ActionListener{
	//instance variable
	private JLabel bookingRef, name, flightNo;
	private JTextField bookingRefTF, nameTF, flightNoTF;
	private JButton okButton, cancelButton;

	private TravelAgent travelAgent;
	private Passenger passenger;

	//costum font
	final Font calibri14 = new Font("Calibri", Font.PLAIN, 14);

	//constructor
	public BookingAddDialog(JFrame parent, 
	Passenger passenger, TravelAgent travelAgent) {
		super(parent, true);
		this.travelAgent = travelAgent;
		this.passenger = passenger;
		
		setTitle("Booking Flight");
		setSize(300, 150);
		setLocation(500,300);

		//create label
		bookingRef = new JLabel("Booking Ref   ");
		bookingRef.setFont(calibri14);
		name = new JLabel("Passanger Name  ");
		name.setFont(calibri14);
		flightNo = new JLabel("Enter Flight No to Book  ");
		flightNo.setFont(calibri14);

		//create text field
		bookingRefTF = new JTextField(15);
		String bookingRefTFString = 
		String.valueOf(passenger.getBookingRef());
		bookingRefTF.setText(bookingRefTFString);
		bookingRefTF.setEditable(false);
		nameTF = new JTextField(15);
		nameTF.setText(passenger.getName());
		nameTF.setEditable(false);
		flightNoTF = new JTextField(15);

		//create button
		okButton = new JButton("OK");
		okButton.setFont(calibri14);
		okButton.setBackground(Color.WHITE);
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(calibri14);
		cancelButton.setBackground(Color.WHITE);

		//add component to panel
		JPanel panel1 = new JPanel(new GridLayout(0,1,0,8));
		panel1.add(bookingRef);
		panel1.add(name);
		panel1.add(flightNo);

		JPanel panel2 = new JPanel(new GridLayout(0,1,0,4));
		panel2.add(bookingRefTF);
		panel2.add(nameTF);
		panel2.add(flightNoTF);

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
			String flightNoString = flightNoTF.getText().trim();
			int flightNo = Integer.parseInt(flightNoString);

			//search selected flight
			Flight selectedFlight = null;
			for (Flight i : travelAgent.getFlightArray()) {
				if(i.getFlightNo() == flightNo)
					selectedFlight = i;
			}
			if(selectedFlight != null) {
				boolean alreadyBook = false;
				//search if selectedFlight already booked
				//by this passenger
				for(Flight i : passenger.getFlightArray()) {
					if(selectedFlight.getFlightNo() == 
						i.getFlightNo())
						alreadyBook = true;
				}
				if (alreadyBook) {
					JOptionPane.showMessageDialog
					(this, "You have booked this flight before!");
				}
				else {
					//check if selected flight have same date as 
					//previous booked flight
					boolean sameDate = false;
					for(Flight i : passenger.getFlightArray()) {
						if(selectedFlight.getDate().equals(i.getDate()))
							sameDate = true;
					}
					if(sameDate) {
						//give option to keep or remove flight
						//if the flight date is conflict
						Object[] options = 
						{"Keep Previous Flight", 
						"Replace Previous Flight", "Cancel"};
						int n = JOptionPane.showOptionDialog
						(this, "This flight have a same date "+
							"with your previous booked flight"
					    + ", would you like to", "Book Flight Option", 
					    JOptionPane.YES_NO_CANCEL_OPTION, 
					    JOptionPane.QUESTION_MESSAGE,
					    null,options, options[2]);
					    //keep previous flight
					    if (n == 0) {
					    	JOptionPane.showMessageDialog
					    	(this, "Previous flight has been keeped,"+
					    	" no change is made");
					    }
					    //replace previous flight
					    else if (n == 1) {
					    	Flight flightToDelete = 
					    	passenger.searchFlightByDate
					    	(selectedFlight.getDate());
							passenger.deleteFlight(flightToDelete);
							selectedFlight.book(passenger);
					    	JOptionPane.showMessageDialog
					    	(this, "Previous flight replaced "+
					    	"with selected flight!");
					    }
					    else {
					    }
					}
					else {
						//book selected flight
						selectedFlight.book(passenger);
						JOptionPane.showMessageDialog(this, 
						"Successfully Book a flight!");
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(this, 
				"Flight that you tried to book not available!");
			}

			setVisible(false);
		}
		else {
			setVisible(false);
		}	
	}
}