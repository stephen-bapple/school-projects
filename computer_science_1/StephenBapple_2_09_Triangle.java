/** Triangle class - acquire sides and calculate perimeter and area.
    
    This class has the three sides of a triangle as private variables, 
    and calculates the perimeter and area of the triangle as well as checks if
    the triangle is real.
    
    Methods used in the class:
    
    StephenBapple_2_09_Triangle() - Default constructor initializes triSide1,
                  triSide2, and triSide3 to 0.0
    
    StephenBapple_2_09_Triangle (double side1, double side2, double side3) - 
                  constructor that intializes triSide1, triSide2, and triSide3 
                  to the parameters side1 side2, and side3.
    getTheSides() - Method calls the private helper method three times to get
                  each side.               
    
    enterASide(int whichSide, Scanner console) - Prompts the user to enter a
                  side corresponding to whichSide. If the user input is greater
                  than zero the method assigns the value to the selected 
                  triSide.
                  If the input is negative the method prompts the user to enter 
                  a positive value.
    
    calcArea() - Method uses the private side lengths to calculate the area
    
    calcPerimeter() - Method uses the private side lengths to calculate the 
                  perimeter.
    
    isReal() - Method checks if the given sides make up a real triangle.
    
    outputLengths() - Outputs the three sides of the triangle to the console.

*/
import java.util.Scanner;
import java.text.DecimalFormat;

public class StephenBapple_2_09_Triangle {
   // Declarations
   private DecimalFormat round2Places = new DecimalFormat("0.00");
   private Scanner console = new Scanner(System.in);
   private double triSide1; // Side one
   private double triSide2; // Side two
   private double triSide3; // Side three
   
   //------------------------------------------------------------------------//
   // Constructors                                                           //
   //------------------------------------------------------------------------//
   public StephenBapple_2_09_Triangle(){
      // Initialize all sides to zero
      triSide1 = 0.0;
      triSide2 = 0.0;
      triSide3 = 0.0;
      
      return;
   }
   //------------------------------------------------------------------------//
   public StephenBapple_2_09_Triangle(double side1, double side2,
         double side3) {
      // Initialize the sides to the parameters
      triSide1 = side1;
      triSide2 = side2;
      triSide3 = side3;

      return;
   }
   
   //------------------------------------------------------------------------//
   // Private helper methods                                                         //
   //------------------------------------------------------------------------//
   private void enterASide(String whichSide) {
      double aSide = 0.0;
      System.out.print("Please enter side number " + whichSide 
      + ": ");
      
      aSide = console.nextDouble();
      if (aSide <= 0) {
         System.out.println("Please enter a positive value");
         aSide = console.nextDouble();
      }

      switch (whichSide) {
         case "one": triSide1 = aSide;
            break;
         case "two": triSide2 = aSide;
            break;
         case "three": triSide3 = aSide;
            break;
      } // End switch
      
      return;
   }
   //------------------------------------------------------------------------//
   // Public methods
   //------------------------------------------------------------------------//
   public void getTheSides() {
      enterASide("one");
      enterASide("two");
      enterASide("three");
      
      return;
   }
   
   //------------------------------------------------------------------------//
   public boolean isReal() {
      // Local boolean
      boolean realTriangle = false;
      
      if ((triSide1 + triSide2 > triSide3)
            && (triSide2 + triSide3 > triSide1)
            && (triSide1 + triSide3 > triSide2)) {
         realTriangle = true;
      }
      
      if (!realTriangle) {
         System.out.println("The sides do not make a real triangle,"
               + " the largest side must be less than the sum of the" 
               + " smaller sides." + "\r\n" + "Please try again." + "\r\n");
      }
      
      return(realTriangle);
   }
   
   //------------------------------------------------------------------------//
   public double calcPerimeter() {
      
      return(triSide1 + triSide2 + triSide3);
   }
   
   //------------------------------------------------------------------------//
   public double calcArea() {
      double halfPer = 0.0;
      
      halfPer = calcPerimeter() / 2.0;
      
      return(Math.sqrt(halfPer 
            * (halfPer - triSide1) 
            * (halfPer - triSide2) 
            * (halfPer - triSide3)));
      
   }
   
   //------------------------------------------------------------------------//
   public void outputLengths() {
      System.out.println("The triangle has sides of " 
         + round2Places.format(triSide1) + ", "
         + round2Places.format(triSide2) + ", and " 
         + round2Places.format(triSide3));
  
      return;
   }
   
} // End class