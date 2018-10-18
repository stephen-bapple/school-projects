/** This class runs the big addition program by calling methods from the class
    BigAddition and JStack.
    
    Stephen Bapple
    
    CS 2, Fall 2015

*/

import java.io.*;

public class BigAdditionDriver {
   
   // Main - creates a BigAddition object and calls methods
   public static void main(String [] args)throws IOException {
      BigAddition bigAdd = new BigAddition(); 
      
      // -call: process problems
      bigAdd.processProblems();
      
      /* Small test to make sure int division works as I understand it 
      System.out.println((10 / 10) + " " + (10 % 10));
      System.out.println((28 / 10) + " " + (28 % 10));
      System.out.println((9 / 10) + " " + (9 % 10));
      */
   }
}
 
