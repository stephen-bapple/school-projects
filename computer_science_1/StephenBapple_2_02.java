/** Interactive Area and Perimeter Program

    This program lets a user input three sides of a triangle and then 
    calculates the perimeter and the area.
    
    Stephen Bapple
    
    Program #2, CS 1050, Section 2.
    jGrasp v2.0.1_01, Sony Vaio laptop, Windows 7.

    Indefatigable: Adjective: said of a person who persists tirelessly,
    or their tireless efforts.

    "It does not matter how slowly you go as long as you do not stop"
    -Confucius (551 - 479 BCE)
    
*/

import java.text.DecimalFormat;
import java.util.Scanner;

public class StephenBapple_2_02 {

   public static void main (String [] args) {
   
      // Declarations   
      Scanner keyboard = new Scanner(System.in);              
      DecimalFormat round2Places = new DecimalFormat("0.00");
      
      // Input variables - sides of the triangle
      double triSide1 = 0; // First side
      double triSide2 = 0; // Second side
      double triSide3 = 0; // Third side
      
      // Output variables - perimeter and area of the triangle
      double perTriangle   = 0; // Perimeter of the triangle
      double halfPer       = 0; // Half the perimeter
      double areaTriangle  = 0; // Area of the triangle
      
      // Explain the program to the user      
      System.out.println("This program program lets you input" 
            + " the three sides of a triangle,");
      System.out.println("and then finds the perimeter and" 
            + " the area of the triangle.");
      
      // Input the three sides      
      System.out.print("Please input the first side: ");
      triSide1 = keyboard.nextDouble();               
      System.out.print("Please input the second side: ");
      triSide2 = keyboard.nextDouble();                   
      System.out.print("Please input the third side: ");
      triSide3 = keyboard.nextDouble();                   
      
      // Calculate the perimeter and area      
      // The perimeter is the sum of the sides 
      perTriangle  = triSide1 + triSide2 + triSide3;         
      halfPer      = perTriangle / 2.0; // Half perimeter for Heron's formula  
      areaTriangle = Math.sqrt(halfPer 
            * (halfPer - triSide1) 
            * (halfPer - triSide2) 
            * (halfPer - triSide3));    // Calculate area using Heron's formula
      
      // Output Results
      System.out.println("The triangle with the sides "
            + round2Places.format(triSide1) + ", " 
            + round2Places.format(triSide2) 
            + ", and " + round2Places.format(triSide3) 
            + ", has");                           // Output rounded sides  
      System.out.println("a perimeter of " 
            + round2Places.format(perTriangle));  // Output rounded perimeter
      System.out.println("and an area of " 
            + round2Places.format(areaTriangle)); // Output rounded area
      System.out.println("Stephen Bapple.");      // Signature
      
      // Close files and exit
      keyboard.close();
      System.exit(0);
   } // End main
} // End class