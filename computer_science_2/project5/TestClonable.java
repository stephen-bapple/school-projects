/** TestClonable
    This program tests if Octagon implemented Clonable and Comparable correctly
    
    Stephen Bapple
    
    CS2, Fall 2015
    October
    
    Specifications taken directly from Introduction to Java Programming by
    Daniel Liang. Page 530, exercise 13.11. Otherwise, all code here is original
   
    Limitations:
      - Does not take octagons with differing sides
      - Does not format output
*/

import java.util.Scanner;
import java.io.*;

public class TestClonable {
   public static void main(String [] args) throws IOException {
      Scanner inputFile = new Scanner(new File("OctagonsToClone.txt"));
      PrintWriter outputFile = new PrintWriter(
            new FileWriter("CloningResults.txt"));
            
      while(inputFile.hasNext()) {
         Octagon testOctagon = new Octagon(inputFile.nextDouble());
         Octagon clonedOctagon = testOctagon.clone();
         outputFile.println("Area: " + testOctagon.getArea() + "\r\n" 
               + "Perimeter: " + testOctagon.getPerimeter());
         
         if (testOctagon.compareTo(clonedOctagon) == 0) { 
            outputFile.println("Test passed, objects have same dimensions");
         }
         else {
            outputFile.println("Test failed, something went wrong");
         }
      }
      
      inputFile.close();
      outputFile.close();
   }
}