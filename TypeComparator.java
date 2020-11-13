/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* TypeComparator.java
* Description : comparator class that compare
* flight by its flight type

* @author Ignaz Maula Ibrahim
*/

import java.util.*;

class TypeComparator implements Comparator<Flight> {

	//compare flight based on the flight type
	public int compare(Flight f1, Flight f2) {
		return f1.getType().compareTo(f2.getType());
	}
}