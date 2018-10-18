/** Primes program
    
    This program gets an upper limit from the user, and then uses it to find
    all the prime numbers (above two) up to and possible including the limit.
    
    Stephen Bapple
    
    August 2015
    
    CS 2, Fall 2015
    
    Limitations:
       - Does not take multiple inputs
       - Extremely slow for larger primes
       - Limited screen space for larger lists
       - Does not parse punctuation
*/

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class PrimesProgram {
   private int upperLimit                = 0;
   private ArrayList<Integer> primesList = new ArrayList<Integer>();
   
   public static void main(String [] args) {
      // Declare the class object
      PrimesProgram program = new PrimesProgram();
      
      // Get the upper limit from the user
      program.getLimit();
      
      // Find all the primes up to the upper limit
      program.findPrimes();
      
      // Output the list of primes
      program.listPrimes();
      
   } // End main
   
   //------------------------------------------------------------------------//
   // Methods section                                                        //
   //------------------------------------------------------------------------//
   public void getLimit() {
      String inputLine = "";   // User input
      boolean badLimit = true; // True when input is not valid
      
      while(badLimit) {
         inputLine = JOptionPane.showInputDialog("Please enter an upper limit" 
               + " for a list of prime numbers, make sure it is a whole number"
               + " and not less than two.");
         try {    
            upperLimit = Integer.parseInt(inputLine);
            if(upperLimit >= 2)
               badLimit = false;
         }
         catch(NumberFormatException e) {
         } // End try / catch block
   
      } // End while loop
   }
   
   //------------------------------------------------------------------------//
   public void findPrimes() {
      int i           = 0;
      int currentNum  = 2;    // Two is the first prime
      boolean isPrime = true; // whether the current candidate is prime
      
      primesList.add(currentNum);
      
      for (currentNum = 3; currentNum <= upperLimit; ++currentNum) {
         isPrime = true; // Assume every candidate is true at first
         
         // Check every prime in the list
         for(i = 0; i < primesList.size(); ++i) {
            if((currentNum % primesList.get(i)) == 0) {
               isPrime = false;
            }
         }
         // If candidate passes the tests add it to the list
         if(isPrime) {
            primesList.add(currentNum);
         }
         
      } // End for loop

   }

   //------------------------------------------------------------------------//
   public void listPrimes() {
      int i              = 0;
      String primeString = "";
      
      // Put the entire list of primes in a string
      primeString = "" + primesList.get(i) + " "; // Always get the first prime
      
      for(i = 1; i < primesList.size(); ++i) {
         primeString = primeString + primesList.get(i)+ " ";
      if(i % 14 == 0) {
         primeString.trim();
         primeString = primeString  + "\r\n";
      }
      }
      // Print the report
      JOptionPane.showMessageDialog(null, "Prime numbers report by Stephen "
            + "Bapple" + "\r\n" + "The limit you entered was: " + upperLimit 
            + "\r\n" + "And the primes in that range were:" + "\r\n"
            + primeString);
   }
   
   //------------------------------------------------------------------------//
   
} // End class
