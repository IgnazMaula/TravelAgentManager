/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* FlightAddDialog.java
* Description : class that create a dialog
* to add new flight

* @author Ignaz Maula Ibrahim
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.*;
import java.time.format.*;

public class FlightAddDialog extends JDialog 
implements ActionListener{
	//instance variable
	private JLabel origin, destination, date, 
	deparr, price, childPerc, rate;
	private JTextField oringTF, destinationTF, 
	dateTF, deparrTF, priceTF, childPercTF, rateTF;
	private JButton okButton, cancelButton;
	private JRadioButton businessRB, economyRB;

	private LocalDate localDate;
	private Flight flight;

	//custom font
	final Font calibri14 = new Font("Calibri", Font.PLAIN, 14);

	//constructor
	public FlightAddDialog(JFrame parent) {
		super(parent, true);
		flight = null;
			
		setTitle("Add New Flight");
		setSize(400, 300);
		setLocation(500,300);

		//create label
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
		oringTF = new JTextField(15);
		destinationTF = new JTextField(15);
		dateTF = new JTextField(15);
		deparrTF = new JTextField(15);
		priceTF = new JTextField(15);
		childPercTF = new JTextField(15);
		rateTF = new JTextField(15);

		//create button and raido button
		okButton = new JButton("OK");
		okButton.setFont(calibri14);
		okButton.setBackground(Color.WHITE);
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(calibri14);
		cancelButton.setBackground(Color.WHITE);
		businessRB = new JRadioButton("Business");
		businessRB.setFont(calibri14);
		economyRB = new JRadioButton("Economy");
		economyRB.setFont(calibri14);
		businessRB.setSelected(true);

		//create button group
		ButtonGroup rb = new ButtonGroup();
		rb.add(businessRB);
		rb.add(economyRB);

		//add component to panel
		JPanel panel1 = new JPanel(new GridLayout(0,1,0,6));
		panel1.add(origin);
		panel1.add(destination);
		panel1.add(date);
		panel1.add(deparr);
		panel1.add(price);
		panel1.add(childPerc);
		panel1.add(rate);

		JPanel panel2 = new JPanel(new GridLayout(0,1,0,4));
		panel2.add(oringTF);
		panel2.add(destinationTF);
		panel2.add(dateTF);
		panel2.add(deparrTF);
		panel2.add(priceTF);
		panel2.add(childPercTF);
		panel2.add(rateTF);

		JPanel panel3 = new JPanel();
		panel3.add(panel1);
		panel3.add(panel2);

		JPanel panel4 = new JPanel();
		panel4.add(businessRB);
		panel4.add(economyRB);

		JPanel panel5 = new JPanel();
		panel5.add(okButton);
		panel5.add(cancelButton);

		getContentPane().add(panel3, "Center");
		getContentPane().add(panel4, "North");
		getContentPane().add(panel5, "South");

		//add action listener
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		businessRB.addActionListener(this);
		economyRB.addActionListener(this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == okButton) {
			//get all information from the textfield
			String origin = oringTF.getText().trim();
			String destination = destinationTF.getText().trim();
			String stringDate = dateTF.getText().trim();
			String deparr = deparrTF.getText().trim();
			String priceString = priceTF.getText().trim();
			String childPercString = childPercTF.getText().trim();
			String rateString = rateTF.getText().trim();

			boolean dateError = false;
			//validate the date format
			try {

				DateTimeFormatter formatDate = 
				DateTimeFormatter.ofPattern("dd/MM/yyyy");
				localDate = LocalDate.parse(stringDate, formatDate);
				dateError = false;
				
			}
			catch(DateTimeParseException f) {
				dateError = true;
				JOptionPane.showMessageDialog
				(this, "Invalid date format!");
			}

			String formattedDate = localDate.format
			(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));

			//parse the double and integer
			double price = Double.parseDouble(priceString);
			int childPerc = Integer.parseInt(childPercString);
			double rate = Double.parseDouble(rateString);

			//create BusinessFlight if businessRB selected
			if(businessRB.isSelected()) {
				flight = new BusinessFlight(origin, destination, 
				localDate, deparr, price, childPerc, rate);
				flight.setInsertDate(stringDate);
			}
			//create EconomyFlight if economyRB selected
			else {
				flight = new EconomyFlight(origin, destination, 
				localDate, deparr, price, childPerc);
				flight.setInsertDate(stringDate);
			}
			setVisible(false);
		}
		else if(e.getSource() == cancelButton) {
			flight = null;
			setVisible(false);
		}
		else if (e.getSource() == businessRB) {
            rateTF.setEditable(true);
            rateTF.setText("");
        }
        //set the rate to 1 and unchangeable
        else if (e.getSource() == economyRB) {
            rateTF.setEditable(false);
            rateTF.setText("1");
        }	
	}
	public Flight getFlight() {
		return flight;
	}
}