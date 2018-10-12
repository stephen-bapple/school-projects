/**
 * This program is a simple command line calculator.
 * It's purpose is simply for practicing Java.
 */
import java.util.Scanner;

public class SimpleCalculator {

   public static void main (String [] args) {
      // Declarations
      String answer = "";
      Scanner keyboard = new Scanner(System.in);
      char char0 = '?'; 
      
      double operand1;
      double operand2;
      double result;
      
      // Explain the program to the user
      System.out.println("This program does simple calculations.");
      
      while (char0 != 'q') {
         System.out.println("Enter Add, Subtract, multiply, or divide to select an operation,"
               + " or type Quit to quit.");
         answer = keyboard.nextLine() + " ";
         answer = answer.toUpperCase();
         char0 = answer.charAt(0);
      
         if (char0 == 'q') {
            System.exit(0);
         }
         else {
            // Ask for both operands
            System.out.println("Please enter the first operand.");
            operand1 = keyboard.nextDouble();
            System.out.println("Please enter the second operand.");
            operand2 = keyboard.nextDouble();
            
            //Do calculation
            switch (char0) {
               case 'A':
                  result = operand1 + operand2;
                  break;
               case 'S':
                  result = operand1 - operand2;
                  break;
               case 'M':
                  result = operand1 * operand2;
                  break;
               case 'D':
                  result = operand2 / operand2;
               case 'Q': 
                  System.exit(0);
                  break;
               default:
                  System.out.println(answer + "is invalid");
            } // End switch   
         } // End if else
      } // End while
      
      // output results

   } // End main
} // End class
