/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* PassengerHandler.java
* Description : Handler class to handle acionlistener,
* ItemListener in Passenger tab 

* @author Ignaz Maula Ibrahim
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class PassengerHandler implements 
ActionListener, ItemListener{

	//instance variable
	TravelGUI travelGUI;
	PassengerTableModel passTableModel;

	//constructor
	public PassengerHandler(TravelGUI travelGUI) {
		this.travelGUI = travelGUI;
		passTableModel = travelGUI.getPassengerTableModel();
	}

	public void itemStateChanged(ItemEvent e) {
		//sort passenger by name
		if(e.getSource() == travelGUI.passengerCB) {
			if(travelGUI.passengerCB.getSelectedItem() == "Name") {
				travelGUI.getTravelAgent().passengerListByName();
				travelGUI.getPassengerTableModel().fireTableDataChanged();
			}
			//soret passenger by booking ref
			else if(travelGUI.passengerCB.getSelectedItem() == "Booking Ref") {
				travelGUI.getTravelAgent().passengerListByBookingRef();
				travelGUI.getPassengerTableModel().fireTableDataChanged();
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		//add passenger
		if(e.getSource() == travelGUI.addPassenger) {	
			//call PassengerAddDialog to create Passenger
			PassengerAddDialog addPassengerDialog = 
			new PassengerAddDialog(travelGUI);
			addPassengerDialog.pack();
			addPassengerDialog.setLocationRelativeTo(travelGUI);
			addPassengerDialog.setVisible(true);
			//add passenger to table
			Passenger passenger = addPassengerDialog.getPassenger();
			if(passenger != null) {
				passTableModel.addRow(passenger);
			}
		}
		//edit passenger
		else if(e.getSource() == travelGUI.editPassenger) {
			if (this.travelGUI.passengerTable.getRowCount() == 0) {
	            JOptionPane.showMessageDialog
	            (travelGUI, "No passenger in the table!");
	        }
	        else {
	            int rowIndex = travelGUI.passengerTable.getSelectedRow();
	            if (rowIndex != -1) {
	                Passenger passenger = 
	                passTableModel.getElementAt(rowIndex);
	                //call PassengerEditDialog to edit selected passenger
                    PassengerEditDialog passengerEdit = 
                    new PassengerEditDialog(travelGUI, passenger);
                    passengerEdit.pack();
                    passengerEdit.setLocationRelativeTo(travelGUI);
                    passengerEdit.setVisible(true);
                    passTableModel.fireTableDataChanged();

	            }
	            else {
	                JOptionPane.showMessageDialog(travelGUI, 
	                "Please select a row of passenger that want to be edit!");
	            }
	        }
		}
		//delete passenger
		else if(e.getSource() == travelGUI.deletePassenger) {
			if(travelGUI.passengerTable.getRowCount() == 0) {
				JOptionPane.showMessageDialog
				(travelGUI, "No passenger in the table!");
			}
			else {
				int rowIndex = travelGUI.passengerTable.getSelectedRow();
				if(rowIndex!= -1) {
					//search passenger to be delete
					Passenger passenger = 
					passTableModel.getElementAt(rowIndex);
					//delete confirmation
					int confirm = JOptionPane.showConfirmDialog
					(travelGUI, 
					"Are you sure you want to delete this passenger?", 
					"Confirm Delete", JOptionPane.OK_CANCEL_OPTION);
					if (confirm == JOptionPane.YES_OPTION) {
						//delete Passenger
						passTableModel.removeRow(rowIndex);
						JOptionPane.showMessageDialog(travelGUI, 
						"Passenger successfully deleted!");
					}
				}
				else {
					JOptionPane.showMessageDialog
					(travelGUI, "Please select a row of passenger "+
					"that want to be delete!");
				} 
			}
		}
	}

}