/** Mileage reimbursement program.
    
    This program reads a file with a list of miles traveled and outputs the
    reimbursement for each entry in a new file.
    
    Stephen Bapple
    
    Program #6, CS 1050, Section 2.
    jGrasp v2.0.1_01, Sony Vaio laptop, Windows 7.
    
    Forbearance: Patience; the willingness to wait.
    
    "I think and think for months and years. Ninety-nine times, the conclusion
    is false. The hundredth time I am right."
    - Albert Einstein (1879 - 1955)
*/

import java.util.Scanner;
import java.io.*;

public class StephenBapple_2_06 {
   static Toolkit tools = new Toolkit();
   static int actualNumbers         = 0;
   static int zeroOrLessNums        = 0;
   static double totalReimbursement = 0.0;
   
   public static void main (String [] args) throws IOException {
      
      // Declarations
      int expectedNumbers  = 0; // First number in file indicates how many to read
      
      // Files to read from and write to
		final String INPUT_FILE  = "StephenBapple_2_06_Input.txt\\";
		final String OUTPUT_FILE = "StephenBapple_2_06_Output.txt\\";
      
      // Access the input/output files
		File inputDataFile        = new File (INPUT_FILE);
		Scanner inputFile         = new Scanner (inputDataFile);
		FileWriter outputDataFile = new FileWriter(OUTPUT_FILE);
		PrintWriter outputFile    = new PrintWriter(outputDataFile);
      System.out.println("Reading  file " + INPUT_FILE + "\r\n" +
		                   "Creating file " + OUTPUT_FILE);

      // Print the header for the table of values
      printHeader(outputFile);
      
      // Read the file and get the actual number of entries 
      expectedNumbers = readData(inputFile, outputFile);
      
      // End the table and 
      // inform the user if the number of entries is different than expected
      printSummary(expectedNumbers, actualNumbers, outputFile);
      
      // Close all the files
      inputFile.close();
      outputFile.close();
		System.exit(0);      
   } // End main
   
   //------------------------------------------------------------------------//
   //Methods section
   //------------------------------------------------------------------------//
   public static int readData (Scanner inputFile, PrintWriter outputFile) {
      
      // Local declarations
      double currentNum   = 0.0; // Current number being read
      int expectedNumbers = 0;   // Expected entries in the file
      
      expectedNumbers = inputFile.nextInt();
         
      while (inputFile.hasNext()) {
         actualNumbers++;                     // Counts the numbers in the file
         currentNum = inputFile.nextDouble(); // Read the next number
         
         // Write mileage to ouput file
         outputFile.print(tools.leftPad(currentNum, 12, "0.0", " "));      
         outputFile.print(" " + "|" + " "); // Separator
         
         // Echo on console
         System.out.print(tools.leftPad(currentNum, 12, "0.0", " "));
         System.out.print(" " + "|" + " "); // Separator
            
         totalReimbursement += calcReimbursement(currentNum, outputFile);
         
      } // End while loop

      return(expectedNumbers);
   }
   
   //------------------------------------------------------------------------//
   public static double calcReimbursement(double miles, PrintWriter outputFile) {
      
      // Local declarations
      double reimbursement = 0.0;
      String output = "";
      
      if (miles <= 0) {
         zeroOrLessNums++;
         output = "             *****";
      }
      else if (miles < 400) {
         reimbursement = 0.18 * miles;
      }
      else if (miles < 900) {
         reimbursement = 65.0 + 0.15*(miles - 400.0);
      }
      else if (miles < 1300) {
         reimbursement = 115.0 + (0.12 * (miles - 900.0));
      }
      else if (miles < 1900) {
         reimbursement = 140.0 + (0.10 * (miles - 1300.0));
      }
      else if (miles < 2600) {
         reimbursement = 165.0 + (0.08 * (miles - 1900.0));
      }
      else {
         reimbursement = 195.00 + (0.06 * (miles - 2600.0));
      } // End if/else if/else
      
      if (reimbursement != 0.0) {
         output = tools.leftPad(reimbursement, 18, "#,##0.00", " ");
      } // End if/no else
      
      outputFile.println(output); // Write the reimbursment to the file
      System.out.println(output); // Echo on the console
      
      return(reimbursement);
   }
   
   //------------------------------------------------------------------------//
   public static void printHeader(PrintWriter outputFile) {
      String output = "Miles Driven | Reimbursement Paid "
            + "\r\n_____________|___________________";
      
      System.out.println(output); // Print table header
      outputFile.println(output); // Echo on console
      return;
   }
   
   //------------------------------------------------------------------------//
   public static void printSummary(int expectedNums, int actualNums, 
         PrintWriter outputFile) {
      // Local declarations
      String output = "";
      
      // Print the table footer
      output = "_____________|___________________";
      outputFile.println(output); // Print to the file 
      System.out.println(output); // Echo on console
      
      // Print total reimbursement, numbers read, and how many were positive
      output = "The total reimbursement to be paid is " 
            + totalReimbursement + "\r\n" 
            + "which is the sum of the reimbursements of the " 
            + actualNumbers + " values processed, of which " 
            + (actualNumbers - zeroOrLessNums) + " were above zero.";
      outputFile.println(output); // Write summary to file
      System.out.println(output); // Echo on console
      
      // Note if the actual numbers read is different than expected
      if (expectedNums != actualNums) {
      output = "\r\n" + "Note: " + "\r\n" 
            + "The file indicated there were " + expectedNums 
            + " numbers to be read,\r\nhowever there were " 
            + actualNums + " numbers in the file.";
      outputFile.println(output); // Write unexpected number note to file
      System.out.println(output); // Echo on console
      }
      
      return;
   }
   
   //------------------------------------------------------------------------//
   
} // End class