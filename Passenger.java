/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* Passenger.java
* Description : class that create Passenger object
* passenger able to books Flight object
* store list of in flight movie

* @author Ignaz Maula Ibrahim
*/

import java.util.*;
import java.time.LocalDate;
import java.io.Serializable;

public class Passenger implements Comparable<Passenger>, Cloneable, 
Serializable{
	//instance variable
	private int bookingRef;
	private String name;
	private int numAdults;
	private int numChildren;
	private int numFlights;
	private double totalPrice;

	//create Flight array with ArrayList
	private ArrayList<Flight> flightArray = new ArrayList<Flight>();
	//initiate next booking ref
	private static int nextbookingRef = 0;

	//default constructor
	public Passenger() {
		//auto increment the booking ref
		bookingRef = ++nextbookingRef;
		name = "unknown";
		numAdults = 0;
		numChildren = 0;
		numFlights = 0;
		totalPrice = 0;
	}
	//constructor with parameter
	public Passenger(String name, int numAdults, 
	int numChildren, int numFlights, double totalPrice) {
		bookingRef = ++nextbookingRef;
		this.name = name;
		this.numAdults = numAdults;
		this.numChildren = numChildren;
		this.numFlights = numFlights;
		this.totalPrice = totalPrice;
	}

	//using equals method
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Passenger))
			return false;
		Passenger passenger = (Passenger) obj;
		if ((this.bookingRef == passenger.bookingRef))
			return true;
		else
			return false;
	}
	//using compareTo method
	public int compareTo(Passenger obj) {
		if (this == obj)
			return 0;
		if (this.equals(obj))
			return 0;
		return getName().compareTo(obj.getName());
				
	}
	//using hashcode method
	public int hascode() {
		return bookingRef;
	}
	//using clone method
	public Object clone()
	{
		try
		{
			return super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			return null;
		}
	}

	//getter method
	public int getBookingRef() {
		return bookingRef;
	}
	public String getName() {
		return name;
	}
	public int getNumAdults() {
		return numAdults;
	}
	public int getNumChildren() {
		return numChildren;
	}
	public int getNumFlights() {
		return flightArray.size();
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public ArrayList<Flight> getFlightArray() {
		return flightArray;
	}

	//setter method
	public void setName(String name) {
		this.name = name;
	}
	public void setNumAdults(int numAdults) {
		this.numAdults = numAdults;
	}
	public void setNumChildren(int numChildren) {
		this.numChildren = numChildren;
	}
	public void setNumFlights() {
		this.numFlights = flightArray.size();
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	//search for price of only particular flight
	public double pricePerFlight(Flight fl) {
		double totalAdults = 0, totalChildren = 0, 
		priceForAdults = 0, priceForChildren = 0, price = 0;
			if (fl instanceof EconomyFlight) {
				EconomyFlight fle = (EconomyFlight)fl;
				priceForAdults = fle.getPrice();
				totalAdults = (double) numAdults * priceForAdults;
				priceForChildren = fle.getPrice() 
				* ((double)fle.getChildPerc() / 100);
				totalChildren = (double) numChildren 
				* priceForChildren;
				price = totalAdults + totalChildren;
			}
			else {
				BusinessFlight flb = (BusinessFlight)fl;
				priceForAdults = flb.getPrice() * flb.getRate();
				totalAdults = (double) numAdults * priceForAdults;
				priceForChildren = flb.getPrice() 
				* ((double)flb.getChildPerc() / 100) * flb.getRate();
				totalChildren =(double) numChildren * priceForChildren;
				price = totalAdults + totalChildren;	
			}
			return price;
	}

	//show the details information of the price (for display itenenary)
	public String priceDetails() {
		double totalAdults = 0, totalChildren = 0, priceForAdults = 0, 
		priceForChildren = 0, price = 0;
		String details = "", allDetails = "";
		Collections.sort(flightArray, new DateComparator());
		for (Flight fl : flightArray) {
			if (fl instanceof EconomyFlight) {
				EconomyFlight fle = (EconomyFlight)fl;
				priceForAdults = fle.getPrice();
				totalAdults = (double) numAdults * priceForAdults;
				priceForChildren = fle.getPrice() * 
				((double)fle.getChildPerc() / 100);
				totalChildren = (double) numChildren * priceForChildren;
				price = totalAdults + totalChildren;
				details = String.format(
				"\tEconomy Flight [Flight No. %d] (Date : %s) "+
				" %s --> %s :\n\n\t"+
				"\t- %d pax Adults ticket\t   @\t%.2f RM\t%.2f RM\n\t"+
				"\t- %d pax Children ticket\t   @\t%.2f RM\t%.2f RM\n\n\t"+
				"\tTotal Price:\t\t\t\t\t%.2f RM\n\n"
				, fle.getFlightNo(), fle.getFormattedDate(), fle.getOrigin(), 
				fle.getDestination(), numAdults, priceForAdults, totalAdults, 
				numChildren, priceForChildren, totalChildren, price);
			}
			else {
				BusinessFlight flb = (BusinessFlight)fl;
				priceForAdults = flb.getPrice() * flb.getRate();
				totalAdults = (double) numAdults * priceForAdults;
				priceForChildren = flb.getPrice() * 
				((double)flb.getChildPerc() / 100) * flb.getRate();
				totalChildren =(double) numChildren * priceForChildren;
				price = totalAdults + totalChildren;
				details = String.format(
				"\tBusiness Flight [Flight No. %d] (Date : %s)"+
				" %s --> %s :\n\n\t"+
				"\t- %d pax Adults ticket\t   @\t%.2f RM\t%.2f RM\n\t"+
				"\t- %d pax Children ticket\t   @\t%.2f RM\t%.2f RM\n\n\t"+
				"\tTotal Price:\t\t\t\t\t%.2f RM\n\n"
				, flb.getFlightNo(), flb.getFormattedDate(), flb.getOrigin(), 
				flb.getDestination(), numAdults, priceForAdults, totalAdults, 
				numChildren, priceForChildren, totalChildren, price);		
			}
			allDetails = allDetails + details;
		}
		String grandTotal = String.format(
		"\n\tGrand Total :\t\t\t\t\t\t%.2f RM", getTotalPrice());
		return allDetails + grandTotal;
	}

	//add Flight to Flight array 
	public void addFlight(Flight fl) {
		flightArray.add(fl);
	}
	//check the size of Flight array
	public int flightListSize() {
		return flightArray.size();
	}
	//show list of Flight in array
	public String flightList() {
		Collections.sort(flightArray, new DateComparator());
		String list = "\t---------------------------------------------\n";
		for (Flight i : flightArray) {
			list = list + i + "\n";
		}
		return list;
	}
	//search Flight based on flight no
	public Flight searchFlight(int no) {
		for (Flight i : flightArray) {
			if ( i.getFlightNo() == no)
				return i;
		}
		return null;
	}
	//search Flight based on date
	public Flight searchFlightByDate(LocalDate date) {
		for (Flight i : flightArray) {
			if ( i.getDate().equals(date))
				return i;
		}
		return null;
	}
	//delete selected flight from Flight array
	public void deleteFlight(Flight flight) {
		flightArray.remove(flight);
		setTotalPrice(getTotalPrice() - pricePerFlight(flight));

		setNumFlights();

	}
	//toString method for diplaying Passenger object
	public String toString() {
		return String.format(
			"\t[Booking Ref No. %d]\n\tName\t\t\t: %s\n\t"+
			"Number of Adults\t: %d\n\tNumber of Children\t: %d"+
			"\n\tFlight Booked\t\t: %d Flight(s)"+
			"\n\tTotal Price\t\t: %.2f RM\n\t"+
			"---------------------------------------------", 
			bookingRef, name, numAdults, numChildren, 
			numFlights, totalPrice);
	}
}