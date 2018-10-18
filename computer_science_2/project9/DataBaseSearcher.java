/** DataBaseSearcher
   
    This class contains the data and methods needed
    to perform queries on the database of cars
    
    Programmer: Stephen Bapple
    Professor:  Judith Gurka
    Class:      CS2, Fall 2015
    Month:      December
    
    Limitations:
      - Program crashes without informing user if invalid file is given
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class DataBaseSearcher {
   private DataBaseTree carTree;
   private int hitCount;
   private CarList carsFound;
   private String[][] similarColors;
   
   //------------------------------------------------------------------------//
   // Constructor with given input file                                      //
   //------------------------------------------------------------------------//
   public DataBaseSearcher(String inputFileName) throws IOException {
      // Try catch block to avoid constantly throwing exceptions
      try {
         Scanner dataFile = new Scanner(new File(inputFileName));
         carTree = new DataBaseTree(dataFile.nextLine().replace(",", ""));
      
         // Add every car in the file to the database
         while (dataFile.hasNext())
            carTree.addCar(dataFile.nextLine().replace(",", ""));
         
         /* Testing: print the entire list of cars alphabetically 
         System.out.println(carTree.toString()); */
      }
      catch(IOException e) {
         throw e;
      }
      
      this.hitCount = 0;
      this.carsFound = null;
      
      /* Stub code
      System.out.println("Driver class: default constructor called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Search the database by make                                            //
   // Queries by make will always execute first, if present.                 //
   //------------------------------------------------------------------------//
   public void searchByMake(String make){
      String compareMake;
      Car compareCar = carTree.getCurrentCar();
      carsFound = new CarList();
      
      // Search tree for make
      while (compareCar != null) { 
         compareMake = compareCar.getMake(); // Get the make of the car
         
         // Compare the make to the current car
         if (make.compareToIgnoreCase(compareMake) == 0) {
            ++hitCount;                              // Increment hits
            carsFound.add(compareCar.clone());       // Add car to list of hits
            compareCar = carTree.getNextCarInList(); // Get next car to compare
         }
         else if (make.compareToIgnoreCase(compareMake) < 0) { 
            if (carTree.getLeftNode() != null)       // Go left
               compareCar = carTree.getCurrentCar(); // Get next car to compare
            else
               compareCar = null;
         }
         else if (make.compareToIgnoreCase(compareMake) > 0) {
            if (carTree.getRightNode() != null)      // Go right
               compareCar = carTree.getCurrentCar(); // Get next car to compare
            else
               compareCar = null;
         }
      }
      carsFound.resetPointer();
      
      /* Stub code
      System.out.println("Driver class: searchByMake() called..."); */
   }
   
   //------------------------------------------------------------------------//
   /* Search the database by a given comparison String
      What type of search performed is based on the code given, but does 
      not search make or year.                                               */
   //------------------------------------------------------------------------//
   public void searchByString(String searchStr, int code) {
      /* If no previous query has executed search the entire tree,
         otherwise search the list of cars found on previous query */
      if (carsFound == null) {
         carsFound = new CarList();
         searchByString(searchStr, carTree.getCurrentCar(), code);
      }
      else if (!carsFound.isEmpty()) {
         carsFound.resetPointer(); // Move pointer to beginning of list
         Car compareCar = carsFound.getCurrentCar(); // Current car to examine
         String compareStr;   // The field of each car being examined
         hitCount = 0;        // Reset number of cars found
         
         // Examine every car in the list of cars found
         while(compareCar != null) {
            // If there's a hit, update counter, otherwise delete car
            if (isHit(searchStr, compareCar, code))
               ++hitCount;
            else
               carsFound.deleteCurrentCar();
            
            compareCar = carsFound.getNextCarInList(); // Get next car
         }
      }
      
      /* Stub code
      System.out.println("Driver class: searchByString() called..."); */
   }
   
   //------------------------------------------------------------------------//
   /* Private helper method to search entire tree for matching Strings       
      Used for model, color, license and partial license
      Searches with VLR order for simplicity                                 */
   //------------------------------------------------------------------------//
   private void searchByString(String searchField, Car currentCar, 
         int code) {
      // Base case: car is null
      if (currentCar == null)
         return;
      
      // Search the current list
      while (currentCar != null) {
         // Update counter / list on match
         if (isHit(searchField, currentCar, code)) {
            ++hitCount;
            carsFound.add(currentCar.clone());
         }
         
         currentCar = carTree.getNextCarInList(); // Get next car
      }
      
      // Search left list
      TreeNode savedPointer = carTree.getPointer(); // Save pointer
      if (carTree.getLeftNode() != null) // Note: current moved left here
         searchByString(searchField, carTree.getCurrentCar(), code);
      
      // Search right list
      carTree.setPointer(savedPointer); // Restore pointer
      if (carTree.getRightNode() != null) // Note: current moved right here
         searchByString(searchField, carTree.getCurrentCar(), code);
   }
   
   //------------------------------------------------------------------------//
   // Search the database by year                                            //
   //------------------------------------------------------------------------//
   public void searchByYear(int year) {
      /* If no previous query has executed search the entire tree,
         otherwise search the list of cars found on previous query */
      if (carsFound == null) {
         carsFound = new CarList();
         searchByYear(year, carTree.getCurrentCar());
      }
      else if (!carsFound.isEmpty()) {
         carsFound.resetPointer(); // Move pointer to beginning of list
         Car compareCar = carsFound.getCurrentCar(); // Current car to examine
         int compareYear;          // The year of each car examined
         hitCount = 0;             // Reset number of cars found
         
         // Examine every car in the list of cars found
         while(compareCar != null) {
            compareYear = compareCar.getYear(); // Get current car's model
            
            // If model is found update counter, otherwise delete car
            if (compareYear == year)
               ++hitCount;
            else
               carsFound.deleteCurrentCar();
            
            compareCar = carsFound.getNextCarInList();
         }
      }
      
      /* Stub code
      System.out.println("Driver class: searchByYear() called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Private helper for searching by year through entire tree               //
   // Very similar to searchByString(), only using int types instead         //
   //------------------------------------------------------------------------//
   private void searchByYear(int year, Car currentCar) {
      // Base case: car is null
      if (currentCar == null)
         return;
      
      // Search the current list
      while (currentCar != null) {
         // Update counter / list on match
         if (year == currentCar.getYear()) {
            ++hitCount;
            carsFound.add(currentCar.clone());
         }
         
         currentCar = carTree.getNextCarInList(); // Get next car
      }
      
      // Search left list
      TreeNode savedPointer = carTree.getPointer(); // Save pointer
      if (carTree.getLeftNode() != null) // Note: current moves left here
         searchByYear(year, carTree.getCurrentCar());
      
      // Search right list
      carTree.setPointer(savedPointer); // Restore pointer
      if (carTree.getRightNode() != null) // Note: current moves right here
         searchByYear(year, carTree.getCurrentCar());
   }
   
   //------------------------------------------------------------------------//
   // Search the database by model                                           //
   //------------------------------------------------------------------------//
   public void searchByModel(String model) {
      searchByString(model, 1);
      
      /* Stub code
      System.out.println("Driver class: searchByModel() called...");
      */
   }
   
   //------------------------------------------------------------------------//
   // Search the database by color                                           //
   //------------------------------------------------------------------------//
   public void searchByColor(String color) {
      similarColors = new String[][]{{"blue", "aqua", "navy", "teal"},
            {"red", "maroon", "pink", "burgundy"},
            {"green", "lime", "jade", "teal"}};
      
      searchByString(color, 2);
      
      /* Stub code
      System.out.println("Driver class: searchByColor() called..."); */ 
   }
   
   //------------------------------------------------------------------------//
   // Search the database by license                                         //
   //------------------------------------------------------------------------//
   public void searchByLicense(String license) {
      searchByString(license, 3); // 3 is the code for this search
      
      /* Stub code
      System.out.println("Driver class: searchByLicense() called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Search the database by partial license                                 //
   //------------------------------------------------------------------------//
   public void searchByPartialLicense(String partialLicense) {
      searchByString(partialLicense, 4); // 4 is the code for this search
      
      /* Stub code
      System.out.println("Driver class: searchByPartialLicense() called...");*/
   }
   
   //------------------------------------------------------------------------//
   /* Return true if car is a hit, comparison based on code given
      1: Model
      2: Color
      3: License
      4: Partial license                                                     */
   //------------------------------------------------------------------------//
   private boolean isHit(String compareStr, Car compareCar, int code) {
      switch (code) {
         case 1: return compareCar.getModel().equalsIgnoreCase(compareStr);
         case 2: return checkColors(compareStr, compareCar.getColor());
         case 3: 
            return compareCar.getSearchLicense().equalsIgnoreCase(compareStr);
         case 4: return compareCar.getSearchLicense().
               contains(compareStr.toLowerCase());
      }
      
      return false;
   }
   
   //------------------------------------------------------------------------//
   // Check the car's color against an array of similar colors               //
   //------------------------------------------------------------------------//
   public boolean checkColors(String color, String carColor) {
      int i = 0;
      // First check if colors are equal
      if (color.equalsIgnoreCase(carColor))
         return true;
      
      // If not, try to find primary color to compare with
      while (i < similarColors.length 
            && !color.equalsIgnoreCase(similarColors[i][0]))
         ++i;
      if (i < similarColors.length) {
         for (int j = 0; j < similarColors[i].length; ++j) {
            if (carColor.equalsIgnoreCase(similarColors[i][j]))
               return true;
         }
      }
      
      return false; // Color not found
   }
   
   //------------------------------------------------------------------------//
   // Get the hitCount on all cars searched so far                           //
   //------------------------------------------------------------------------//
   public int getHitCount() {
      return hitCount;
      
      /* Stub code
      System.out.println("Driver class: getHitCount() called...");
      return -1; */
   }
   
   //------------------------------------------------------------------------//
   // Get the String listing all cars found so far                           //
   //------------------------------------------------------------------------//
   public String getCarsFound(){
      return carsFound.toString();
      
      /* Stub code
      System.out.println("Driver class: getCarsFound() called...");
      return "FIXME"; */ 
      
   }
   
   //------------------------------------------------------------------------//
   // Reset hitCount, carsFound and current cars for new queries             //
   //------------------------------------------------------------------------//
   public void resetQuery() {
      this.hitCount = 0;
      this.carsFound = null;
      carTree.resetPointer();
      
      /* Stub code
      System.out.println("Driver class: resetQuery() called..."); */
   }
   
   //------------------------------------------------------------------------//
   // toString(): simplay call the database's toString()                     //
   //------------------------------------------------------------------------//
   @Override
   public String toString() {
      return carTree.toString();
      
      /* Stub code
      System.out.println("Driver class: toString() called...");
      return "FIXME"; */
   }
   
   //------------------------------------------------------------------------//
}