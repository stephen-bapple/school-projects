/** AckCalculator

    This program contains the methods to calculate ackermann numbers using
    three different techniques. Regular recursion, lookup table enhanced
    recursion, and a loop. 

    Programmer: Stephen Bapple
    Professor:  Judith Gurka
    Class:      CS2, Fall 2015
    Month:      November
    
    Limitations:
       -Does not check for valid input
       -Output is not very easy to read with so many numbers
    
*/

import java.util.*;
import java.io.*;

public class AckCalculator {
   private final int MAX_X = 5;
   private final int MAX_Y = 101;
   //private final int MAX_Y = 5001; // Extra credit big table
   //private final int MAX_Y = 50001; // Extra credit biggest table
   private int[][] caseTable;
   private int[] calls;
   private int[] answers;
   private int accesses;
   private int outOfBounds;
   private int largestY;
   
   //------------------------------------------------------------------------//
   // Console input method                                                   //
   //------------------------------------------------------------------------//
   /* public void getInput() {
      Scanner keyboard = new Scanner(System.in);
      int x = keyboard.nextInt();
      int y = keyboard.nextInt();
      ack(x, y);
      output(x, y);
   } 
   */
   //------------------------------------------------------------------------//
   // Loop input method:                                                     //
   // Try to calculate all ack values from 0, 0, to 4, 15                    //
   //------------------------------------------------------------------------//
   public void calculateValues() throws IOException {
      PrintWriter outputFile = new PrintWriter(new File("AckResults.txt"));
      /* Files for larger table output:
      PrintWriter outputFile = new PrintWriter(new File("AckBigTable.txt"));
      PrintWriter outputFile = new PrintWriter(new File("BiggestTable.txt"));*/
      
      // Run through every x
      for (int x = 0; x < 5; ++x) {
         // Run through every y
         for (int y = 0; y < 16; ++y) {
            // Try to calculate an ackermann
            try {
               ack(x, y);
               output(x, y, outputFile);
            }
            catch(StackOverflowError ex) {
               outputFile.println("With " + x + " for x, " + y 
                     + " for y is too large to calculate, " + "\r\n"
                     + "calculation with higher y's impossible." + "\r\n");
               break; // Exit the for y loop
            }
         }
      }
      outputFile.close();
   }

   //------------------------------------------------------------------------//
   // File output method                                                     //
   // Parameters and println statement were altered slighty from a console   //
   // version, to save space in code.                                        //
   //------------------------------------------------------------------------//
   public void output(int x, int y, PrintWriter outputFile) {
      //System.out.println("Ack of: " + x + ", " + y + "\r\n"
      outputFile.println("Ack of: " + x + ", " + y + "\r\n"
            + "Regular answer: " + answers[0] + "\r\n" 
            + "Optimized answer: " + answers[1] + "\r\n" 
            + "Loop answer: " + answers[2] + "\r\n" 
            + "Regular calls: " + calls[0] + "\r\n" 
            + "Optimized calls: " + calls[1] + "\r\n" 
            + "Loop iterations: " + calls [2] + "\r\n" 
            + "Optimized table accesses: " + accesses + "\r\n"
            + "Out of bounds y values: " + outOfBounds + "\r\n"
            + "Maximum y value: " + largestY + "\r\n");
   }
   
   //------------------------------------------------------------------------//
   // Public Ackerman handler                                                //
   //------------------------------------------------------------------------//
   public void ack(int x, int y) {
      // Reset table, answers and all counters to zero
      caseTable   = new int[MAX_X][MAX_Y];
      calls       = new int[3];
      answers     = new int[3];
      accesses     = 0;
      outOfBounds = 0;
      largestY    = 0;
            
      // Call all versions of ackermann
      answers[0] = ackAux(x, y);
      answers[1] = ackLookup(x, y);
      answers[2] = ackLoop(x, y);
   }
   
   //------------------------------------------------------------------------//
   // Unoptimized Ackerman calculator                                        //
   //------------------------------------------------------------------------//
   private int ackAux(int x, int y) {
      ++calls[0];
      
      // Check base cases
      if (x == 0) {
         return y + 1;
      }
      else if (y == 0) {
         return ackAux(x - 1, 1);
      }
      
      // Otherwise recurse
      return ackAux(x - 1, ackAux(x, y - 1));

      //return -1; // Stub return
   }
   
   //------------------------------------------------------------------------//
   // Ackerman optimized with a lookup table                                 //
   //------------------------------------------------------------------------//
   private int ackLookup(int x, int y) {
      ++calls[1];
      
      // Update max y
      if (largestY < y )
         largestY = y;
         
      // Check if y is out of bounds
      if (y < MAX_Y) {
         // If not, check the lookup table
         ++accesses;
         if (caseTable[x][y] != 0) {
            ++accesses;
            return caseTable[x][y];
         }
      } // If y is out of bounds do normal recursion
      else {
         ++outOfBounds;
         if (x == 0)
            return y + 1;
         else if (y == 0)
            return ackLookup(x - 1, 1);
            
         return ackLookup(x - 1, ackLookup(x, y - 1));
      }
      
      // Check other base cases if y < 0 and lookup empty
      if (x == 0) {
         ++accesses;
         return caseTable[x][y] = y + 1;
      }
      else if (y == 0) {
         ++accesses;
         return caseTable[x][y] = ackLookup(x - 1, 1);
      }
      
      // Otherwise recurse and update table
      ++accesses;
      return caseTable[x][y] = ackLookup(x - 1, ackLookup(x, y - 1));
      
      //return -1; // Stub return
   }
   
   //------------------------------------------------------------------------//
   // Loop version of Ackerman                                               //
   //------------------------------------------------------------------------//
   private int ackLoop(int x, int y) {
      ArrayList<int[]> table = new ArrayList<>();
      boolean xStored = true;
      
      do {
         ++calls[2];
         
         // Base case of x = 0
         if (x == 0) {
            ++y; // Increment y
            
            // Check if there are any stored x's to process
            if (table.size() > 0) {
               xStored = true;
               
               // Check if there are duplicates
               if (table.get(table.size() - 1)[1] > 1) {
                  x = table.get(table.size() - 1)[0]; // Get stored x
                  --table.get(table.size() - 1)[1];   // Decr. dupe counter
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
         else if (y == 0) {
            --x;   // Decrement x
            y = 1; // Reset y
            
         }
         // Default case
         else {
            // If the x value isn't a duplicate store it
            if (table.size() == 0 || x - 1 != table.get(table.size() - 1)[0])
               table.add(new int[]{x - 1, 1});
            // Otherwise update its counter
            else
               ++table.get(table.size() - 1)[1];
            
            --y; // Decrement y
         }
         
      } while (xStored);
      
      return y;
      
      //return -1; // Stub return
   }
   
   //------------------------------------------------------------------------//
   
}