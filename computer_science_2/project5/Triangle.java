/** Triangle
    
    Stephen Bapple
    
    CS 2, fall 2015
    October 2015
    
    Extends Geometric object
      - Geometric Object authored by Daniel Liang, published in 
        "Introduction to Java Programming" on pages 496-498.
    
    This class file was written by Stephen Bapple, 
    but it extends GeometricObject and uses specifications from 
    "Introduction to Java Programming" by Daniel Liang, exercise 12.5, 
    on page 488, for the exception thrown, 
    as well as specifications on page 445 for the triangle class.
    
    Original specifications:
      - Triangle with 3 sides defaulted to 1.0.
      - Accessors.
      - getters for area and perimeter
      - no triangles where the sides do not meet
    
    Additional specifications
      - No negative or zero sides 
      - toString calls super class's toString as well.
*/

public class Triangle extends GeometricObject {
   // Private class fields
   private double side1 = 1.0;
   private double side2 = 1.0;
   private double side3 = 1.0;
   
   //------------------------------------------------------------------------//
   // Constructors                                                           //
   //------------------------------------------------------------------------//
   // Default constructors                                                   //
   //------------------------------------------------------------------------//
   public Triangle() {
      super("white", true);
      this.side1 = 1.0;
      this.side2 = 1.0;
      this.side3 = 1.0;
   }
   
   //------------------------------------------------------------------------//
   // Custom side constructor                                                //
   //------------------------------------------------------------------------//
   public Triangle(double side1, double side2, double side3, String color,
         boolean filled) throws IllegalTriangleException {
      super(color, filled);
      if (side3 + side2 > side1 && side3 + side1 > side2 
            && side2 + side1 > side3 && side1 > 0.0 && side2 > 0.0 
            && side3 >0.0) {
         this.side1 = side1;
         this.side2 = side2;
         this.side3 = side3;
      }
      else {
         throw new IllegalTriangleException();
      }
   }
   
   //------------------------------------------------------------------------//
   // Accessors                                                              //
   //------------------------------------------------------------------------//
   // Get the area                                                           //
   //------------------------------------------------------------------------//
   public double getArea() {
      double s = getPerimeter() / 2;
      return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
   }
   
   //------------------------------------------------------------------------//
   // Get the perimeter                                                      //
   //------------------------------------------------------------------------//
   public double getPerimeter() {
      return(side1 + side2 + side3);
   }
   
   //------------------------------------------------------------------------//
   // toString                                                               //
   //------------------------------------------------------------------------//
   @Override
   public String toString() {
      return "Triangle: side1 = " + side1 + " side2 = " + side2 + " side3 = " +
            side3 + "\r\n" + super.toString();
   }
      
   //------------------------------------------------------------------------//
   // Get side one                                                           //
   //------------------------------------------------------------------------//
   public double getSide1() {
      return side1;
   }
   
   //------------------------------------------------------------------------//
   // Get side two                                                           //
   //------------------------------------------------------------------------//
   public double getSide2() {
      return side2;
   }
   
   //------------------------------------------------------------------------//
   // Get side three                                                         //
   //------------------------------------------------------------------------//
   public double getSide3() {
      return side3;
   }
   
   //------------------------------------------------------------------------//
}