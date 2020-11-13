/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* FlightTableModel.java
* Description : create costum table model for
* flight table

* @author Ignaz Maula Ibrahim
*/

import java.util.*;
import javax.swing.table.*;

public class FlightTableModel extends AbstractTableModel {

	//create header for the table
	private static final String[] tableHeader = 
	{"Flight Type", "Flight No", "Origin" , "Destination", 
	"Date", "Dep/Arr", "Price (RM)", "Child Perc.", "Rate"};
	private ArrayList<Flight> flightArray;

	public FlightTableModel() {
		setFlight(new ArrayList<Flight>());
	}
	//set the flight ArrayList that want to show on the table
	public FlightTableModel(ArrayList<Flight> flightArray) {
		setFlight(flightArray);
	}
	//get the amount of the row
	public int getRowCount() {
		return getFlight().size();
	}
	//get the amount of the column
	public int getColumnCount() {
		return tableHeader.length;
	}
	//get the information of the object in each
	//row of the table
	public Object getValueAt(int row, int column) {
		Flight flight = (Flight) getFlight().get(row);
		switch (column) {
			case 0:
				return flight.getType();
			case 1:
				return flight.getFlightNo();
			case 2:
				return flight.getOrigin();
			case 3:
				return flight.getDestination();
			case 4:
				return flight.getDate();
			case 5:
				return flight.getDeparr();
			case 6:
				return flight.getPrice();
			case 7:
				return flight.getChildPerc();
			case 8:
				return flight.getRate();
			default:
				return "";
		}
	}
	//get the name of the selected column
	public String getColumnName(int column) {
		return tableHeader[column];
	}
	//add flight to the row
	public void addRow(Flight flight) {
		int row = getFlight().size();
		getFlight().add(flight);
		fireTableRowsInserted(row, row);
	}
	//get element at the selected row
	public Flight getElementAt(int row) {
		Flight flight = (Flight) getFlight().get(row);
		return flight;
	}
	//remove element at the selected row
	public Flight removeRow(int row) {
		Flight flight = (Flight) getFlight().remove(row);
		fireTableRowsDeleted(row, row);
		return flight;
	}
	//clear the data of the selected row
	public void clear() {
		int row = getFlight().size() -1;
		getFlight().clear();
		if(row >=0)
			fireTableRowsDeleted(0, row);
	}
	//check if selected flight is contains in the
	//flight ArrayList 
	public boolean contains(Flight flight) {
		return getFlight().contains(flight);
	}
	//check if flight ArrayList is empty
	public boolean isEmpty() {
		return getFlight().size() == 0;
	}
	//set the ArrayList of flight that want to be display
	//in the table
	public void setFlight(ArrayList<Flight> array) {
		flightArray = array;
		this.fireTableDataChanged();
	}
	//get the ArrayList of flight
	public ArrayList<Flight> getFlight() {
		return flightArray;
	}

}