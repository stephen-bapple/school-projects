/** Interactive Area and Perimeter Program v2.

    This program uses dialog boxes to let the user input three sides of
    a triangle and then calculates the perimeter and the area.
    
    Stephen Bapple
    
    Program #3, CS 1050, Section 2.
    jGrasp v2.0.1_01, Sony Vaio laptop, Windows 7.

    Indomitable: not easily discouraged or subdued.
    
    "Expect problems and eat them for breakfast."
    -Alfred A. Montapert (1906 - 1997)
    
*/

import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import java.util.*;

public class StephenBapple_2_03 {

   public static void main (String [] args) {
   
      // Declarations - formats & tokenizer
      DecimalFormat round1Place  = new DecimalFormat("0.0");
      DecimalFormat round2Places = new DecimalFormat("0.00");
      StringTokenizer stringT;
            
      // Declarations - sides of the triangle
      double triSide1 =  0; // First side
      double triSide2 =  0; // Second side
      double triSide3 =  0; // Third side
      String inputStr = ""; // String that holds the three sides
      
      // Output variables - perimeter and area of the triangle
      double perTriangle  = 0; // Perimeter of the triangle
      double halfPer      = 0; // Half the perimeter
      double areaTriangle = 0; // Area of the triangle
      
      // Input the three sides
      inputStr = JOptionPane.showInputDialog("Please enter three sides of a "
            + "triangle seperated by spaces:");
      stringT  = new StringTokenizer(inputStr);
      triSide1 = Double.parseDouble(stringT.nextToken()); 
      triSide2 = Double.parseDouble(stringT.nextToken());
      triSide3 = Double.parseDouble(stringT.nextToken());                 
      
      // Calculate the perimeter and area   
      // The perimeter is the sum of the sides
      perTriangle  = triSide1 + triSide2 + triSide3;
      halfPer      = perTriangle / 2; // Half perimeter for Heron's formula
      areaTriangle = Math.sqrt(halfPer 
            * (halfPer - triSide1) 
            * (halfPer - triSide2) 
            * (halfPer - triSide3));  // Calculate area using Heron's formula
      
      // Output Results
      JOptionPane.showMessageDialog(null, "The triangle with the sides "
            + round2Places.format(triSide1) + ", " 
            + round2Places.format(triSide2) + ", and " 
            + round2Places.format(triSide3) + " has:" + "\n"
            + "A perimeter of: " + round1Place.format(perTriangle) + "\n"
            + "An approximate area of: " + round1Place.format(areaTriangle)
            + "\n" + "And a true area of: " + areaTriangle + "\n"
            + "Stephen Bapple");
      
      // Close files and exit
      System.exit(0);
   } // End main
} // End class