/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* TravelAgent.java
* Description : container class that store Flight
* and Passenger object and manage its arrayList

* @author Ignaz Maula Ibrahim
*/

import java.util.*;
import java.time.LocalDate;
import java.io.Serializable;

public class TravelAgent implements Serializable {

	//instance variable
	private String name;
	//create Flight array and Passenger array with ArrayList
	private ArrayList<Flight> flightArray = 
	new ArrayList<Flight>();
	private ArrayList<Passenger> passengerArray = 
	new ArrayList<Passenger>();

	//default constructor
	public TravelAgent() {
		name = "NONE";
	}
	//constructor with parameter
	public TravelAgent(String name) {
		this.name = name;
	}
	//getter method
	public String getName() {
		return name;
	}
	public ArrayList<Flight> getFlightArray() {
		return flightArray;
	}
	public ArrayList<Passenger> getPassengerArray() {
		return passengerArray;
	}
	//setter method
	public void setName(String name) {
		this.name = name;
	}

	//create BusinessFlight object and add it to Flight array
	public BusinessFlight addBusinessFlight(String origin, 
		String destination, LocalDate date, String deparr, 
		double price, int childPerc, double rate) {
		BusinessFlight flight = new BusinessFlight(origin, destination, 
		date, deparr, price, childPerc, rate);
		flightArray.add(flight);
		return flight;
	}
	//create EconomyFlight object and add it to Flight array
	public EconomyFlight addEconomyFlight(String origin, 
		String destination, LocalDate date, String deparr, 
		double price, int childPerc) {
		EconomyFlight flight = new EconomyFlight(origin, destination, 
		date, deparr, price, childPerc);
		flightArray.add(flight);
		return flight;
	}
	//create Passenger object and add it to Passenger array
	public Passenger addPassenger(String name, int numAdults, 
	int numChildren, int numFlights, double totalPrice) {
		Passenger passenger = new Passenger(name, numAdults, 
		numChildren, numFlights, totalPrice);
		passengerArray.add(passenger);
		return passenger;
	}
	//receive needed parameter to create Movie 
	//and pass it to selected Flight
	public void addMovie(int flightNum, String title, int length)
	{
		Flight flight = searchFlight(flightNum);
		flight.addMovie(title, length);
	}
	//check the size of Flight array
	public int flightListSize() {
		return flightArray.size();
	}
	//check the size of Passenger array
	public int passengerListSize() {
		return passengerArray.size();
	}

	//show list of Flight in array
	public void flightListByNo() {
		Collections.sort(flightArray, new FlightNoComparator());
	}
	//show list of Flight sorted by type of flight
	public void flightListByType() {
		Collections.sort(flightArray, new TypeComparator());
	}
	//show list of Flight sorted by date of flight
	public void flightListByDate() {
		Collections.sort(flightArray, new DateComparator());
	}

	//show list of Passenger in array
	public String passengerList() {
		String list = "\t---------------------------------------------\n";
		for (Passenger i : passengerArray) {
			list = list + i + "\n";
		}
		return list;
	}
	//show list of Passenger sorted by name
	public void passengerListByName() {
		Collections.sort(passengerArray, new NameComparator());

	}
	//show list of Passenger sorted by booking ref
	public void passengerListByBookingRef() {
		Collections.sort(passengerArray, new BookingRefComparator());
	}

	//search Flight based on flight no
	public Flight searchFlight(int no) {
		for (Flight i : flightArray) {
			if ( i.getFlightNo() == no)
				return i;
		}
		return null;
	}

	//search Passenger based on booking ref
	public Passenger searchPassenger(int no) {
		for (Passenger i : passengerArray) {
			if (i.getBookingRef() == no)
				return i;
		}
		return null;
	}

	//delete selected flight from Flight array
	public void deleteFlight(Flight flight) {
		flightArray.remove(flight);
	}
	//delete selected passenger from Passenger array
	public void deletePassenger(Passenger passenger) {
		passengerArray.remove(passenger);
	}
	//delete selected flight from Flight array located in Passenger class
	public void deleteFlightinPassenger(Flight flight) {
		for (Passenger i : passengerArray) {
			i.deleteFlight(flight);
		}
	}

}