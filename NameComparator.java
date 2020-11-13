/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* NameComparator.java
* Description : comparator class that compare
* passenger by its name

* @author Ignaz Maula Ibrahim
*/

import java.util.*;

class NameComparator implements Comparator<Passenger> {

	//compare passenger based on the name
	public int compare(Passenger p1 , Passenger p2) {
		return p1.getName().compareTo(p2.getName());
	}
}