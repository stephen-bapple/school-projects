/** SearchGUI
   
    This class handles user input via a Graphical User Interface
    User is presented with text fields and check boxes / buttons 
    for make, model, year, color, license, and partial license.
    
    Buttons are used for license fields, 
    as program will only search by license OR partial license
    
    Programmer: Stephen Bapple
    Professor:  Judith Gurka
    Class:      CS2, Fall 2015
    Month:      December
    
    Limitations:
      - Assumes user will only enter numbers in the year field
      - Assumes user input file names correctly: program will not inform
        user and will simply shut down
      - Program will not inform user if they filled in a text field
        but did not select the box / button and will not execute the query
      - Program will crash if no buttons are selected 
        but search cars is pressed
*/

import java.io.*;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleGroup;

public class SearchGUI extends Application {
   private ArrayList<String> searchTypes;
   private ArrayList<String> searchFields;
   private PrintWriter outputFile;
   private DataBaseSearcher databaseQuery;
   private String currentQuery;
   
   //------------------------------------------------------------------------//
   // Main method                                                            //
   //------------------------------------------------------------------------//
   public static void main(String [] args) {
      launch(args);
      
      //System.out.println("SearchGUI: In main method..."); // Stub code
   }
   //------------------------------------------------------------------------//
   // Graphical User Interface for inputting input and output files          //
   //------------------------------------------------------------------------//
   @Override
   public void start(Stage primaryStage) {
      // Pane for user input
      GridPane pane = new GridPane();
      pane.setPadding(new Insets(20, 20, 20, 20));
      pane.setHgap(20);
      pane.setVgap(20);
      
      // TextFields, Buttons, and Labels
      TextField inputFileText = new TextField();
      TextField outputFileText = new TextField();
      Button quit = new Button("Quit");
      Button continueProg = new Button("Continue");
      
      // Add Labels, TextFields, and Buttons to pane
      pane.add(new Label("File to read from: "), 0, 0);
      pane.add(inputFileText, 1, 0);
      pane.add(new Label("File to write to: "), 0, 1);   
      pane.add(outputFileText, 1, 1);
      pane.add(continueProg, 0, 2);
      pane.add(quit, 1, 2);
      
      // Quit the program if quit is pressed
      quit.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent e) {
            primaryStage.close();
         }
      });
      
      // Continue the program if continue is pressed
      continueProg.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent e) {
            // Initialize the database and output file writer
            try {
               databaseQuery = new DataBaseSearcher(inputFileText.getText()); 
               outputFile = new PrintWriter(
                     new FileWriter(outputFileText.getText()));
            
               primaryStage.close(); // Close window
               getQueries();         // Continue program   
            }
            catch(IOException ex) {
               primaryStage.close(); // Program does not handle invalid files
            }
         }
      });
      
      // Set up scene and stage
      Scene scene = new Scene(pane);
      primaryStage.setTitle("Enter file names");
      primaryStage.setScene(scene);
      primaryStage.setResizable(false);
      primaryStage.show();
   }
   
   //------------------------------------------------------------------------//
   /* The graphical User Interface for taking user input for queries                     
      User is required to select all boxes / buttons for the queries they     
      want to execute, as well as fill in those fields.                      */
   //------------------------------------------------------------------------//
   public void getQueries() {
      // New Stage
      Stage secondaryStage = new Stage();
      
      // Create the pane for user input
      GridPane pane = new GridPane();
      pane.setPadding(new Insets(20, 20, 20, 20));
      pane.setHgap(20);
      pane.setVgap(20);
      
      // Create the text fields
      TextField makeText = new TextField();
      TextField modelText = new TextField();
      TextField yearText = new TextField();
      TextField colorText = new TextField();
      TextField licenseText = new TextField();
      TextField partialLicenseText = new TextField();
      
      // Create the check boxes and buttons
      CheckBox makeCheck = new CheckBox();
      CheckBox modelCheck = new CheckBox();
      CheckBox yearCheck = new CheckBox();
      CheckBox colorCheck = new CheckBox();
      RadioButton licenseButton = new RadioButton();
      RadioButton partialLicenseButton = new RadioButton();
      Button searchCars = new Button("Search Cars");
      Button quit = new Button("Quit");
      
      // Add the two radio buttons to a toggle group
      ToggleGroup licenseGroup = new ToggleGroup();
      licenseButton.setToggleGroup(licenseGroup);
      partialLicenseButton.setToggleGroup(licenseGroup);
      
      // Add all the text fields and buttons to the pane
      pane.add(new Label("Make:"), 0,0);
      pane.add(makeText, 1, 0);
      pane.add(makeCheck, 2, 0);
      pane.add(new Label("Model:"), 0, 1); 
      pane.add(modelText, 1, 1);
      pane.add(modelCheck, 2, 1);
      pane.add(new Label("Year:"), 0, 2);
      pane.add(yearText, 1, 2);
      pane.add(yearCheck, 2, 2);
      pane.add(new Label("Color:"), 0, 3);
      pane.add(colorText, 1, 3);
      pane.add(colorCheck, 2, 3);
      pane.add(new Label("Licence:"), 0, 4);
      pane.add(licenseText, 1, 4);
      pane.add(licenseButton, 2, 4);
      pane.add(new Label("Partial License:"), 0, 5); 
      pane.add(partialLicenseText, 1, 5);
      pane.add(partialLicenseButton, 2, 5);
      pane.add(searchCars, 0, 6);
      pane.add(quit, 2, 6);
      
      // Quit the program if quit is pressed
      quit.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent e) {
            // Print report and close the output file if it's been opened
            if (outputFile != null) {
               outputFile.println(databaseQuery.toString());
               outputFile.close();
            }
            secondaryStage.close(); // Close program
         }
      });
      
      // Search all selected fields if searchCars is pressed
      searchCars.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent e) {
            searchTypes = new ArrayList<String>();  // Holds types of searches
            searchFields = new ArrayList<String>(); // Holds search data
            currentQuery = "Query: ";
            
            // Add every query selected to the search queues
            if (makeCheck.isSelected() && !makeText.getText().equals("")) {
               //System.out.println("Search by make");
               searchTypes.add("make");
               searchFields.add(makeText.getText());
               currentQuery = currentQuery + "\r\n" 
                     + "By make: " + makeText.getText();
            }
            if (modelCheck.isSelected() && !modelText.getText().equals("")) {
               //System.out.println("Search by model");
               searchTypes.add("model");
               searchFields.add(modelText.getText());
               currentQuery = currentQuery + "\r\n" 
                     + "By model: " + modelText.getText();
            }
            if (yearCheck.isSelected() && !yearText.getText().equals("")) {
               //System.out.println("Search by year");
               searchTypes.add("year");
               searchFields.add(yearText.getText());
               currentQuery = currentQuery + "\r\n" 
                     + "By year: " + yearText.getText();
            }
            if (colorCheck.isSelected() && !colorText.getText().equals("")) {
               //System.out.println("Search by color");
               searchTypes.add("color");
               searchFields.add(colorText.getText());
               currentQuery = currentQuery + "\r\n" 
                     + "By color: " + colorText.getText();
            }
            // Only one can be selected, but neither might be
            if (licenseButton.isSelected() 
                  && !licenseText.getText().equals("")) {
               //System.out.println("Search by license");
               searchTypes.add("license");
               searchFields.add(licenseText.getText().replace("-","")
                     .replace(" ", ""));
               currentQuery = currentQuery + "\r\n" 
                     + "By full license: " + licenseText.getText();
            }
            else if (partialLicenseButton.isSelected() 
                  && !partialLicenseText.getText().equals("")) {
               //System.out.println("Search by partial license");
               searchTypes.add("partial license");
               searchFields.add(partialLicenseText.getText().replace("-","")
                     .replace(" ", ""));
               currentQuery = currentQuery + "\r\n" 
                     + "By partial license: " + partialLicenseText.getText();
            }
            
            // Close the window and perform the search
            secondaryStage.close();
            executeQueries(searchTypes, searchFields);
         }
      });
       
      // Set up scene and stage
      Scene scene = new Scene(pane);
      secondaryStage.setTitle("Check boxes and fill in fields to search");
      secondaryStage.setScene(scene);
      secondaryStage.setResizable(false);
      secondaryStage.show();
      
      // System.out.println("SearchGUI: start() called..."); // Stub code
   }
   
   //------------------------------------------------------------------------//
   // Execute the queries the user input in start()                          //
   //------------------------------------------------------------------------//
   public void executeQueries(ArrayList<String> searchTypes, 
         ArrayList<String> searchFields) {
      // Execute every query
      while (!searchFields.isEmpty()) {
         // Choose the correct method to call based on the search type
         switch (searchTypes.get(0)) {
            case "make": databaseQuery.searchByMake(searchFields.get(0)); 
               break;
            case "model": databaseQuery.searchByModel(searchFields.get(0));
               break;
            case "year": databaseQuery.searchByYear
                  (Integer.parseInt(searchFields.get(0)));
               break;
            case "color": databaseQuery.searchByColor(searchFields.get(0));
               break;
            case "license": databaseQuery.searchByLicense(searchFields.get(0));
               break;
            default: // Searching by partial license
               databaseQuery.searchByPartialLicense(searchFields.get(0));
         }

         // Delete the query
         searchTypes.remove(0);
         searchFields.remove(0); 
      }
      
      // Display results of all queries
      storeResults(databaseQuery.getHitCount(), databaseQuery.getCarsFound());
      
      /* Stub code
      System.out.println("SearcheGUI: executeQueries() called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Display the results of the queries executed                            //
   //------------------------------------------------------------------------//
   public void storeResults(int hitCounter, String carsFound) {
      outputFile.println(currentQuery + "\r\n" + "\r\n" + "Cars found: " 
            + hitCounter + "\r\n" 
            + (carsFound.equals("") ? "" : carsFound + "\r\n"));
            
      databaseQuery.resetQuery();
      getQueries();
      
      /* Stub and test code
      System.out.println(hitCounter + " " + carsFound);
      System.out.println("SearchGUI: storeResults() called..."); */
   }
   
   //------------------------------------------------------------------------//
}