/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* FlightNoComparator.java
* Description : comparator class that compare
* flight by its Flight No

* @author Ignaz Maula Ibrahim
*/

import java.util.*;

class FlightNoComparator implements Comparator<Flight> {

	//compare flight based on the flight type
	public int compare(Flight f1, Flight f2) {
		if (f1.getFlightNo() == f2.getFlightNo())
			return 0;
		else if (f1.getFlightNo() > f2.getFlightNo())
			return 1;
		else
			return -1;
	}
}