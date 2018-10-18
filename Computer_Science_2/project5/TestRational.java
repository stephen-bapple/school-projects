/** TestRational
    This program tests if Rational implemented the internal array correctly
    
    Stephen Bapple
    
    CS2, Fall 2015
    October
    
    Specifications taken directly from Introduction to Java Programming by
    Daniel Liang. Page 530, exercise 13.14. Otherwise, all code here is original
   
*/

import java.util.Scanner;

public class TestRational {
   public static void main(String [] args) {
      // Declarations
      int n = 0; // The numerator
      int d = 0; // The denominator
      String input = "";
      Scanner keyboard = new Scanner(System.in);
      
      // Loop while user inputs numbers
      while(!input.equals("Q")) {
         // Prompt for input
         System.out.print("Enter the numerator and denominator of a number,"
            + " or Q to quit:");
         input = keyboard.next();
         
         // Get numbers if user doesn't want to quit
         if (!input.equals("Q")) {
            n = Integer.parseInt(input);
            d = keyboard.nextInt();
         }
         else {
            System.out.println("Goodbye!");
            break;
         }
         
         // Output the number and its double value
         Rational number = new Rational(n, d);
         System.out.println("You entered: " + number.toString() + "\n" + 
               "The decimal value of that number is: " + number.doubleValue());
         
      } // End while loop
   
   } // End main
   
} // End class