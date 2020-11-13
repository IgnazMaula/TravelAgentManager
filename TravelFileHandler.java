/**
* BIT203 Advanced Programming in Java Assignment 2
* Name: Ignaz Maula Ibrahim
* StudentID: E180184
* TravelFileHandler.java
* Description : Handler class to handle acionlistener
* for item in the JMenu

* @author Ignaz Maula Ibrahim
*/

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.*;

public class TravelFileHandler implements ActionListener
{   
    //instance variable
    JFileChooser fc;
    File file;
    TravelGUI travelGUI;
    
    //constructor
    TravelFileHandler(TravelGUI gui) {
        this.fc = new JFileChooser();
        this.file = null;
        this.travelGUI = gui;
    }
    
    //handle actionperformed
    public void actionPerformed(ActionEvent ae) {
        //open file
        if (ae.getSource() == travelGUI.openItem) {
            //ask to save current file or not
            int n = JOptionPane.showConfirmDialog(travelGUI, 
            "Would you like to save your changes first", "Warning", 1);
            //save current file
            if (n == 0) {
                if (file != null) {
                    saveToFile();
                }
                else {
                    showSaveFileChooser();
                }
                int returnVal = fc.showOpenDialog(travelGUI);
                //open selected file
                if (returnVal == 0) {
                    file = fc.getSelectedFile();
                    readFromFile();
                }
            }
            //discard current file
            else if(n == 1) {
                int returnVal = fc.showOpenDialog(travelGUI);
                //open selected file
                if (returnVal == 0) {
                    file = fc.getSelectedFile();
                    readFromFile();
                }
            }
            else {
            }
            
        }
        //create new file
        else if (ae.getSource() == travelGUI.newItem) {
            //ask to save current file or not
            int n = JOptionPane.showConfirmDialog(travelGUI, 
            "Would you like to save your changes first", "Warning", 1);
            //save current file
            if (n == 0) {
                if (file != null) {
                    saveToFile();
                }
                else {
                    showSaveFileChooser();
                }
                //create new fresh travel TravelAgent
                travelGUI.setTravelAgent(new TravelAgent());
                JOptionPane.showMessageDialog
                (travelGUI, "New Travel Agent Created");
            }
            //discard current file
            else if(n == 1) {
                //create new fresh TravelAgent
                travelGUI.setTravelAgent(new TravelAgent());
                JOptionPane.showMessageDialog
                (travelGUI, "New Travel Agent Created");
            }
            else {
            
            }
        }
        //exit
        else if (ae.getSource() == travelGUI.exitItem) {
            //ask to save current file or not
            int n = JOptionPane.showConfirmDialog(travelGUI, 
            "Would you like to save your changes first", "Warning", 1);
            //save current file
            if (n == 0) {
                if (file != null) {
                    saveToFile();
                }
                else {
                    showSaveFileChooser();
                }
                //exit
                System.exit(0);
            }
            //discard current file
            else if(n == 1) {
                //exit
                System.exit(0);
            }
            else {
                
            }
        }
        //save as file
        else if (ae.getSource() == travelGUI.saveAsItem || 
            (ae.getSource() == travelGUI.saveItem && file == null)) {
            //open save file chooser to save as
            showSaveFileChooser();
        }
        //save file
        else if (ae.getSource() == travelGUI.saveItem && file != null) {
            //save file
            saveToFile();
            System.out.println("Saving: " + file.getName());
        }   
    }
    //read the selected file to be load using fileInputStream
    public void readFromFile() {
        FileInputStream fis = null;
        try {
            //reaad object
            fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            TravelAgent travelAgent = (TravelAgent)ois.readObject();
            travelGUI.setTravelAgent(travelAgent);
            ois.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            if (fis != null) {
                try {
                    fis.close();
                }
                catch (IOException e2) {
                    System.out.println(e2.getMessage());
                }
            }
            return;
        }
        finally {
            if (fis != null) {
                try {
                    fis.close();
                }
                catch (IOException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        if (fis != null) {
            try {
                fis.close();
            }
            catch (IOException e2) {
                System.out.println(e2.getMessage());
            }
        }
    }
    //file chooser to save a file
    public void showSaveFileChooser() {
        int returnVal = fc.showSaveDialog(travelGUI);
        if (returnVal == 0) {
            file = fc.getSelectedFile();
            saveToFile();
            System.out.println("Saving: " + file.getName());
        }
        else {
            System.out.println("Save command cancelled by user.");
        }
    }
    //save the current file with ObjectOutputStream
    public void saveToFile() {
        FileOutputStream fos = null;
        try {
            //write the object into file
            fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(travelGUI.getTravelAgent());
            oos.flush();
            oos.close();
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            if (fos != null) {
                try {
                    fos.close();
                }
                catch (IOException ioe2) {
                    System.out.println(ioe2.getMessage());
                }
            }
            return;
        }
        finally {
            if (fos != null) {
                try {
                    fos.close();
                }
                catch (IOException ioe2) {
                    System.out.println(ioe2.getMessage());
                }
            }
        }
        if (fos != null) {
            try {
                fos.close();
            }
            catch (IOException ioe2) {
                System.out.println(ioe2.getMessage());
            }
        }
    }
}
