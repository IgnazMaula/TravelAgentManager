/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* FlightEditDialog.java
* Description : class that create a dialog
* to edit flight

* @author Ignaz Maula Ibrahim
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.*;
import java.time.format.*;

public class FlightEditDialog 
extends JDialog implements ActionListener{
	//instance variable
	private JLabel origin, destination, date, deparr, 
	price, childPerc, rate, flightNo, flightType;
	private JTextField originTF, destinationTF, dateTF, deparrTF, 
	priceTF, childPercTF, rateTF, flightNoTF, flightTypeTF;
	private JButton okButton, cancelButton, clearButton;

	private Flight flight;
	private LocalDate localDate;

	//custom font
	final Font calibri14 = new Font("Calibri", Font.PLAIN, 14);

	//constructor
	public FlightEditDialog(JFrame parent, Flight flight) {
		super(parent, true);
		this.flight = flight;
		
		setTitle("Edit Flight");
		setSize(400, 300);
		setLocation(500,300);

		//create label
		flightNo = new JLabel("Flight No   ");
		flightNo.setFont(calibri14);
		flightType = new JLabel("Flight Type   ");
		flightType.setFont(calibri14);
		origin = new JLabel("Origin   ");
		origin.setFont(calibri14);
		destination = new JLabel("Destination   ");
		destination.setFont(calibri14);
		date = new JLabel("Date (DD/MM/YYYY)  ");
		date.setFont(calibri14);
		deparr = new JLabel("Departure/Arrival   ");
		deparr.setFont(calibri14);
		price = new JLabel("Price (RM)   ");
		price.setFont(calibri14);
		childPerc = new JLabel("Child Percentage (%)   ");
		childPerc.setFont(calibri14);
		rate = new JLabel("Rate   ");
		rate.setFont(calibri14);

		//create text field
		flightNoTF = new JTextField(15);
		String flightNoTFString = String.valueOf(flight.getFlightNo());
		flightNoTF.setText(flightNoTFString);
		flightNoTF.setEditable(false);
		flightTypeTF = new JTextField(15);
		flightTypeTF.setText(flight.getType());
		flightTypeTF.setEditable(false);
		originTF = new JTextField(15);
		originTF.setText(flight.getOrigin());
		destinationTF = new JTextField(15);
		destinationTF.setText(flight.getDestination());
		dateTF = new JTextField(15);
		dateTF.setText(flight.getInsertDate());
		deparrTF = new JTextField(15);
		deparrTF.setText(flight.getDeparr());
		priceTF = new JTextField(15);
		String priceTFString = String.valueOf(flight.getPrice());
		priceTF.setText(priceTFString);
		childPercTF = new JTextField(15);
		String childPercTFString = String.valueOf(flight.getChildPerc());
		childPercTF.setText(childPercTFString);
		rateTF = new JTextField(15);
		String rateTFString = String.valueOf(flight.getRate());
		rateTF.setText(rateTFString);
		if (flight.getType() == "Business")
			rateTF.setEditable(true);
		else
			rateTF.setEditable(false);

		//create button
		okButton = new JButton("OK");
		okButton.setFont(calibri14);
		okButton.setBackground(Color.WHITE);
		clearButton = new JButton("Clear");
		clearButton.setFont(calibri14);
		clearButton.setBackground(Color.WHITE);
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(calibri14);
		cancelButton.setBackground(Color.WHITE);

		//add component to panel
		JPanel panel1 = new JPanel(new GridLayout(0,1,0,6));
		panel1.add(flightNo);
		panel1.add(flightType);
		panel1.add(origin);
		panel1.add(destination);
		panel1.add(date);
		panel1.add(deparr);
		panel1.add(price);
		panel1.add(childPerc);
		panel1.add(rate);

		JPanel panel2 = new JPanel(new GridLayout(0,1,0,4));
		panel2.add(flightNoTF);
		panel2.add(flightTypeTF);
		panel2.add(originTF);
		panel2.add(destinationTF);
		panel2.add(dateTF);
		panel2.add(deparrTF);
		panel2.add(priceTF);
		panel2.add(childPercTF);
		panel2.add(rateTF);

		JPanel panel3 = new JPanel();
		panel3.add(panel1);
		panel3.add(panel2);

		JPanel panel5 = new JPanel();
		panel5.add(okButton);
		panel5.add(clearButton);
		panel5.add(cancelButton);

		getContentPane().add(panel3, "Center");
		getContentPane().add(panel5, "South");

		//add action listener
		okButton.addActionListener(this);
		clearButton.addActionListener(this);
		cancelButton.addActionListener(this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == okButton) {
			//get all information from the textfiled
			String origin = originTF.getText().trim();
			String destination = destinationTF.getText().trim();
			String date = dateTF.getText().trim();
			String deparr = deparrTF.getText().trim();
			String priceString = priceTF.getText().trim();
			String childPercString = childPercTF.getText().trim();
			
			//parse the double and integer
			double price = Double.parseDouble(priceString);
			int childPerc = Integer.parseInt(childPercString);

			boolean dateError = false;
			//validate the date format
			try {

				DateTimeFormatter formatDate =
				 DateTimeFormatter.ofPattern("dd/MM/yyyy");
				localDate = LocalDate.parse(date, formatDate);
				dateError = false;
				
			}
			catch(DateTimeParseException f) {
				dateError = true;
				JOptionPane.showMessageDialog
				(this, "Invalid date format!");
			}

			String formattedDate = localDate.format
			(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

			//set all setable component in selected flight
			flight.setOrigin(origin);
			flight.setDestination(destination);
			if (localDate!= null)
				flight.setDate(localDate);
			flight.setDeparr(deparr);
			flight.setPrice(price);
			flight.setChildPerc(childPerc);

			//set rate if the flight is BusinessFlight
			if(flight.getType() == "Business") {
				String rateString = rateTF.getText().trim();
				double rate = Double.parseDouble(rateString);
				BusinessFlight flightb = (BusinessFlight) flight;
				flightb.setRate(rate);
			}
			setVisible(false);
		}
		else if(e.getSource() == clearButton) {
			//reset all text field
			originTF.setText("");
			destinationTF.setText("");
			dateTF.setText("");
			deparrTF.setText("");
			priceTF.setText("");
			childPercTF.setText("");

			if(flight.getType() == "Business")
				rateTF.setText("");
		}
		else if(e.getSource() == cancelButton) {
			flight = null;
			setVisible(false);
		}
	}
	public Flight getFlight() {
		return flight;
	}
}