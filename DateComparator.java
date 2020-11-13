/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* DateComparator.java
* Description : comparator class that compare
* flight by its date

* @author Ignaz Maula Ibrahim
*/

import java.util.*;

class DateComparator implements Comparator<Flight> {

	//compare flight based on the flight date
	public int compare(Flight f1, Flight f2) {
		return f1.getDate().compareTo(f2.getDate());
	}
}