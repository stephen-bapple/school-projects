/** Interactive area and perimeter program with methods.

    This program lets a user input three sides of a triangle and then 
    calculates the perimeter and the area using methods.
    
    Stephen Bapple
    
    Program #2, CS 1050, Section 2.
    jGrasp v2.0.1_01, Sony Vaio laptop, Windows 7.
    
    Recondite: little known, abtruse.
    
    "He who has a why to live can bear almost any how."
    -Friedrich Nietzsche (1844 - 1900)
    
*/
import java.text.DecimalFormat;
import java.util.Scanner;

public class StephenBapple_2_05 {

   public static void main (String [] args) {
      
      // Input variables - sides of the triangle
      double triSide1 = 0; // First side
      double triSide2 = 0; // Second side
      double triSide3 = 0; // Third side
      
      // Output variables
      double perTriangle   = 0; // Perimeter of the triangle
      double areaTriangle  = 0; // Area of the triangle
      
      // Call method that explains the program
      explainProgram();
      
      // Call method that gets sides
      triSide1 = getSide(1);
      triSide2 = getSide(2);
      triSide3 = getSide(3);
      
      // Call method that calculates perimeter
      perTriangle = calcPerimeter(triSide1, triSide2, triSide3);
      
      // Call method that calculates area
      areaTriangle = calcArea(triSide1, triSide2, triSide3);
      
      // Call method that outputs results
      outputResults(triSide1, triSide2, triSide3, perTriangle, areaTriangle);
      
      
   System.exit(0);
   } // End main
   
   //------------------------------------------------------------------------//
   
   // Methods section
   
   // Explains the program to the user
   public static void explainProgram() {
      System.out.println("This program uses methods to find the perimeter and"
            +"\r\n" + "area of a real triangle from user inputted sides." 
            + "\r\n" + "Stephen Bapple");
      return;
   } // End explainProgram
   
   //------------------------------------------------------------------------//

   // Returns the number input by the user
   public static double getSide(int whichSide){
      
      // Declarations
      Scanner keyboard = new Scanner(System.in);
      double thisSide = 0;        // User input
      boolean isPositive = false; // Whether or not the side is real
      
      while (!isPositive){
         // Get side
         System.out.print("Enter side " + whichSide + ": ");
         thisSide = keyboard.nextDouble();
      
         if (thisSide > 0) {
            isPositive = true;
         }
         else {
            System.out.println("All sides must be postive.");
         } // End if/else
      } // End while
      return(thisSide);
   } // End getSide
   
   //------------------------------------------------------------------------//

   
   // Calculates the perimeter
   public static double calcPerimeter(double a, double b, double c) {
      double perimeter = a + b + c;
      return(perimeter);
   } // End calcPerimeter
   
   //------------------------------------------------------------------------//
   
   // Calculates the area
   public static double calcArea(double a, double b, double c) {
      double area = 0;
      double s = 0;
      s = calcPerimeter(a, b, c) / 2.0;
      area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
      return(area);
   } // End calcArea
   
   //------------------------------------------------------------------------//

   // Outputs the three sides, the perimeter, and the area
   public static void outputResults(double side1, double side2, 
         double side3, double perimeter, double area) {
      
      // Declarations
      DecimalFormat round2Places = new DecimalFormat("0.00");
      
      // Print output   
      System.out.println("The triangle with the sides "
            + round2Places.format(side1) + ", " 
            + round2Places.format(side2) 
            + ", and " 
            + round2Places.format(side3) + ","); // Output rounded sides
      System.out.println("has a perimeter of " 
            + round2Places.format(perimeter));   // Output rounded perimeter
      System.out.println("and an area of " 
            + round2Places.format(area));        // Output rounded area
      System.out.println("Stephen Bapple.");     // Signature
      
   } // End outputResults
} // End class