/** Mileage reimbursement program with arrays
    
    This program reads a file with a list of miles traveled outputs the
    reimbursement for each entry in a new file, and outputs the sum and 
    averages of the miles and reimbursment. This program uses arrays for 
    the data.
    
    Stephen Bapple
    
    Program #8, CS 1050, Section 2.
    jGrasp v2.0.1_01, Sony Vaio laptop, Windows 7.
    
    Arcane: something that is obscure and known to few people
    
    "Real difficulties can be overcome; 
    it is only the imaginary ones that are unconquerable."
       - Theodore Newton Vail (1845 - 1920)
*/

import java.util.Scanner;
import java.io.*;

public class StephenBapple_2_08 {
   static Toolkit tools = new Toolkit();
   
   public static void main (String [] args) throws IOException {
      
      // Declarations
      double miles[];         // Array for the mileage values
      double reimbursement[]; // Array for the payment values
      
      // Files to read from and write to
		final String INPUT_FILE  = "C:\\javadev\\StephenBapple_2_08_Input.txt\\";
		final String OUTPUT_FILE = "C:\\javadev\\StephenBapple_2_08_Output.txt\\";
      
      // Access the input/output files
		File inputDataFile        = new File(INPUT_FILE);
		Scanner inputFile         = new Scanner(inputDataFile);
		FileWriter outputDataFile = new FileWriter(OUTPUT_FILE);
		PrintWriter outputFile    = new PrintWriter(outputDataFile);
      System.out.println("Reading  file " + INPUT_FILE + "\r\n" +
		                   "Creating file " + OUTPUT_FILE);

      // Explain the program
      explainProgram(outputFile);
      
      // Print the header for the table of values
      printHeader(outputFile);
      
      // Fill the arrays
      miles = readData(inputFile, outputFile);
      reimbursement = calcReimbursement(miles, outputFile);
      
      // Write the data from the arrays
      writeData(miles, reimbursement, outputFile);
      
      // End the table and print the summary
      printSummary(miles, reimbursement, outputFile);
      
      // Close all the files
      inputFile.close();
      outputFile.close();
		System.exit(0);
            
   } // End main
   
   //------------------------------------------------------------------------//
   //Methods section
   //------------------------------------------------------------------------//
   public static void explainProgram(PrintWriter outputFile) {
      String output = ""; // Output to write
      
      output = "\r\n" 
            + "This program first reads a file with miles driven as entries "
            + "and calculates the reimbursment for each entry." + "\r\n"
            + "Then it outputs the mileage and reimbursment in a table. " 
            + "\r\n"
            + "And then lastly it outputs the number of entries read, "
            + "how many entries had positive miles logged, " + "\r\n"
            + "and the total and average of the mileage and reimbursment "
            + "for the positive mileage entries." + "\r\n"; 
            
      outputFile.println(output); // Write the data to the file
      System.out.println(output); // Echo on the console
      
      return;
   }
   
   //------------------------------------------------------------------------//
   public static double[] readData(Scanner inputFile, PrintWriter outputFile) {
      
      // Local declarations
      double tempMiles[]; // Local mileage array
      String output = ""; // Output to write
      
      // Give the mileage array the expected number of entries as its length
      tempMiles = new double[inputFile.nextInt()];
      
      // Fill the array with the mileage values
      for (int i = 0; i < tempMiles.length; ++i) {
         tempMiles[i] = inputFile.nextDouble();  // Read the next number
                 
         // Check for end of file and output an error message and close the 
         // file if there is an improper number of entries
         if ((inputFile.hasNext() && i == tempMiles.length - 1) 
               || (!inputFile.hasNext() && i != tempMiles.length - 1)) {
            // Output error message
            output = "Error: the number of entries in the file is different " 
                  + "than the file indicated. Please check the file." 
                  + "\r\n" + "Program exiting...";
            outputFile.println(output); // Write message to file
            System.out.println(output); // Echo on console

            // Close files and program
            inputFile.close();
            outputFile.close();
            System.exit(0);
         } // End if/no else
            
      } // End for loop
      
      return (tempMiles);
   }
   
