/** TestColorable
    This program tests if Colorable and Square work
    
    Stephen Bapple
    
    CS2, Fall 2015
    October
    
    Specifications taken directly from Introduction to Java Programming by
    Daniel Liang. Page 530, exercise 13.7. Otherwise, all code here is original
    
*/

public class TestColorable {
   public static void main(String [] args) {
      // Create the array
      GeometricObject[] array = new GeometricObject[5];
      
      // Fill the array
      array[0] = new Square(5.5);
      array[1] = new Circle(3.0);
      array[2] = new Square(6.9);
      array[3] = new Triangle();
      array[4] = new Square(1);
      
      // Loop through the array
      for (int i = 0; i < array.length; ++i) {
         System.out.println("Area: " + array[i].getArea());
         if(array[i] instanceof Colorable) { 
            ((Colorable)array[i]).howToColor();
         }
         else {
            System.out.println("Object is not colorable.");
         }
         
         System.out.println(); // Separate lines
      }
   }
}