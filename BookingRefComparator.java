/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* BookingRefComparator.java
* Description : comparator class that compare
* passenger by its booking ref

* @author Ignaz Maula Ibrahim
*/

import java.util.*;

class BookingRefComparator implements Comparator<Passenger> {

	//compare passenger based on the booking ref
	public int compare(Passenger p1, Passenger p2) {
		if (p1.getBookingRef() == p2.getBookingRef())
			return 0;
		else if (p1.getBookingRef() > p2.getBookingRef())
			return 1;
		else
			return -1;
	}
}