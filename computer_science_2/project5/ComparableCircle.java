/** ComparableCircle
    
    This class extends Circle and implements Comparable, making it possible
    to compare two circles by their area (only the first needs to be 
    comparable, since Circle has getArea() already)
   
    Stephen Bapple
    
    CS2, Fall 2015
    October
    
    Specifications taken directly from Introduction to Java Programming by
    Daniel Liang. Page 530, exercise 13.6. Otherwise, all code here is original
    
*/

public class ComparableCircle extends Circle 
      implements Comparable<Circle> {
   /** Constructor */
   public ComparableCircle(double radius) {
      super(radius);
   }
   
   /** Compare two circles based on their area */
   public int compareTo(Circle obj) {
         if (getArea() - obj.getArea() > 0.0)
            return 1;
         else if (getArea() - obj.getArea() < 0.0)
            return -1;
         else
            return 0;
   }
   
}
