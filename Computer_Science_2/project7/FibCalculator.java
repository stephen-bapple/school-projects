/** FibCalculator

    This program contains the methods to calculate Fibonacci numbers using
    three different techniques. Regular recursion, lookup table enhanced
    recursion, and a loop. 

    Programmer: Stephen Bapple
    Professor:  Judith Gurka
    Class:      CS2, Fall 2015
    Month:      November
    
    Limitations:
       -Does not check for a valid file
       -Does not check for valid input
       -Output is not very easy to read with so many numbers
    
*/

import java.util.Scanner;
import java.io.*;

public class FibCalculator {
   private final int MAX = 30;
   private int[] caseTable;
   private int[] calls;
   private int[] answers;
   private int accesses;
   
   //------------------------------------------------------------------------//
   // Console input method                                                   //
   //------------------------------------------------------------------------//
   public void getInput() {
      Scanner keyboard = new Scanner(System.in);
      int n; 
      n = keyboard.nextInt();
      fib(n);
      //output(n);
   }
   
   //------------------------------------------------------------------------//
   // File input method                                                      //
   //------------------------------------------------------------------------//
   public void getFileInput() throws IOException {
      // Declarations
      Scanner inputFile = new Scanner(new File("FibInput.txt"));
      PrintWriter outputFile = new PrintWriter(new FileWriter("FibStats.txt"));
      int n;
      
      // Read and write every given Fibonacci
      while (inputFile.hasNext()) {
         n = inputFile.nextInt();
         fib(n);
         outputToFile(outputFile, n);
      }
      
      inputFile.close();
      outputFile.close();
   }
   
   //------------------------------------------------------------------------//
   // File output method                                                     //
   // Parameters and println statement were altered slightly from a console  //
   // version, to save space in code.                                        //
   //------------------------------------------------------------------------//
   private void outputToFile(PrintWriter outputFile, int n) {
      outputFile.println("Fib of: " + n + "\r\n" 
            + "Regular answer: " + answers[0] + "\r\n" 
            + "Optimized answer: " + answers[1] + "\r\n" 
            + "Loop answer: " + answers[2] + "\r\n" 
            + "Regular calls: " + calls[0] + "\r\n" 
            + "Optimized calls: " + calls[1] + "\r\n" 
            + "Loop iterations: " + calls [2] + "\r\n" 
            + "Optimized table accesses: " + accesses + "\r\n");
   }
   
   //------------------------------------------------------------------------//
   // Public Fibonacci handler                                               //
   //------------------------------------------------------------------------//
   public void fib(int n) {
      // Reset all counters, the answers, and the table
      calls        = new int[3];
      answers      = new int[3];
      accesses      = 0;
      caseTable    = new int[MAX];
      caseTable[0] = 1;
      caseTable[1] = 1;
      
      // Call all versions of fib to get the data
      answers[0] = fibAux(n);
      answers[1] = fibLookup(n);
      answers[2] = fibLoop(n);
   
   }
   
   //------------------------------------------------------------------------//
   // Unoptimized Fibonacci method                                           //
   //------------------------------------------------------------------------//
   private int fibAux(int n) {
      ++calls[0];
      
      // Base cases
      if (n <= 2)
         return 1;
      
      // Recursive case
      return fibAux(n - 1) + fibAux(n - 2);
      
      //return -1; // Stub return
   }
   
   //------------------------------------------------------------------------//
   // Fibonacci calculator enhanced with lookup table                        //
   //------------------------------------------------------------------------//    
   private int fibLookup(int n) {
      // Update counters
      ++calls[1];
      ++accesses; // Will always check table at least once in the if
      
      /* Lookup value, and return if already known
         Note: -1 is used for all table accesses since fib(0) is undefined */
      if (caseTable[n - 1] != 0) {
         ++accesses;
         return caseTable[n - 1];
      }
      
      // Else calculate it, store it in the table, and return it
      ++accesses;
      caseTable[n - 1] = fibLookup(n - 2) + fibLookup(n - 1);
      return caseTable[n - 1];
      
      //return -1; // Stub return
   }
   
   //------------------------------------------------------------------------//
   // Loop version of Fibonacci                                              //
   //------------------------------------------------------------------------//
   private int fibLoop(int n) {
      // If a default value is requested return it
      if (n < 3) {
         ++calls[2];
         return 1;
      }
      
      // Set the default values
      int fib1    = 1;
      int fib2    = 1;
      int nextFib = 0;
      
      // If a non default value is requested loop to find it
      for (int i = 3; i <= n; ++i) {
         ++calls[2];
         nextFib = fib1 + fib2;
         fib1 = fib2;
         fib2 = nextFib;
      }
    
      return(nextFib);
      
      //return -1; // Stub return
   }
   
   //------------------------------------------------------------------------//

}