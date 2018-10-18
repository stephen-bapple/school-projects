/** 
 * This program converts Fahrenheit to Celsius and kelvin.
 *  
 */

import java.util.Scanner;

public class Conversion {
   
   public static void main (String [] args) {
   
      Scanner keyboard = new Scanner(System.in); //declare scanner
      
      double tempInF = 0.0;                      //User input farenheit
      double tempInC = 0.0;                      //output in celcius
      double tempInK = 0.0;                      //output in kelvin
      
      // Explain the program
      System.out.println("This program converts Farenheit to Celcius and Kelvin");
      
      //Prompt for input
      System.out.println("Please input a temperature in Farenheit");
      
      tempInF = keyboard.nextDouble();            // Get input
      tempInC = (tempInF - 32.0) * (5.0 / 9.0);   // calculate celcius
      tempInK = (tempInF + 459.67) * (5.0 / 9.0); // calculate kelvin
      
      //Round the numbers
      tempInF = (double)Math.round(tempInF * 100) / 100;
      tempInC = (double)Math.round(tempInC * 100) / 100;
      tempInK = (double)Math.round(tempInK * 100) / 100;
      
      // Output results
      System.out.println(tempInF + " degrees Farenheit is " + tempInC + " degrees Celcius. And " 
      + tempInK + " Kelvin.");
      
      //chooses which closing message to display based on temperature
      if (tempInF <= 30) {
         System.out.println("Bundle up!");
         
      }
      else if (tempInF > 90){
      
         System.out.print("Wow! It's hot out!");//for temperatures over 90F
      } 
      else {
         System.out.print("Have a nice day!"); //Politeness costs a simple .print
      }
         
      return;
      
   }//main ends
}//class ends

