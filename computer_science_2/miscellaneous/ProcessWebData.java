/** ProcessWebData
    This program 
    Stephen Bapple
    
    CS 2, Fall 2015
    
    October
    
    This program takes code from Listing 12.7 of 
    Introduction to Java Programming by Daniel Liang (10th ed.) on page 483.
    All borrowed code is marked.
    
    Specifications:
      - Read a file of scores separated by blanks from the web (ints)
      - find their sum and average
      Additional:
         - Catch exceptions for nonexistent file and bad url.

*/

import javax.swing.JOptionPane;
import java.util.Scanner;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class ProcessWebData {
   // Instance data
   private ArrayList<Integer> data;
   private int sum;
   private double average;
   private final String DATA_URL = 
         "http://cs.armstrong.edu/liang/data/Scores.txt";
   
   //------------------------------------------------------------------------//
   // Main method                                                            //
   //------------------------------------------------------------------------//
   public static void main(String [] args) {
      ProcessWebData program = new ProcessWebData();
      
      // Read and process the data
      program.processData();
      
   }
   
   //------------------------------------------------------------------------//
   // processData                                                            //
   // Read data into the ArrayList                                           //
   // Catch exceptions for no file or bad url                                //
   //------------------------------------------------------------------------//
   public void processData() {
      // Try catch framework taken from Liang. Page 483
      try {
         // Added temporay score holder
         int tempScore;
         data = new ArrayList<>();
         
         // Prepare to read data - Condensed from page 483 lines 9, 11
         Scanner inputFile = new Scanner(
               new java.net.URL(DATA_URL).openStream());
         
         // Get every entry and sum
         while(inputFile.hasNext()) {
            tempScore = inputFile.nextInt();
            data.add(tempScore);
            sum += tempScore;
         }
         
         // Get average
         average = (double)sum / data.size();
         
         // Only show the data if the file was read successfully
         showData();
      }
      catch(java.net.MalformedURLException ex) {
         // Altered from line 20 on page 483 of Liang. to use JOptionPane
         JOptionPane.showMessageDialog(null, "Invalid URL");
      }
      catch(java.io.IOException ex) {
         // Altered from line 23 on page 483 of Liang. to use JOptionPane
         JOptionPane.showMessageDialog(null, "File not found.");
      }
      // Added just in case the we page re-directs
      catch(Exception ex) {
         JOptionPane.showMessageDialog(null, "File not found, check URL");
      }
      
      return;
   }
   
   //------------------------------------------------------------------------//
   // showData                                                               //
   // Output the data to the screen                                          //
   //------------------------------------------------------------------------//
   public void showData() {
      JOptionPane.showMessageDialog(null, "File read successfully. " + 
            "Sum of all scores: " + sum + "\n" + "Average of the " 
            + data.size() + " scores: " 
            + new DecimalFormat("##0.00").format(average));
      
      return;
   }
   
   //------------------------------------------------------------------------//
}