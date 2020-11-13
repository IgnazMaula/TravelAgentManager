/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* MovieEditDialog.java
* Description : class that create a dialog
* to edit selected movie

* @author Ignaz Maula Ibrahim
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MovieEditDialog extends JDialog 
implements ActionListener{
	//instance variable
	private JLabel title, duration;
	private JTextField titleTF, durationTF;
	private JButton okButton, cancelButton, clearButton;

	private Movie movie;

	//custom font
	final Font calibri14 = new Font("Calibri", Font.PLAIN, 14);

	//constructor
	public MovieEditDialog(JFrame parent, Movie movie) {
		super(parent, true);
		this.movie = movie;
		
		setTitle("Edit Movie");
		setSize(300, 150);
		setLocation(500,300);

		//create label
		title = new JLabel("Movie Title   ");
		title.setFont(calibri14);
		duration = new JLabel("Movie Duration (Minutes)  ");
		duration.setFont(calibri14);

		//create text field
		titleTF = new JTextField(15);
		titleTF.setText(movie.getTitle());
		durationTF = new JTextField(15);
		String durationTFString = String.valueOf(movie.getLength());
		durationTF.setText(durationTFString);

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
		JPanel panel1 = new JPanel(new GridLayout(0,1,0,8));
		panel1.add(title);
		panel1.add(duration);

		JPanel panel2 = new JPanel(new GridLayout(0,1,0,4));
		panel2.add(titleTF);
		panel2.add(durationTF);

		JPanel panel3 = new JPanel();
		panel3.add(panel1);
		panel3.add(panel2);

		JPanel panel4 = new JPanel();
		panel4.add(okButton);
		panel4.add(clearButton);
		panel4.add(cancelButton);

		getContentPane().add(panel3, "North");
		getContentPane().add(panel4, "Center");

		//add action listener
		okButton.addActionListener(this);
		clearButton.addActionListener(this);
		cancelButton.addActionListener(this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == okButton) {
			//get all information from texfield
			String title = titleTF.getText().trim();
			String durationString = durationTF.getText().trim();
			//parse int
			int duration = Integer.parseInt(durationString);
			//set the movie attribute with the inputed data
			movie.setTitle(title);
			movie.setLength(duration);

			setVisible(false);
		}
		else if(e.getSource() == clearButton) {
			//reset text field 
			titleTF.setText("");
			durationTF.setText("");
		}
		else {
			movie = null;
			setVisible(false);
		}	
	}
	public Movie getMovie() {
		return movie;
	}
}