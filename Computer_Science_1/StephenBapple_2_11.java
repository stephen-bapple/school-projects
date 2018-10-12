/** This program uses array lists and recursion to populate a list of integers
    entered in the console by the user, and calculate the sum and average of 
    the list.
    
    Stephen Bapple
    
    Program #11, CS 1050, Section 2.
    jGrasp v2.0.1_01, Sony Vaio laptop, Windows 7.

    Inveterate: long established, unlikely to change.
    
    "When everything seems to be going against you, remember that the airplane
    takes off against the wind, not with it."
    - Henry Ford (1863 - 1947)
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class StephenBapple_2_11 {
   static DecimalFormat round2Places = new DecimalFormat("0.00");
   
   public static void main(String [] args) {
      // Declarations
      ArrayList<Integer> numList = new ArrayList<Integer>(0);
      Scanner keyboard           = new Scanner(System.in);
      
      // Explain the program
      explainProgram();
      
      // Call method that reads input
      readInput(numList, keyboard);
      
      // Close the program
      keyboard.close();
      System.exit(0);
      
   } // End main
   
   //------------------------------------------------------------------------//
   // Methods section
   //------------------------------------------------------------------------//
   public static void explainProgram () {
      System.out.println("This program lets you enter a list of integers,"
            + " one at a time, and calculates their sum and average." 
            + "\r\n"
            + "Enter any integer, or enter 0 to find the sum and average"
            + " / quit the program.");
      
      return;
   }
   
   //------------------------------------------------------------------------//
   public static void readInput(ArrayList<Integer> numList, Scanner keyboard) {
      // Declarations
      String input    = "";    // String that holds input
      int oneNumber   = 0;     // Current number being read
      boolean isValid = false; // True when input is an integer 
      
      // Check if user input is valid
      while(!isValid) {
         try {
            // Try to get input and parse out an integer
            input = keyboard.next();
            oneNumber = Integer.parseInt(input);
            isValid = true; // End loop if successful
         }
         catch(NumberFormatException e) {
            // Inform user input is invalid
            System.out.println("Please enter an integer,"
                  + " like -2, 56, or 99999.");
         }
      } // End while loop
      
      // End recursion and continue program if user enters zero
      if(oneNumber == 0) {
         sumInfo(numList);
      }
      // Else add number to list and call readInput again
      else {
         numList.add(oneNumber);
         readInput(numList, keyboard);
      }

      return; 
   }
   
   //------------------------------------------------------------------------//
   public static void sumInfo(ArrayList<Integer> numList) {
      // Declarations
      int sum        = 0; // Sum of all the entries
      double average = 0; // Average of all the entries
      
      // If there are entries find the sum and average
      if(numList.size() > 0) {
         // Add every entry to the sum
         for (int i = 0; i < numList.size(); ++i) {
            sum += numList.get(i);
         }
         
         // Find the average using the sum found in the previous step
         average = (double)sum / numList.size(); 
         
         // Print the number of entries, the sum, and the average
         System.out.println("The sum of the " + numList.size() 
               + " numbers entered is " + sum + ", and their average is " 
               + round2Places.format(average));
         }
         
      // Else print that there were no numbers
      else {
         System.out.println("No numbers entered.");
      }
      
      return;
   }     
   
   //------------------------------------------------------------------------//
   
} // End class