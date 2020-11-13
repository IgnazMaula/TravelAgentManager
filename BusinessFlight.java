/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* BussinessFlight.java
* Description : subclass of Flight
* that have rate and can add more than 1 movie

* @author Ignaz Maula Ibrahim
*/

import java.time.LocalDate;
import java.io.Serializable;

public class BusinessFlight extends Flight {

	//instance variable
	private double rate;

	//default constructor
	public BusinessFlight() {
		//instance variable (inherit from Flight)
		this("Unknown", "Unknown", LocalDate.now(), "Unknown", 0, 0, 0);
	}
	//constructor with parameter
	public BusinessFlight(String origin, String destination, 
		LocalDate date, String deparr, double price, 
		int childPerc, double rate) {
		super(origin, destination, date, deparr, price, childPerc);
		this.rate = rate;
	}

	//getter method
	public double getRate() {
		return rate;
	}
	//setter method
	public void setRate(double rate) {
		this.rate = rate;
	}
	//method for assign this Flight to selected passanger
	public void book(Passenger ps) {
		ps.addFlight(this);
		ps.setTotalPrice(ps.getTotalPrice() + ps.pricePerFlight(this));
		ps.setNumFlights();
	}
	//toString method for diplaying BusinessFlight object
	public String toString() {
		return String.format("\t[Flight No. %d]\n\tClass\t\t\t: "+
		"%s\n\tOrigin\t\t\t: %s\n\tDestination\t\t: %s" + 
		"\n\tDate\t\t\t: %s\n\tDeparture/Arrival Time  : %s\n\t"+
		"Price\t\t\t: %.2f RM\n\tChild Perc.\t\t: %d %%\n\t"+
		"Rate\t\t\t: %.2f\n\tMovie\t\t\t: %s\n\t"+
		"---------------------------------------------" 
		, getFlightNo(), getType(), getOrigin(), getDestination(), 
		getFormattedDate(), getDeparr(), getPrice(), getChildPerc(), 
		getRate(), showMovie());
	}
}