   //------------------------------------------------------------------------//
   public static void writeData(double[] miles, double[] payment, 
         PrintWriter outputFile) {
      String output = ""; // Output to write
      
      for (int i = 0; i < miles.length; ++i) {
         if (payment[i] == 0.0) {
            output = "             *****";
         }
         else {
            output = tools.leftPad(payment[i], 18, "#,##0.00", " ");
         } // End if/else
         
         output = tools.leftPad(miles[i], 12, "0.0", " ") + " | " + output;
         outputFile.println(output); // Write the data to the file
         System.out.println(output); // Echo on the console
         
      } // End for loop
      
      return;
   }
   
   //------------------------------------------------------------------------//
   public static double sumArray(double[] array) {
      // Local declarations
      double total = 0.0; // Total of the array (payment or mileage)
      
      for (int i = 0; i < array.length; ++i) {
         if (array[i] > 0.0) {
            total += array[i];
         } // End if/no else
         
      } // End for loop
      
      return(total);
   }
   //------------------------------------------------------------------------//
   public static double calcAverage (double[] array) {
      // Number of entries with zero or negative values
      int zeroOrLess = countNonPositive(array);
      
      // If there are entries above zero return the average, else return zero
      if ((array.length - zeroOrLess) > 0) {
         return(sumArray(array) / (array.length - zeroOrLess));
      }
      else{
         return(0.0);
      }
   }
   //------------------------------------------------------------------------//
   public static int countNonPositive(double[] array) {
      // Local declarations
      int zeroOrLessNums = 0; // Entries with zero or negative mileage 
      
      for (int i = 0; i < array.length; ++i) {
         if (array[i] <= 0.0) {
            ++zeroOrLessNums;
         } // End if no else
         
      } // End for loop
      
      return(zeroOrLessNums);
   }
   //------------------------------------------------------------------------//
   public static double[] calcReimbursement(double[] miles, 
         PrintWriter outputFile) {
      // Local declarations
      double reimbursement[] = new double[miles.length]; // Local payment array
      
      // Calculate reimbursment according to a range of values
      for (int i = 0; i < miles.length; ++i) {
         if (miles[i] <= 0) {
            reimbursement[i] = 0.0;
         }
         else if (miles[i] < 400) {
            reimbursement[i] = 0.18 * miles[i];
         }
         else if (miles[i] < 900) {
            reimbursement[i] = 65.0 + 0.15*(miles[i] - 400.0);
         }
         else if (miles[i] < 1300) {
            reimbursement[i] = 115.0 + (0.12 * (miles[i] - 900.0));
         }
         else if (miles[i] < 1900) {
            reimbursement[i] = 140.0 + (0.10 * (miles[i] - 1300.0));
         }
         else if (miles[i] < 2600) {
            reimbursement[i] = 165.0 + (0.08 * (miles[i] - 1900.0));
         }
         else {
            reimbursement[i] = 195.00 + (0.06 * (miles[i] - 2600.0));
         } // End if/else if/else
         
      } // End for loop
         
      return(reimbursement);
   }
   
   //------------------------------------------------------------------------//
   public static void printHeader(PrintWriter outputFile) {
      String output = "Miles Driven | Reimbursement Paid "
            + "\r\n" + "_____________|___________________"; // Output to write
      System.out.println(output); // Print table header
      outputFile.println(output); // Echo on console
      
      return;
   }
   //------------------------------------------------------------------------//
   public static void printSummary(double[] miles, double[] payment,
         PrintWriter outputFile) {
      // Local declarations
      String output = ""; // Output to write
      
      // Print the table footer
      output = "_____________|___________________";
      outputFile.println(output); // Print to the file 
      System.out.println(output); // Echo on console
      
      // Print summary information    
      output = "There were " + miles.length + " entries read, and " 
            + (miles.length - countNonPositive(miles)) 
            + " entries had positive mileage values and were reimbursed."
            + "\r\n" 
            + "There was a total of " + sumArray(miles) + " miles driven and $"
            + sumArray(payment) + " reimbursement paid." 
            + "\r\n" + "The average mileage for the positive entries was " 
            + tools.leftPad(calcAverage(miles), 0, "0.0", " ") + " miles," 
            + "\r\n" 
            + "and the average reimbursment for the positive entries was $"
            + tools.leftPad(calcAverage(payment), 0, "#,##0.00", " ");
      outputFile.println(output); // Write summary to file
      System.out.println(output); // Echo on console
      
      return;
   }
   
   //------------------------------------------------------------------------//
   
} // End class
