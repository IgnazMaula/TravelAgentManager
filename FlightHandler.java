/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* FlightHandler.java
* Description : Handler class to handle acionlistener,
* ListSelectionListener, ItemListener in Flight tab 

* @author Ignaz Maula Ibrahim
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class FlightHandler implements ActionListener, 
ListSelectionListener, ItemListener {
	
	//instance variable
	TravelGUI travelGUI;
	FlightTableModel flightTableModel;
	MovieListModel movieListModel;

	Flight selectedFlight;

	//custom font
	final Font calibri14 = new Font("Calibri", Font.PLAIN, 14);

	//constructor
	public FlightHandler(TravelGUI travelGUI ) {
		this.travelGUI = travelGUI;
		flightTableModel = travelGUI.getFlightTableModel();
		movieListModel = travelGUI.getMovieListModel();
	}

	public void valueChanged(ListSelectionEvent e) {
		//find selected flight to add movie
		if(e.getSource() == travelGUI.flightTable.getSelectionModel()) {
			int r = travelGUI.flightTable.getSelectedRow();
			if ( r != -1) {
				Flight flight = flightTableModel.getElementAt(r);
				selectedFlight = flight;
				movieListModel.setMovie(flight.getMovieArray());
			}
		}
	}
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == travelGUI.flightCB) {
			//sort flight by Flight No
			if(travelGUI.flightCB.getSelectedItem() == "Flight No") {
				travelGUI.getTravelAgent().flightListByNo();
				travelGUI.getFlightTableModel().fireTableDataChanged();
			}
			//sort flight by Flight Type
			else if(travelGUI.flightCB.getSelectedItem() == "Flight Type") {
				travelGUI.getTravelAgent().flightListByType();
				travelGUI.getFlightTableModel().fireTableDataChanged();
			}
			//sort flight by flight date
			else if(travelGUI.flightCB.getSelectedItem() == "Flight Date") {
				travelGUI.getTravelAgent().flightListByDate();
				travelGUI.getFlightTableModel().fireTableDataChanged();
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
		//add flight
		if(e.getSource() == travelGUI.addFlight) {
			//call FlightAddDialog to create Flight	
			FlightAddDialog addFlightDialog = new FlightAddDialog(travelGUI);
			addFlightDialog.pack();
			addFlightDialog.setLocationRelativeTo(travelGUI);
			addFlightDialog.setVisible(true);
			//add flight to table
			Flight flight = addFlightDialog.getFlight();
			if(flight != null) {
				flightTableModel.addRow(flight);
			}
		}
		//edit flight
		else if(e.getSource() == travelGUI.editFlight) {
			if (this.travelGUI.flightTable.getRowCount() == 0) {
	            JOptionPane.showMessageDialog
	            (travelGUI, "No flight in the table!");
	        }
	        else {

	            int rowIndex = travelGUI.flightTable.getSelectedRow();
	            if (rowIndex != -1) {
	                Flight flight = flightTableModel.getElementAt(rowIndex);
	                //search for  flight price before update
	                double priceBefore = 0;
	                 for (Passenger i : travelGUI.getTravelAgent().
	                 	getPassengerArray()) {
                    	for(Flight j : i.getFlightArray()) {
								if(flight.getFlightNo() == j.getFlightNo())
									priceBefore = i.pricePerFlight(j);
						}
					}
					//call FlightEditDialog to edit selected flight
                    FlightEditDialog flightEdit = 
                    new FlightEditDialog(travelGUI, flight);
                    flightEdit.pack();
                    flightEdit.setLocationRelativeTo(travelGUI);
                    flightEdit.setVisible(true);
                    flightTableModel.fireTableDataChanged();
                    //update the changed price flight in 
                    //flightArray of passanger
                    //that booked the edited flight
                    for (Passenger i : travelGUI.getTravelAgent().
                    	getPassengerArray()) {
                    	for(Flight j : i.getFlightArray()) {
								if(flight.getFlightNo() == j.getFlightNo())
									i.setTotalPrice(i.getTotalPrice() - 
									priceBefore + i.pricePerFlight(flight));
						}
					}

	            }
	            else {
	            	//if no row selected
	                JOptionPane.showMessageDialog(travelGUI, 
	                "Please select a row of flight that want to be edit!");
	            }
	        }
		}
		//delete flight
		else if(e.getSource() == travelGUI.deleteFlight) {
			if(travelGUI.flightTable.getRowCount() == 0) {
				JOptionPane.showMessageDialog(travelGUI, 
				"No flight in the table!");
			}
			else {
				int rowIndex = travelGUI.flightTable.getSelectedRow();
				if(rowIndex!= -1) {
					//search flight to be delete
					Flight flight = flightTableModel.getElementAt(rowIndex);
					//delete confirmation
					int confirm = JOptionPane.showConfirmDialog(travelGUI, 
					"Are you sure you want to delete this flight?", 
					"Confirm Delete", JOptionPane.OK_CANCEL_OPTION);
					if (confirm == JOptionPane.YES_OPTION) {
						//delete Flight
						flightTableModel.removeRow(rowIndex);
						Flight flightinBook = null;
						//delete Flight object if flight in booked
						for (Passenger i : travelGUI.getTravelAgent().
							getPassengerArray()) {
							for(Flight j : i.getFlightArray()) {
								if(flight.getFlightNo() == j.getFlightNo())

									i.deleteFlight(j);
							}
						}
						JOptionPane.showMessageDialog(travelGUI, 
						"Flight successfully deleted!");
					}
				}
				else {
					JOptionPane.showMessageDialog(travelGUI, 
					"Please select a row of flight that want to be delete!");
				} 
			}
		}
		else if(e.getSource() == travelGUI.addMovie) {
			//if flight not selected
			if (this.travelGUI.flightTable.getRowCount() == 0) {
            	JOptionPane.showMessageDialog(travelGUI, 
            	"No flight in the table!");
        	}
        	else {
        		//validate if the flight only can add one movie
        		//if the selected flight is EconomyFlight
        		if (selectedFlight instanceof EconomyFlight 
        			&& selectedFlight.getMovieArray().size() > 0)
					JOptionPane.showMessageDialog(travelGUI, 
					"You cannot added any movie again for this flight");
				else {
	        		int rowIndex = this.travelGUI.flightTable.getSelectedRow();
	            	if (rowIndex != -1) {
	            		//call MovieAddDialog to add movie
	                    MovieAddDialog addMovieDialog = 
	                    new MovieAddDialog(travelGUI);
						addMovieDialog.pack();
						addMovieDialog.setLocationRelativeTo(travelGUI);
						addMovieDialog.setVisible(true);
						Movie movie = addMovieDialog.getMovie();
						if(movie != null) {
							movieListModel.addElement(movie);
						}
	            	}
	            	else {
	                	JOptionPane.showMessageDialog(travelGUI, 
	                	"Please select a flight");
	            	}
	            }
		
			}
		}
		else if(e.getSource() == travelGUI.editMovie) {
			//no selected flight
            if (this.travelGUI.flightTable.getRowCount() == 0) {
            	JOptionPane.showMessageDialog(null, 
            	"No flight in the table!");
        	}
        	else {
        		//search selected movie to be edited
				int[] values = travelGUI.movieList.getSelectedIndices();
		        int[] array;
		        for (int length = (array = values).
		        	length, j = 0; j < length; ++j) {
		            int i = array[j];
		            Movie movie = (Movie)movieListModel.getElementAt(i);
		            //call MovieEditDialog to edit selected movie
		            final MovieEditDialog editMovie = 
		            new MovieEditDialog(travelGUI, movie);
                    editMovie.pack();
                    editMovie.setLocationRelativeTo(travelGUI);
                    editMovie.setVisible(true);
				}	
			}
       	}
		else if(e.getSource() == travelGUI.deleteMovie) {
			if (this.travelGUI.flightTable.getRowCount() == 0) {
				//no selected flight
            	JOptionPane.showMessageDialog
            	(travelGUI, "No flight in the table!");
        	}
        	else {
        		//search selected movie to be delete
				int[] values = travelGUI.movieList.getSelectedIndices();
		        int[] array;
		        for (int length = (array = values).
		        	length, j = 0; j < length; ++j) {
		            int i = array[j];
		            Movie movie = (Movie)movieListModel.getElementAt(i);
		     		//ask confirmation to delete
		            int confirm = JOptionPane.showConfirmDialog(travelGUI, 
		            "Are you sure you want to delete this movie?", 
		            "Confirm Delete", 
		            JOptionPane.OK_CANCEL_OPTION);
		            if (confirm == JOptionPane.YES_OPTION) {
		            	//delete movie
						movieListModel.removeElement(movie);
						JOptionPane.showMessageDialog(travelGUI, 
						"Movie successfully deleted!");
					}
		      
				}
			}
		}

	}

}