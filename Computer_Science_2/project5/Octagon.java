/** Octagon
    Class for the Octagon shape that implements Comparable and Cloneable
    
    Stephen Bapple
    
    CS2, Fall 2015
    October
    
    Specifications taken directly from Introduction to Java Programming by
    Daniel Liang. Page 530, exercise 13.11. Otherwise, all code here is original
    
    Limitations:
      - As in specs, all sides are equal
*/

public class Octagon extends GeometricObject implements Comparable<Octagon>,
      Cloneable {
   // Instance data
   private double sides;
   
   /** Default Constructor */
   public Octagon() {
      this.sides = 1.0;
   }

   /** Input equal sides constructor */
   public Octagon(double sides) {
      this.sides = sides;
   }
   
   /** Return the side lengths */
   public double getSides() {
      return sides;
   }
   
   /** Set the side lengths */
   public void setSides(double sides) {
      this.sides = sides;
   }
   
   /** Return the perimeter */
   public double getPerimeter() {
      return 8 * sides;
   }
   
   /** Return the area */
   public double getArea() {
      return (2 + 4 / Math.sqrt(2)) * sides * sides; 
   }
   
   /** Compare object */
   @Override
   public int compareTo(Octagon obj) {
      if (getArea() - obj.getArea() > 0.0)
         return 1;
      else if (getArea() - obj.getArea() < 0.0)
         return -1;
      else
         return 0;
   
   }
   
   /** Clone object */
   @Override
   public Octagon clone() {
      return new Octagon(sides);
   }
}