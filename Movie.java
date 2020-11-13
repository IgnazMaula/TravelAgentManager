/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* Movie.java
* Description : class that create Movie object
* movie is created and can be safe on array of a flight

* @author Ignaz Maula Ibrahim
*/

import java.io.Serializable;

public class Movie implements Serializable{

	//instance variable
	private Flight flight;
	private String title;
	private int length;

	//default constructor
	public Movie() {
		title = "Untitled";
		length = 0;
	}
	//constructor with method
	public Movie(Flight flight, String title, int length) {
		this.flight = flight;
		this.title = title;
		this.length = length;
	}
	//getter method
	public Flight getFlight() {
		return flight;
	}
	public String getTitle() {
		return title;
	}
	public int getLength() {
		return length;
	}
	//setter method
	public void setTitle(String title) {
		this.title = title;
	}
	public void setLength(int length) {
		this.length = length;
	}

	//toString method for diplaying Movie object
	public String toString() {
		return  title + " - " + length + " minutes";
	}
}