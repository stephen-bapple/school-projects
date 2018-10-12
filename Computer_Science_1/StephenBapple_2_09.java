/** This program gets the three sides of a triangle from a user's keyboard
    input, checks if the triangle is real, and if so then calculates the 
    perimeter and the area of the triangle. 
    
    Note: this program uses the seperate class file StephenBapple_2_09_Triangle
    for the triangle object.
    
    Stephen Bapple
    
    Program #9, CS 1050, Section 2.
    jGrasp v2.0.1_01, Sony Vaio laptop, Windows 7.
    
    Methods used in the main program:
    
    explainProgram() - Outputs an explanation of what the program does
    
    outputResults() - Calls methods from the triangle class file
                   to get the sides of the triangle from the user, and 
                   calculate the perimeter and the area, and then prints the 
                   results.
    
    Extremum: A mathematical term for a maximum or minimum value 
    of a set of numbers or a graph.
    
    "Fortune sides with him who dares."
    -Virgil (70 - 19BC)
    
*/
import java.text.DecimalFormat;

public class StephenBapple_2_09 {
   static DecimalFormat round1Place = new DecimalFormat("0.0"); 

   public static void main(String [] args) {
      
      // Declarations
      StephenBapple_2_09_Triangle triangle = new StephenBapple_2_09_Triangle();
      double area = 0.0;
      double perimeter = 0.0;
      
      // Explain the program
      explainProgram();
      
      // Get the sides
      triangle.getTheSides();
      
      // Get the perimeter and area
      area = triangle.calcArea();
      perimeter = triangle.calcPerimeter();
      
      // Output the sides, area, and perimeter of the triangle if it is real
      outputResults(triangle, area, perimeter);
   
   } // End main
   
   //------------------------------------------------------------------------//
   // Methods section                                                        //
   //------------------------------------------------------------------------//
   public static void explainProgram() {
      System.out.println("This program gets the three sides of a triangle from"
            + " the user, and finds the perimeter and area of the triangle."
            + "\r\n" + "Program note: this program uses classes and methods.");
      
      return; 
   }
   
   //------------------------------------------------------------------------//
   public static void outputResults(StephenBapple_2_09_Triangle triangle, 
         double area, double perimeter) {
   
      if(triangle.isReal()) {
         triangle.outputLengths(); // Method gets the three sides
         System.out.println("The triangle has a perimeter of "
               + round1Place.format(perimeter) 
               + " and an area of " 
               + round1Place.format(area));
      }

      return;
   }
   
   //------------------------------------------------------------------------//
   
} // End class