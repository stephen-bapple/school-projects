/** 
 * Fibonacci calculator that uses lookup tables.
 *
 * Only goes up to fib(92) due to overflow of long type.
 *
 */

import java.util.Scanner;

public class FibonacciLookup {

   private long[] caseTable;
   private int baseIndex = 1;
   
   //------------------------------------------------------------------------//
   // Main                                                                   //
   //------------------------------------------------------------------------//
   public static void main(String [] args) {
      FibonacciLookup prog = new FibonacciLookup();
      prog.getN();
      
      System.exit(0);
      
   }

   //------------------------------------------------------------------------//
   // Public fibonnaci handler                                               //
   //------------------------------------------------------------------------//
   public long fib(int n) {
      return fib(n, 0);
   }

   //------------------------------------------------------------------------//
   // Recursive fibonnaci calculator                                         //
   //------------------------------------------------------------------------//
   private long fib(int n, int c) {
      //System.out.print("Calling  ");
      //printDots(c);
      if(n <= baseIndex + 1) {
         ++baseIndex;
         //System.out.print("Returning");
         //printDots(c);
         return caseTable[n - 1];
      }

      caseTable[n - 1] = fib(n-1, c + 1) + fib(n-2, c + 1);
      //System.out.print("Returning");
      //printDots(c);
      return caseTable[n - 1];
   }

   //------------------------------------------------------------------------//
   // Get an index from the user                                             \|
   //------------------------------------------------------------------------\\
   public void getN() {
      // Declarations
      int n;
      Scanner keyboard = new Scanner(System.in);
      
      // Get input
      System.out.print("Please enter a position "
            + "(starting at 1) to get a fibonacci from: ");
      n = keyboard.nextInt();
      
      // Intialize table and call recursion
      caseTable = new long[n + 1];
      caseTable[0] = 1;
      caseTable[1] = 1;
      System.out.println("The fibbonacci of " + n + " is: " + fib(n));
      
      return;
   }
   
   //------------------------------------------------------------------------//
   // Test table contents                                                    //
   //------------------------------------------------------------------------//
   private void printCasesFound() {
      for (int i = 0; i < caseTable.length - 1; ++i) {
         System.out.print(caseTable[i] + " ");
      }
      System.out.println();
   }
   
   //------------------------------------------------------------------------//
   private void printDots(int dots) {
      for (int i = 0; i < dots; ++i) {
         System.out.print('.');
      }

      System.out.println();
      return;
   }
   
   //------------------------------------------------------------------------//
}

