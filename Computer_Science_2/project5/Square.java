/** Square
    Object for colorable squares
    
    Stephen Bapple
    
    CS2, Fall 2015
    October
    
    Specifications taken directly from Introduction to Java Programming by
    Daniel Liang. Page 530, exercise 13.7. Otherwise, all code here is original
    
*/

public class Square extends GeometricObject implements Colorable {
   /** Instance data */
   private double oneSide;
   
   /** Constructor initializes Square with a side length of 1.0 */
   public Square() {
      this.oneSide = 1.0;
   }
   
   /** Constructor with the side as a parameter */
   public Square(double oneSide) {
      this.oneSide = oneSide;
   }
   
   /** Return the side */
   public double getSide() { 
      return oneSide;
   }
   
   /** Set the side */
   public void setSide(double oneSide) {
      this.oneSide = oneSide;
   }
   /** Return the area */
   @Override
   public double getArea() {
      return oneSide * oneSide;
   }
   
   /** Return the perimeter */
   @Override
   public double getPerimeter() {
      return 4 * oneSide;
   }
   
   /** How to color a square */
   @Override
   public void howToColor() {
      System.out.println("Color all four sides");
   }
}