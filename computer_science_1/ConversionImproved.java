/**
 *  Improved version of the temperature conversion program.
 */

import java.util.Scanner;

public class ConversionImproved {

   public static void main (String [] args) {
   
      //declare variables
      double farenheit = 0;
      double celcius   = 0;
      double kelvin    = 0;
      
      Scanner keyboard = new Scanner(System.in);
      
      //Declare constants
      final double KELVIN_CONSTANT      = 273.15;
      final double FARENHEIT_TO_CELCIUS = 5.0/9.0;
      final double CELCIUS_TO_FARENHEIT = 9.0/5.0;
      final double FARENHEIT_INCREMENT  = 32.0;
      
      char convert = 'c';
      
      final String prompt = "Enter \'c\', or \'k\', or \'f\', or "
      + "\'q\' to quit: ";
      
      while (convert != 'q') {
         System.out.print(prompt);
         convert = keyboard.next().charAt(0);
         if (convert == 'q')
            break;
            
            switch (convert) {
               case 'c': System.out.print("Enter celcius temp: ");
                         celcius = keyboard.nextDouble();
                         farenheit = celcius * CELCIUS_TO_FARENHEIT 
                         + FARENHEIT_INCREMENT;
                         kelvin = celcius + KELVIN_CONSTANT;
                  break;          
               case 'f': System.out.print("Enter farenheit temp: ");
                         farenheit = keyboard.nextDouble();
                         celcius = (farenheit - FARENHEIT_INCREMENT) * FARENHEIT_TO_CELCIUS;
                  break;
               case 'k':/*
                         System.out.print("Enter kelvin temp: "); */
                  break; 
               
                   
            } //End switch
      System.out.println("farenheit = " + farenheit + "\n" + "and celcius = " 
                         + celcius + "\n" + "and kelvin = " + kelvin);  
         
      } //End while
      
   keyboard.close();
   System.exit(0);   
   
   } //End main
   
} //End class
