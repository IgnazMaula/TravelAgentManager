/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* EconomyFlight.java
* Description : subclass of Flight
* that not have rate and cannot add more than 1 movie

* @author Ignaz Maula Ibrahim
*/

import java.time.LocalDate;
import java.io.Serializable;


public class EconomyFlight extends Flight {

	//rate always 1 for economy flight
	private final double rate = 1;

	//default constructor
	public EconomyFlight() {
		this("Unknown", "Unknown", LocalDate.now(), "Unknown", 0, 0);
	}
	//constructor with parameter
	public EconomyFlight(String origin, String destination, 
	LocalDate date, String deparr, double price, int childPerc) {
		super(origin, destination, date, deparr, price, childPerc);
	}
	//get default rate
	public double getRate() {
		return rate;
	}
	//method for assign this Flight to selected passanger
	public void book(Passenger ps) {
		ps.addFlight(this);
		ps.setTotalPrice(ps.getTotalPrice() + ps.pricePerFlight(this));
		ps.setNumFlights();
	}
	//toString method for diplaying EconomyFlight object
	public String toString() {
		return String.format("\t[Flight No. %d]\n\tClass\t\t\t: "+
		"%s\n\tOrigin\t\t\t: %s\n\tDestination\t\t: %s" + 
		"\n\tDate\t\t\t: %s\n\tDeparture/Arrival Time  : %s\n\t"+
		"Price\t\t\t: %.2f RM\n\tChild Perc.\t\t: %d %%\n\t"+
		"Movie\t\t\t: %s\n\t"+
		"---------------------------------------------" 
		, getFlightNo(), getType(), getOrigin(), getDestination(), 
		getFormattedDate(), getDeparr(), getPrice(), getChildPerc(), 
		showMovie());
	}
}