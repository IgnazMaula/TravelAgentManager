/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* ShowItenenaryHandler.java
* Description : Handler class to handle acionlistener
* in Show Itenenary tab 

* @author Ignaz Maula Ibrahim
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class ShowItenenaryHandler implements ActionListener {
	//instance variable
	TravelGUI travelGUI;
	FlightTableModel flightTableModel;

	Passenger selectedPassenger;

	//constructor
	public ShowItenenaryHandler(TravelGUI travelGUI ) {
		this.travelGUI = travelGUI;
		flightTableModel = travelGUI.getFlightTableModel();
	}
	//handle action performed
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == travelGUI.searchIte) {
			//search passenger based on entered booking ref
			String bookingRefString = 
			travelGUI.searchBookingRefTFIte.getText();
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
				travelGUI.searchBookingRefTFIte.setText("");
			}
			else {
				//check if passenger already book a flight or not
				if(passengerToBook.getFlightArray().size() == 0) {
					travelGUI.searchBookingRefTFIte.setText("");
					JOptionPane.showMessageDialog(travelGUI, 
					"This passenger didn't book any flight yet!");
				}
				else {
				//display the selected passenger information on the label
				String text = "  Itenary for Mr./Ms. " + 
				passengerToBook.getName() + " [Booking Ref. No. " 
				+ passengerToBook.getBookingRef() + "]";
				travelGUI.itenenaryLabel.setText(text);
				//call priceDetails function to show the itenenary
				travelGUI.textArea.setText(passengerToBook.priceDetails());
				//display the itenenary on the text area
				travelGUI.searchBookingRefTFIte.setText("");
				}	
			}

		}
		else if(e.getSource() == travelGUI.refreshIte) {
			//reset label and text area to empty
			travelGUI.itenenaryLabel.setText(" ");
			travelGUI.textArea.setText("");
		}
		
	}
}