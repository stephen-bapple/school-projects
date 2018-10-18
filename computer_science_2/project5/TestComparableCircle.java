/** TestComparableCircle
    Small test program for testing compareTo in ComparableCircle

    Stephen Bapple
    
    CS2, Fall 2015
    October   
*/

import java.util.Scanner;

public class TestComparableCircle {
   public static void main(String [] args) {
      // Scanner
      Scanner keyboard = new Scanner(System.in);
      
      // Get the first circle
      System.out.println("Enter a radius for circle 1");
      ComparableCircle circle1 = new ComparableCircle(keyboard.nextDouble());      
      
      // Get the second
      System.out.println("Enter a radius for circle 2");
      Circle circle2 = new Circle(keyboard.nextDouble());
      
      
      // Test which circle is bigger and inform user
      if (circle1.compareTo(circle2) > 0.0)
         System.out.println("Circle 1 is bigger than circle 2");
      else if (circle1.compareTo(circle2) < 0.0)
         System.out.println("Circle 1 is smaller than circle 2");
      else
         System.out.println("The circles are the same size");
   }
}