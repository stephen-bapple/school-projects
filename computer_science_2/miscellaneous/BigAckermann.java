/** BigAckermann
 *
 *  This program contains the loop version of Ackermann from AckCalculator
 *  enhanced with BigInteger to allow calculation of larger Ackermann values.
 *    
 *  Programmer: Stephen Bapple
 *  Professor:  Judith Gurka
 *  Class:      CS2, Fall 2015
 *  Month:      November
 *  
 *  Limitations:
 *     -Does not check for valid input
 *     -Output is not very easy to read with so many numbers  
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.math.BigInteger;

public class BigAckermann {
   private long calls;
   private BigInteger answer;

   //------------------------------------------------------------------------//
   // Main                                                                   //
   //------------------------------------------------------------------------//
   public static void main(String[] args) {
      BigAckermann program = new BigAckermann();
      System.exit(program.getUserInput(args));
   }
   
   //------------------------------------------------------------------------//
   // Console input / output method                                          //
   //------------------------------------------------------------------------//
   public int getUserInput(String[] args) {
      int x, y;

      if (args.length == 0) {
         Scanner keyboard = new Scanner(System.in);
         System.out.print("Enter an x and y value for Ackermann: ");
         x = keyboard.nextInt();
         y = keyboard.nextInt();
      }
      else if (args.length == 2) {
         try {
            x = Integer.parseInt(args[0]);
            y = Integer.parseInt(args[1]);
         } 
         catch (Exception e) {
            System.out.println("Invalid arguments supplied to program.\n"
                             + " Enter 2 positive integers.");
            return -1;
         }
      }
      else {
         System.out.println("Invalid number of aruments supplied to program.");
         return -1;
      }         

      // Print answer.
      ack(x, y);
      System.out.println(answer.toString());
      System.out.println("Iterations: " + calls);
      return 0;
   }
 
   //------------------------------------------------------------------------//
   // Public Ackerman handler                                                //
   //------------------------------------------------------------------------//
   public void ack(int x, int intY) {
      // Reset table, answers and all counters to zero
      calls = 0;

      // Run Ackermann
      BigInteger y = BigInteger.valueOf(intY);
      answer = ackLoop(x, y);
   }

   //------------------------------------------------------------------------//
   // Loop version of Ackerman                                               //
   //------------------------------------------------------------------------//
   private BigInteger ackLoop(long x, BigInteger y) {
      ArrayList<long[]> table = new ArrayList<>();
      boolean xStored = true;
      
      do {
         ++calls;

         // Base case of x = 0
         if (x == 0) {
            y = y.add(BigInteger.valueOf(1));
            
            // Check if there are any stored x's to process
            if (table.size() > 0) {
               xStored = true;
               
               // Check if there are duplicates
               if (table.get(table.size() - 1)[1] > 1) {
                  x = table.get(table.size() - 1)[0]; // Get stored x
                  --table.get(table.size() - 1)[1];   // --duplicate counter
               }
               // If none, remove entry
               else {
                  x = table.remove(table.size() - 1)[0];
               }
            }
            else {
               xStored = false; // No more x to process
            }
         }
         // Semi-base case y = 0
         else if (y.equals(BigInteger.valueOf(0))) {
            --x;   // Decrement x
            y = BigInteger.valueOf(1); // Reset y
            
         }
         // Default case
         else {
            // If the x value isn't a duplicate store it
            if (table.size() == 0 || x - 1 != table.get(table.size() - 1)[0])
               table.add(new long[]{x - 1, 1});
            // Otherwise update its counter
            else
               ++table.get(table.size() - 1)[1];
            
            y = y.subtract(BigInteger.valueOf(1)); // Decrement y
         }
         
      } while (xStored);
      
      return y;
      
      //return -1; // Stub return
   } 
}

