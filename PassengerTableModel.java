/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* PassengerTableModel.java
* Description : create costum table model for
* passenger table

* @author Ignaz Maula Ibrahim
*/

import java.util.*;
import javax.swing.table.*;

public class PassengerTableModel extends AbstractTableModel {

	//create header for the table
	private static final String[] tableHeader = 
	{"Booking Ref", "Passenger Name", "Num of Adults" , 
	"Num of Children", "Flight Booked", "Total Bill (RM)"};
	private ArrayList<Passenger> passengerArray;

	public PassengerTableModel() {
		setPassenger(new ArrayList<Passenger>());
	}
	//set the passenger ArrayList that want to show on the table
	public PassengerTableModel(ArrayList<Passenger> passengerArray) {
		setPassenger(passengerArray);
	}
	//get the amount of the row
	public int getRowCount() {
		return getPassenger().size();
	}
	//get the amount of the column
	public int getColumnCount() {
		return tableHeader.length;
	}
	//get the information of the object in each
	//row of the table
	public Object getValueAt(int row, int column) {
		Passenger passenger = (Passenger) getPassenger().get(row);
		switch (column) {
			case 0:
				return passenger.getBookingRef();
			case 1:
				return passenger.getName();
			case 2:
				return passenger.getNumAdults();
			case 3:
				return passenger.getNumChildren();
			case 4:
				return passenger.getNumFlights();
			case 5:
				return passenger.getTotalPrice();
			default:
				return "";
		}
	}
	//get the name of the selected column
	public String getColumnName(int column) {
		return tableHeader[column];
	}
	//add passenger to the row
	public void addRow(Passenger passenger) {
		int row = getPassenger().size();
		getPassenger().add(passenger);
		fireTableRowsInserted(row, row);
	}
	//get element at the selected row
	public Passenger getElementAt(int row) {
		Passenger passenger = (Passenger) getPassenger().get(row);
		return passenger;
	}
	//remove element at the selected row
	public Passenger removeRow(int row) {
		Passenger passenger = (Passenger) getPassenger().remove(row);
		fireTableRowsDeleted(row, row);
		return passenger;
	}
	//clear the data of the selected row
	public void clear() {
		int row = getPassenger().size() -1;
		getPassenger().clear();
		if(row >=0)
			fireTableRowsDeleted(0, row);
	}
	//check if selected passenger is contains in the
	//passenger ArrayList
	public boolean contains(Passenger passenger) {
		return getPassenger().contains(passenger);
	}
	//check if passenger ArrayList is empty
	public boolean isEmpty() {
		return getPassenger().size() == 0;
	}
	//set the ArrayList of passenger that want to be display
	//in the table
	public void setPassenger(ArrayList<Passenger> array) {
		passengerArray = array;
		this.fireTableDataChanged();
	}
	//get the ArrayList of passenger
	public ArrayList<Passenger> getPassenger() {
		return passengerArray;
	}

}