/** Randomness testing program
    
    This program generates 50 random numbers in a preselected range and prints
    them to a file. It then generates 1000 more numbers and prints the number
    of occurrences of each number in the range.
    
    Some code has been modified from the sample code given by Dr. Gurka.
    Specifically, the call to Math.random was copied exactly and the general
    outline of the first test for 50 numbers was modified for this program.
    
    September 2015
    
    CS 2, Fall 2015
    
    Limitations: 
      - Doesn't take user input; only uses a preselected range of numbers 
*/

import java.util.Scanner;
import java.io.*;

public class TestRandomness {
   
   public static void main(String [] args)throws IOException {
      final int MAX = 18;        // Maximum value in range
      final int MIN = 14;        // Minimum value in range
      
      int printAmount;           // Amount of numbers to print
      int number;                // One random number
      int actualCount;           // Actual amount of numbers printed
      int[] counts = new int[5]; // Counters for each number in the range
      
      // Initialize the PrintWriter
      PrintWriter outputFile = new PrintWriter(new FileWriter("testData.txt"));
      
      // Output the header
      outputFile.println("Data for testing randomness in a lottery program" 
            + "\r\n" + "Stephen Bapple" + "\r\n" + "\r\n" 
            + "Fifty sample values in the range of " 
            + MIN + " to " + MAX + ":" + "\r\n");
            
      // Output 50 random numbers in the range
      printAmount = 50;
      
      for(int i = 0; i < printAmount; ++i) {
         outputFile.println((int)(Math.random() * (MAX - MIN + 1) + MIN));
      }
      // Count the occurrences of the five different values in 1000 numbers 
      printAmount = 1000;
      
      for(int i = 0; i < printAmount; ++i) {
         number = ((int)(Math.random() * (MAX - MIN + 1) + MIN));
         
         if (number == MIN)
            ++counts[0];
         else if (number == MIN + 1)
            ++counts[1];
         else if (number == MIN + 2)
            ++counts[2];
         else if (number == MIN + 3)
            ++counts[3];
         else if (number == MAX)
            ++counts[4];
         else
            outputFile.println("Something went very wrong");
      }
      // Check if program counter correctly
      actualCount = counts[0] + counts[1] + counts[2] + counts[3] + counts[4];
      
      // Summarize the data
      outputFile.println("\r\n" + "Out of " + printAmount + 
            " numbers generated between " + MIN + " and " + MAX 
            + ", there were: "
            + "\r\n" + counts[0] + " occurrences of " + MIN 
            + "\r\n" + counts[1] + " occurrences of " + (MIN + 1) 
            + "\r\n" + counts[2] + " occurrences of " + (MIN + 2)
            + "\r\n" + counts[3] + " occurrences of " + (MIN + 3)
            + "\r\n" + counts[4] + " occurrences of " + MAX
            + "\r\n" + "\r\n" 
            + "Note: the program generated " + actualCount + " numbers.");
            
      outputFile.close();
   }
}
