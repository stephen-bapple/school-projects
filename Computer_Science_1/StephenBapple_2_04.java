/** Basic Input/Output Program.
    
    Modified from a previous program provided by David Kramer.
    
    This program lets a user type any amount of numbers into a text file,
    and then reads the numbers and their running total line by line into 
    another text file. It then finishes by writing the amount of numbers, 
    their sum, and their average into the file.
    
    Stephen Bapple
    
    Program #4, CS 1050, Section 2.
    jGrasp v2.0.1_01, Sony Vaio laptop, Windows 7.
    
    Sedulous: Ajective: said of a person or action that shows dedication and diligence.
    
    "Being defeated is only a temporary condition; giving up is what makes it permanent."
    - Marilyn vos Savant (b. 1946)
    
*/

import java.util.Scanner;
import java.io.*;
import java.text.DecimalFormat;

public class StephenBapple_2_04 {
   static Toolkit tools = new Toolkit();
   
	public static void main(String[] args) throws IOException {
      
      // Files to read from and write to
		final String INPUT_FILE  = "C:\\javadev\\StephenBapple_2_04_Input.txt\\";
		final String OUTPUT_FILE = "C:\\javadev\\StephenBapple_2_04_Output.txt\\";	
            
      // Declarations
      DecimalFormat round2Places = new DecimalFormat("0.00"); 
      String outputLine          = ""; // String to write
		int numberOfNumbers        =  0; // Number of numbers in the input file
		double sum                 =  0; // The sum of the numbers
      double average             =  0; // The average of the numbers read
		double oneNumber;                // An individual number read from the file	
      
      // Access the input/output files
		File inputDataFile = new File(INPUT_FILE);
		Scanner inputFile  = new Scanner(inputDataFile);
		FileWriter outputDataFile = new FileWriter(OUTPUT_FILE);
		PrintWriter outputFile = new PrintWriter(outputDataFile);
      System.out.println("Reading  file " + INPUT_FILE + "\r\n" +
		                   "Creating file " + OUTPUT_FILE);
		
		// Loop reads the input file and writes to the output file
      // Until there are no numbers left
		while (inputFile.hasNext()) {
				numberOfNumbers++; // Increment number of numbers every loop
			   oneNumber = inputFile.nextDouble(); // Read number from file
				sum = sum + oneNumber;              // Adds number to running total
            
            // Assign number being read and the running total to a string
            outputLine = "The number is " + tools.leftPad(oneNumber, 12, "0.00"," ") 
                  + ", running total is " + tools.leftPad(sum, 14, "0.00", " ");
            outputFile.println(outputLine); // Write string to output file
            System.out.println(outputLine); // Echo string on console

       } // End while
		
      
      // Check for division by zero before finding average
      if (numberOfNumbers == 0) {
         // The most likely reason for division by zero is the file being empty
         outputFile.println("Error: division by zero is undefined."
               + "\r\n" + "File empty."); // Write error message to file         
         System.out.println("Error: division by zero is undefined."
               + "\r\n" + "File empty."); // Echo message on console
      }
      else {
          // The average is the sum divided by how many numbers were summed
          average = (sum / numberOfNumbers);
          // Assign sum, the total numbers, and the average to a string
          outputLine = "There were " + numberOfNumbers + " numbers read" 
                + "\r\n" + "their sum is " + round2Places.format(sum) + "\r\n"
                + "and their average is " + round2Places.format(average);
          outputFile.println(outputLine); // Write string to output file
          System.out.println(outputLine); // Echo string on console
            
      } // End if else
      inputFile.close();
      outputFile.close();
		System.exit(0);
	} // End main
} // End class