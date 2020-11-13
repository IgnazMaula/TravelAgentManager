/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* BookFlightHandler.java
* Description : Handler class to handle acionlistener
* in Book Flight tab 

* @author Ignaz Maula Ibrahim
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class BookFlightHandler implements ActionListener {
	//instance variable
	TravelGUI travelGUI;
	FlightTableModel flightTableModel;
	Passenger selectedPassenger;

	//constructor
	public BookFlightHandler(TravelGUI travelGUI ) {
		this.travelGUI = travelGUI;
		flightTableModel = travelGUI.getFlightTableModel();
	}
	//handle actionperformed
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == travelGUI.searchBook) {
			//search passenger based on entered booking ref
			String bookingRefString = 
			travelGUI.searchBookingRefTF.getText();
			int bookingRef = Integer.parseInt(bookingRefString);
			Passenger passengerToBook = null;
			for( Passenger i : travelGUI.getTravelAgent().
				getPassengerArray()) {
				if(i.getBookingRef() == bookingRef)
					passengerToBook = i;
			}
			//if passenger not found
			if(passengerToBook == null) {
				JOptionPane.showMessageDialog(travelGUI, 
				"Passenger that you search not available on the list!");
				travelGUI.searchBookingRefTF.setText("");
			}
			else {
				//call BookingAddDialog
				BookingAddDialog bookingDialog = 
				new BookingAddDialog(travelGUI, 
				passengerToBook, travelGUI.getTravelAgent());
				travelGUI.searchBookingRefTF.setText("");
				bookingDialog.pack();
	            bookingDialog.setLocationRelativeTo(travelGUI);
	            bookingDialog.setVisible(true);
			}
		}
		
	}
}