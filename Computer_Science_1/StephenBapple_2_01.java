/** Interactive Average Program <NAME of program>
    This program asks the user to input two real numbers,
    calculates the average of these numbers, and prints the results

    Stephen Bapple

    Program #1, CS 1050, Section 2.
    jGrasp v2.0.1_01, Sony Vaio laptop, Windows 7.
    
    Cogent: something that is clear, logical, and convincing.
    Example: the lobbyist made a very cogent argument for the adoption
    of the newly proposed budget.
    
    "The most difficult thing is the decision to act,
    the rest is merely tenacity." -Amelia Earhart (1898 - 1937)
*/

import java.util.Scanner;

public class StephenBapple_2_01 {

   public static void main (String [] args) {
   
      Scanner console = new Scanner(System.in);
      double num1; 
      double num2;        // Input values
      double average;    // Average of the input values
      
      // Explain the program to the userr
      System.out.println("This program averages two real numbers.");
      
      //Input the two numbers
      System.out.print("Input your first number:");
      num1 = console.nextDouble();
      System.out.print("Input your second number: ");
      num2 = console.nextDouble();
      
      //Calculate the average of the two numbers
      average = (num1 + num2) / 2.0;
      
      //Output the results
      System.out.print("The average of " + num1);
      System.out.println(" and " + num2 + " is " + average);
      System.out.println("Stephen Bapple");
      
      //Close files and exit
      console.close();
      System.exit(0);
   } // End main
} // End class