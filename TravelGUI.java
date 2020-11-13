/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* TravelGUI.java
* Description : The main JFrame for this GUI program 
* that store all the GUI item needed in this program

* @author Ignaz Maula Ibrahim
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class TravelGUI extends JFrame{

	//instance variable
	private TravelAgent travelAgent;
	private PassengerTableModel passTableModel;
	private FlightTableModel flightTableModel;
	private MovieListModel movieListModel;
	
	JMenuItem newItem, openItem, exitItem, saveItem, saveAsItem;
	JMenuBar menuBar;
	JTabbedPane tabbedPane;

	JTable passengerTable, flightTable, bookTablePass, bookTableFlight;
	JList movieList;

	//Passenger Table
	JButton addPassenger, editPassenger, deletePassenger;
	JLabel listPassenger;
	JComboBox passengerCB;

	//Flight Table
	JButton addFlight, editFlight, deleteFlight;
	JComboBox flightCB;

	//Movie List
	JButton addMovie, editMovie, deleteMovie;

	//Book Flight
	JButton searchBook;//bookFlight, bookFlights;
	JTextField searchBookingRefTF;

	//Show Itenenary
	JButton searchIte, refreshIte;
	JTextField searchBookingRefTFIte;
	JTextArea textArea;
	JLabel itenenaryLabel;

	//costum fonts
	final Font calibri14 = new Font("Calibri", Font.PLAIN, 14);
	final Font calibri15 = new Font("Calibri", Font.BOLD, 15);
	final Font calibri16 = new Font("Calibri", Font.BOLD, 16);
	final Font calibri24 = new Font("Calibri", Font.BOLD, 24);

	//costum icon
	Icon iconPassenger = new ImageIcon("passenger.png");
	Icon iconPlane = new ImageIcon("plane.png");
	Icon iconBook = new ImageIcon("book.png");
	Icon iconItenenary = new ImageIcon("itenenary.png");

	//constructor
	public TravelGUI() {
		//ask travel agent name when launch and create travel agent
		String travelName = JOptionPane.showInputDialog(
		this, "Enter Travel Agent Name", "Register Travel Agent",
		JOptionPane.DEFAULT_OPTION);
		if(travelName == null) {
			travelName = "Default";
			setTitle(" Travel Agent Management System");
			travelAgent = new TravelAgent(travelName);
		}
		else {
			setTitle(travelName + " Travel Agent Management System");
			travelAgent = new TravelAgent(travelName);
		}
		//set size of the main frame
		setSize(1000, 665);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//create a tab pane
		tabbedPane = new JTabbedPane();
		tabbedPane.setFont(calibri16);
		//create menu bar
		menuBar = new JMenuBar();
		setUpMenuBar();
		setJMenuBar(menuBar);
		//call the components of the tab
		setUpPassengerTab();
		setUpFlightTab();
		setUpBookFlightTab();
		setUpShowItenenary();
		//add tab pane to main frame
		getContentPane().add(tabbedPane);

	}

	public void setUpMenuBar() {
		//create JMenu
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		//create item of JMenu
		newItem = new JMenuItem("New");
		openItem = new JMenuItem("Open");
		exitItem = new JMenuItem("Exit");
		saveItem = new JMenuItem("Save");
		saveAsItem = new JMenuItem("Save As...");
		//add item to JMenu
		fileMenu.add(newItem);
		fileMenu.add(openItem);
		fileMenu.addSeparator();
		fileMenu.add(saveItem);
		fileMenu.add(saveAsItem);	
	   	fileMenu.addSeparator();
		fileMenu.add(exitItem);
		//add action listener to the menu item
		TravelFileHandler fileHandler = new TravelFileHandler(this);
		newItem.addActionListener(fileHandler);
		openItem.addActionListener(fileHandler);
		saveItem.addActionListener(fileHandler);
		saveAsItem.addActionListener(fileHandler);
		exitItem.addActionListener(fileHandler);

	}
	public void setUpPassengerTab() {
		//create the panel for the tab
		JPanel passengerPanel = new JPanel(new BorderLayout());
		tabbedPane.addTab("  Passenger", iconPassenger ,passengerPanel);
		
		//create passenger table with costume table model
		passTableModel = new PassengerTableModel(travelAgent.getPassengerArray());
		passengerTable = new JTable(passTableModel);
		passengerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//align table to center
		DefaultTableCellRenderer CENTERCELL = new DefaultTableCellRenderer();
		CENTERCELL.setHorizontalAlignment(JLabel.CENTER);
		passengerTable.getColumnModel().getColumn(0).setCellRenderer(CENTERCELL);
		passengerTable.getColumnModel().getColumn(1).setCellRenderer(CENTERCELL);
		passengerTable.getColumnModel().getColumn(2).setCellRenderer(CENTERCELL);
		passengerTable.getColumnModel().getColumn(3).setCellRenderer(CENTERCELL);
		passengerTable.getColumnModel().getColumn(4).setCellRenderer(CENTERCELL);
		passengerTable.getColumnModel().getColumn(5).setCellRenderer(CENTERCELL);

		//create panel for the table
		JPanel displayPanel = new JPanel(new BorderLayout());
		displayPanel.setBackground(new Color(128, 204, 255));
		listPassenger = new JLabel("List of Passenger");
		listPassenger.setHorizontalAlignment(JLabel.CENTER);
		listPassenger.setFont(calibri15);
		listPassenger.setForeground(Color.WHITE);
		JScrollPane passengerSP = new JScrollPane(passengerTable);
		displayPanel.add(listPassenger, "North");
		displayPanel.add(passengerSP, "Center");

		//create button in this tab
		JPanel buttonPanel = new JPanel();
		addPassenger = new JButton("Add New Passenger");
		addPassenger.setFont(calibri14);
		addPassenger.setBackground(Color.WHITE);
		editPassenger = new JButton("Edit Passenger");
		editPassenger.setFont(calibri14);
		editPassenger.setBackground(Color.WHITE);
		deletePassenger = new JButton("Delete Passenger");
		deletePassenger.setFont(calibri14);
		deletePassenger.setBackground(Color.WHITE);
		passengerCB = new JComboBox();
		passengerCB.setFont(calibri14);
		passengerCB.setBackground(Color.WHITE);
		passengerCB.addItem("Booking Ref");
		passengerCB.addItem("Name");
		buttonPanel.add(addPassenger);
		buttonPanel.add(editPassenger);
		buttonPanel.add(deletePassenger);
		buttonPanel.add(passengerCB);

		//add table panel and button panel to main panel
		passengerPanel.add(displayPanel, "Center");
		passengerPanel.add(buttonPanel, "South");

		//add action listener to the item
		PassengerHandler passHandler = new PassengerHandler(this);
		addPassenger.addActionListener(passHandler);
		editPassenger.addActionListener(passHandler);
		deletePassenger.addActionListener(passHandler);
		passengerCB.addItemListener(passHandler);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void setUpFlightTab() {
		//create the panel for the tab
		JPanel flightMoviePanel = new JPanel(new BorderLayout());
		tabbedPane.addTab("  Flight & Movie", iconPlane, flightMoviePanel);

		//create flight table with costume table model
		flightTableModel = new FlightTableModel(travelAgent.getFlightArray());
		flightTable = new JTable(flightTableModel);
		flightTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//align table to center
		DefaultTableCellRenderer CENTERCELL = new DefaultTableCellRenderer();
		CENTERCELL.setHorizontalAlignment(JLabel.CENTER);
		flightTable.getColumnModel().getColumn(0).setCellRenderer(CENTERCELL);
		flightTable.getColumnModel().getColumn(1).setCellRenderer(CENTERCELL);
		flightTable.getColumnModel().getColumn(2).setCellRenderer(CENTERCELL);
		flightTable.getColumnModel().getColumn(3).setCellRenderer(CENTERCELL);
		flightTable.getColumnModel().getColumn(4).setCellRenderer(CENTERCELL);
		flightTable.getColumnModel().getColumn(5).setCellRenderer(CENTERCELL);
		flightTable.getColumnModel().getColumn(6).setCellRenderer(CENTERCELL);
		flightTable.getColumnModel().getColumn(7).setCellRenderer(CENTERCELL);
		flightTable.getColumnModel().getColumn(8).setCellRenderer(CENTERCELL);

		//create panel for the flight table
		JPanel displayPanel = new JPanel( new BorderLayout());
		JLabel flightLabel = new JLabel("List of Flight");
		flightLabel.setHorizontalAlignment(JLabel.CENTER);
		flightLabel.setFont(calibri15);
		flightLabel.setForeground(Color.WHITE);
		JPanel flightPanel = new JPanel(new BorderLayout());
		flightPanel.setBackground(new Color(128, 204, 255));
		flightPanel.add(flightLabel, "North");
		JScrollPane flightSP = new JScrollPane(flightTable);
		flightPanel.add(flightSP, BorderLayout.CENTER);

		//create panel for the flight button
		JPanel buttonPanelF = new JPanel();
		addFlight = new JButton("Add New Flight");
		addFlight.setFont(calibri14);
		addFlight.setBackground(Color.WHITE);
		editFlight = new JButton("Edit Flight");
		editFlight.setFont(calibri14);
		editFlight.setBackground(Color.WHITE);
		deleteFlight = new JButton("Delete Flight");
		deleteFlight.setFont(calibri14);
		deleteFlight.setBackground(Color.WHITE);
		deletePassenger.setBackground(Color.WHITE);
		flightCB = new JComboBox();
		flightCB.setFont(calibri14);
		flightCB.setBackground(Color.WHITE);
		flightCB.addItem("Flight No");
		flightCB.addItem("Flight Type");
		flightCB.addItem("Flight Date");
		buttonPanelF.add(addFlight);
		buttonPanelF.add(editFlight);
		buttonPanelF.add(deleteFlight);
		buttonPanelF.add(flightCB);
		flightPanel.add(buttonPanelF, BorderLayout.SOUTH);

		//create movie list with costume list model
		movieListModel = new MovieListModel();
		movieList = new JList(movieListModel);
		movieList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//create panel for the movie list
		JLabel movieLabel = new JLabel("List of Movie for This Flight");
		movieLabel.setHorizontalAlignment(JLabel.CENTER);
		movieLabel.setFont(calibri15);
		movieLabel.setForeground(Color.WHITE);
		JPanel moviePanel = new JPanel( new BorderLayout());
		moviePanel.setBackground(new Color(128, 204, 255));
		moviePanel.add(movieLabel, "North");
		JScrollPane movieSP = new JScrollPane(movieList);
		moviePanel.add(movieSP, BorderLayout.CENTER);

		//create panel for the movie button
		JPanel buttonPanelM = new JPanel();
		addMovie = new JButton("Add New Movie");
		editMovie = new JButton("Edit Movie");
		deleteMovie = new JButton("Delete Movie");
		buttonPanelM.add(addMovie);
		addMovie.setFont(calibri14);
		addMovie.setBackground(Color.WHITE);
		buttonPanelM.add(editMovie);
		editMovie.setFont(calibri14);
		editMovie.setBackground(Color.WHITE);
		buttonPanelM.add(deleteMovie);
		deleteMovie.setFont(calibri14);
		deleteMovie.setBackground(Color.WHITE);
		moviePanel.add(buttonPanelM, BorderLayout.SOUTH);

		//add flight and movie panel to main panel
		displayPanel.add(flightPanel, BorderLayout.CENTER);
		displayPanel.add(moviePanel, BorderLayout.EAST);
		flightMoviePanel.add(displayPanel, BorderLayout.CENTER);

		//add action listener to the item
		FlightHandler flightHandler = new FlightHandler(this);
        flightTable.getSelectionModel().addListSelectionListener(flightHandler);
        addFlight.addActionListener(flightHandler);
        editFlight.addActionListener(flightHandler);
        deleteFlight.addActionListener(flightHandler);
        addMovie.addActionListener(flightHandler);
        editMovie.addActionListener(flightHandler);
        deleteMovie.addActionListener(flightHandler);
        flightCB.addItemListener(flightHandler);
	}
	public void setUpBookFlightTab() {
		//create the panel for the tab
		JPanel display = new JPanel(new GridLayout(2,0,0,0));
		JPanel bookFlightPanel = new JPanel(new GridLayout(2,3,0,0));
		bookFlightPanel.setBackground(new Color(230, 249, 255));
		tabbedPane.addTab("  Book Flight", iconBook, display);

		//create the title of the tab
		JPanel titlePanel = new JPanel();
		JLabel bookFlightText = new JLabel("Booking Flight Menu");
		bookFlightText.setHorizontalAlignment(JLabel.CENTER);
		bookFlightText.setFont(calibri24);
		titlePanel.add(bookFlightText);
		
		//create search bar and button for search passenger
		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(new Color(230, 249, 255));
		JLabel searchText = new JLabel
		("Enter the booking ref of passanger to book");
		searchText.setFont(calibri14);
		searchBookingRefTF = new JTextField(25);
		searchBook = new JButton("Search");
		searchBook.setFont(calibri16);
		searchBook.setForeground(Color.WHITE);
		searchBook.setBackground(new Color(128, 204, 255));
		searchPanel.add(searchText);
		searchPanel.add(searchBookingRefTF);
		searchPanel.add(searchBook);

		//put the title, search bar, and button into grid
		bookFlightPanel.add(new JLabel(""));
		bookFlightPanel.add(bookFlightText);
		bookFlightPanel.add(new JLabel(""));
		bookFlightPanel.add(new JLabel(""));
		bookFlightPanel.add(searchPanel);
		bookFlightPanel.add(new JLabel(""));

		//create panel for passenger and flight table
		JPanel tabelPanel = new JPanel(new GridLayout(2,0,0,0));

		//reshow the passenger table
		JPanel passengerTablePanel = new JPanel(new BorderLayout());
		passengerTablePanel.setBackground(new Color(128, 204, 255));
		bookTablePass = new JTable(passTableModel);
		bookTablePass.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//align table to center
		DefaultTableCellRenderer CENTERCELL = new DefaultTableCellRenderer();
		CENTERCELL.setHorizontalAlignment(JLabel.CENTER);
		bookTablePass.getColumnModel().getColumn(0).setCellRenderer(CENTERCELL);
		bookTablePass.getColumnModel().getColumn(1).setCellRenderer(CENTERCELL);
		bookTablePass.getColumnModel().getColumn(2).setCellRenderer(CENTERCELL);
		bookTablePass.getColumnModel().getColumn(3).setCellRenderer(CENTERCELL);
		bookTablePass.getColumnModel().getColumn(4).setCellRenderer(CENTERCELL);
		bookTablePass.getColumnModel().getColumn(5).setCellRenderer(CENTERCELL);
		//add passenger table to the panel
		JScrollPane passBookSP = new JScrollPane(bookTablePass);
		JLabel passengerLabel = new JLabel("List of Available Passenger");
		passengerLabel.setHorizontalAlignment(JLabel.CENTER);
		passengerLabel.setFont(calibri15);
		passengerLabel.setForeground(Color.WHITE);
		passengerTablePanel.add(passengerLabel, BorderLayout.NORTH);
		passengerTablePanel.add(passBookSP, BorderLayout.CENTER);

		//reshow the flight table
		JPanel flightTablePanel = new JPanel(new BorderLayout());
		flightTablePanel.setBackground(new Color(128, 204, 255));
		bookTableFlight = new JTable(flightTableModel);
		bookTableFlight.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//align table to center
		bookTableFlight.getColumnModel().getColumn(0).setCellRenderer(CENTERCELL);
		bookTableFlight.getColumnModel().getColumn(1).setCellRenderer(CENTERCELL);
		bookTableFlight.getColumnModel().getColumn(2).setCellRenderer(CENTERCELL);
		bookTableFlight.getColumnModel().getColumn(3).setCellRenderer(CENTERCELL);
		bookTableFlight.getColumnModel().getColumn(4).setCellRenderer(CENTERCELL);
		bookTableFlight.getColumnModel().getColumn(5).setCellRenderer(CENTERCELL);
		bookTableFlight.getColumnModel().getColumn(6).setCellRenderer(CENTERCELL);
		bookTableFlight.getColumnModel().getColumn(7).setCellRenderer(CENTERCELL);
		bookTableFlight.getColumnModel().getColumn(8).setCellRenderer(CENTERCELL);
		//add flight table to the panel
		JScrollPane flightBookSP = new JScrollPane(bookTableFlight);
		JLabel flightLabel = new JLabel("List of Available Flight");
		flightLabel.setHorizontalAlignment(JLabel.CENTER);
		flightLabel.setFont(calibri15);
		flightLabel.setForeground(Color.WHITE);
		flightTablePanel.add(flightLabel, BorderLayout.NORTH);
		flightTablePanel.add(flightBookSP, BorderLayout.CENTER);

		//add passenger and flight table to table panel
		tabelPanel.add(passengerTablePanel);
		tabelPanel.add(flightTablePanel);
		//add all panel to the main panel
		display.add(bookFlightPanel);
		display.add(tabelPanel);

		//add action listener to the item
		BookFlightHandler bookHandler = new BookFlightHandler(this);
		searchBook.addActionListener(bookHandler);
	}
	public void setUpShowItenenary() {
		//create the panel for the tab
		JPanel display = new JPanel(new GridLayout(2,0,0,0));
		JPanel showItePanel = new JPanel(new GridLayout(2,3,0,0));
		showItePanel.setBackground(new Color(230, 249, 255));
		tabbedPane.addTab("  Show Itenerary", iconItenenary, display);

		//create the title of the tab
		JPanel titlePanel = new JPanel();
		JLabel showIteText = new JLabel("Show Itenerary Menu");
		showIteText.setHorizontalAlignment(JLabel.CENTER);
		showIteText.setFont(calibri24);
		titlePanel.add(showIteText);
		
		//create search bar and button for search passenger
		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(new Color(230, 249, 255));
		JLabel searchText = new JLabel
		("Enter the booking ref of passanger to show itenerary");
		searchText.setFont(calibri14);
		searchBookingRefTFIte = new JTextField(25);
		searchIte = new JButton("Search");
		searchIte.setFont(calibri16);
		searchIte.setForeground(Color.WHITE);
		searchIte.setBackground(new Color(128, 204, 255));
		refreshIte = new JButton("Reset");
		refreshIte.setFont(calibri16);
		refreshIte.setForeground(Color.WHITE);
		refreshIte.setBackground(new Color(128, 204, 255));
		searchPanel.add(searchText);
		searchPanel.add(searchBookingRefTFIte);
		searchPanel.add(searchIte);
		searchPanel.add(refreshIte);

		//put the title, search bar, and button into grid
		showItePanel.add(new JLabel(""));
		showItePanel.add(showIteText);
		showItePanel.add(new JLabel(""));
		showItePanel.add(new JLabel(""));
		showItePanel.add(searchPanel);
		showItePanel.add(new JLabel(""));

		//create panel for text area
		JPanel itenenaryPanel = new JPanel(new BorderLayout());
		itenenaryPanel.setBackground(new Color(128, 204, 255));

		//create text area for space to show itenenary
		itenenaryLabel = new JLabel(" ");
		itenenaryLabel.setHorizontalAlignment(JLabel.CENTER);
		itenenaryLabel.setFont(calibri15);
		itenenaryLabel.setForeground(Color.WHITE);
		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane itenenarySP = new JScrollPane(textArea);
		itenenaryPanel.add(itenenaryLabel, BorderLayout.NORTH);
		itenenaryPanel.add(itenenarySP, BorderLayout.CENTER);

		//add all panel to the main panel
		display.add(showItePanel);
		display.add(itenenaryPanel);

		//add action listener to the item
		ShowItenenaryHandler iteHandler = new ShowItenenaryHandler(this);
		searchIte.addActionListener(iteHandler);
		refreshIte.addActionListener(iteHandler);
	}
	//method to access the TravelAgent in this JFrame
	 public TravelAgent getTravelAgent() {
        return travelAgent;
    }
    //method to setup new TravelAgent 
    public void setTravelAgent(TravelAgent newTravelAgent) {
        String travelName = JOptionPane.showInputDialog
        (this, "Enter Travel Agent Name", 
        "Register Travel Agent", JOptionPane.DEFAULT_OPTION);
		if(travelName == null) {
			travelName = "Default";
			setTitle(" Travel Agent Management System");
			travelAgent = newTravelAgent;
			travelAgent.setName(travelName);
		}
		else {
			setTitle(travelName + " Travel Agent Management System");
			travelAgent = newTravelAgent;
			travelAgent.setName(travelName);
		}
        passTableModel.setPassenger(travelAgent.getPassengerArray());
        passengerTable.setModel(passTableModel);
        flightTableModel.setFlight(travelAgent.getFlightArray());
        flightTable.setModel(flightTableModel);
        movieListModel.clear();
    }
    //get costum TableModel for the passenger tab
	public PassengerTableModel getPassengerTableModel() {
		return passTableModel;
	}
	//get costum TableModel for the flight tab
	public FlightTableModel getFlightTableModel() {
		return flightTableModel;
	}
	//get costum ListModel for the flight tab
	public MovieListModel getMovieListModel() {
		return movieListModel;
	}
	//main method
	public static void main(String[] args)
	{	
		JFrame travel = new TravelGUI();
		travel.setVisible(true);
	}
}