/** TestTriangle
    This class runs a loop to test the triangle class and its custom exception.
    
    Stephen Bapple
    
    CS 2, Fall 2015
    
    October

*/

import java.util.Scanner;

public class TestTriangle {
   public static void main(String[] args) {
      Scanner keys = new Scanner(System.in);
      
      // Initial prompt
      System.out.println("Enter a triangle? Type nothing to quit.");
      String inputStr = keys.nextLine();
      
      // loop until user is done
      while(!inputStr.equals("") && !inputStr.toUpperCase().equals("NO")) {
         // Attempt to get the triangle's dimensions
         try {
         
            // Get sides
            System.out.println("Sides?");
            double s1 = keys.nextDouble();
            double s2 = keys.nextDouble();
            double s3 = keys.nextDouble();
            
            // Get filled value
            System.out.println("Is it filled? (true or false):");
            boolean f = keys.nextBoolean();
            keys.nextLine(); // Clear input
            
            // Get color
            System.out.println("What color?");
            String c = keys.nextLine();
            
            // Attempt to initialize the triangle
            Triangle myTriangle = new Triangle(s1,s2,s3,c,f);
            System.out.println(myTriangle.toString());
            System.out.println("Area: " + myTriangle.getArea() + "\r\n" 
                  + "Perimeter: " + myTriangle.getPerimeter());
         }
         
         // Catch the Exception when invalid sides are entered
         catch(IllegalTriangleException ex) {
            System.out.println(ex);
         }
         
         // Prompt the user to enter another
         finally {
            System.out.println("Enter another triangle?");
            inputStr = keys.nextLine();
         } // End try / catch / finally
      
      } // End while loop
      
      // End program
      System.out.println("Bye!");
      
   } // End main
   
} // End class