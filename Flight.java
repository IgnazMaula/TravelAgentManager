/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* Flight.java
* Description : parent of Business Flight and
* Economy flight, can booked by passenger and
* store list of in flight movie

* @author Ignaz Maula Ibrahim
*/

import java.util.ArrayList;
import java.time.*;
import java.time.format.*;
import java.io.Serializable;

public abstract class Flight implements Comparable<Flight>, Cloneable, 
Serializable{
	//instance variable
	private int flightNo;
	private String origin;
	private String destination;
	private LocalDate date;
	private String deparr;
	private double price;
	private int childPerc;
	private String insertDate;

	//create Movie array with ArrayList
	private ArrayList<Movie> movieArray;

	//initiate next flight no 
	private static int nextFlightNo = 0;

	//default constructor
	public Flight() {

		//auto increment the flight no
		flightNo = ++nextFlightNo;
		origin = "Unknown";
		destination = "Unknown";
		date = LocalDate.now();
		deparr = "Unknown";
		price = 0;
		childPerc = 0;
	}
	//constructor with parameter
	public Flight(String origin, String destination, LocalDate date, 
		String deparr, double price, int childPerc) {
		flightNo = ++nextFlightNo;
		this.origin = origin;
		this.destination = destination;
		this.date = date;
		this.deparr = deparr;
		this.price = price;
		this.childPerc = childPerc;
		this.movieArray = new ArrayList<Movie>();
	}

	//using equals method
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Flight))
			return false;
		Flight flight = (Flight) obj;
		if ((this.flightNo == flight.flightNo))
			return true;
		else
			return false;
	}

	//using compareTo method
	public int compareTo(Flight obj) {
		if (this == obj)
			return 0;
		if (this.equals(obj))
			return 0;
		return getType().compareTo(obj.getType());
				
	}
	//using hashcode method
	public int hascode() {
		return flightNo;
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
	public int getFlightNo() {
		return flightNo;
	}
	public String getOrigin() {
		return origin;
	}
	public String getDestination() {
		return destination;
	}
	public LocalDate getDate() {
		return date;
	}
	public String getDeparr() {
		return deparr;
	}
	public double getPrice() {
		return price;
	}
	public int getChildPerc() {
		return childPerc;
	}
	public String getType() {
		if (this instanceof BusinessFlight) {
			return "Business";
		}
		else {
			return "Economy";
		}
	}
	public String getFormattedDate() {
		String formattedDate = 
		date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
		return formattedDate;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public ArrayList<Movie> getMovieArray() {
		return movieArray;
	}
	public abstract double getRate();

	//setter method
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public void setDeparr(String deparr) {
		this.deparr = deparr;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setChildPerc(int childPerc) {
		this.childPerc = childPerc;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	//create Movie object and add it to Movie array
	public void addMovie(String title, int length) {
		Movie movie = new Movie(this, title, length);
		movieArray.add(movie);
	}
	//check if movie can add to this flight
	public boolean movieAvailable() {
		for (Movie i : movieArray) {
			if(this == i.getFlight())
				return true;
		}
		return false;
	}
	//show list of Movie from this flight
	public String showMovie() {
		String list = "";
		boolean available = false;
		for (Movie i : movieArray) {
			if (this == i.getFlight()) {
				list = list + "- " + i.toString() + "\n\t\t";
				available = true;
			}
		}
		if (available)
			return "\n\t\t" + list;
		else
			return "\n" + list;	
	}

	//abstract book method to use in subclasses
	public abstract void book(Passenger ps);
	//abstract toString method to use in subclasses
	public abstract String toString();

}
