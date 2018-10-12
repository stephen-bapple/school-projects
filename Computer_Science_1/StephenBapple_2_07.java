/** This program reads a file with student grades and names, and outputs 
    to a file the name, grade, and an assessment of the student's grade 
    formatted to a table.
    
    Stephen Bapple
    
    Program #7, CS 1050, Section 2.
    jGrasp v2.0.1_01, Sony Vaio laptop, Windows 7.
    
    Outlier = something situated far from the center, remote. 
    Most commonly used for data.
    
    "Always do your best. What you plant now, you will harvest later."
    - Augustine Mandino II (1923 - 1996)
*/
import java.util.Scanner;
import java.io.*;

public class StephenBapple_2_07 {
   static Toolkit tools = new Toolkit();
   
   public static void main (String [] args) throws IOException {
   
      // Files to read from and write to
      final String INPUT_FILE  = "C:\\javadev\\StephenBapple_2_07_Input.txt\\";
      final String OUTPUT_FILE = "C:\\javadev\\StephenBapple_2_07_Output.txt\\";
      
      // Declarations - ints
      int oneGrade   = 0;       // Current grade being read
      int linesRead  = 0;       // Number of lines processed 
      int outliers   = 0;       // Number of outlying grades
      int sumGrades  = 0;       // Sum of all non-outliers - used for average
      
      // Declarations - Strings
      String studentName = "";  // Name of student	
      String gradeMessage = ""; // Assesment of student's grade
      String output = "";       // String to write output
      
      // Access the input/output files
      File inputDataFile        = new File(INPUT_FILE);
      Scanner inputFile         = new Scanner(inputDataFile);
      FileWriter outputDataFile = new FileWriter(OUTPUT_FILE);
      PrintWriter outputFile    = new PrintWriter(outputDataFile);
      System.out.println("Reading  file " + INPUT_FILE + "\r\n" +
                         "Creating file " + OUTPUT_FILE);
      
      // Print the table header
      printHeader(outputFile);
      
      // Read the file and count how many students recieved outlier grades
      while (inputFile.hasNext()) {
         ++linesRead;                    // Count the lines read
         
         oneGrade = inputFile.nextInt(); // Get the next grade 
         
         if (oneGrade >= 70 && oneGrade < 90) {
            sumGrades += oneGrade; // Add grade to sum if not an outlier
         }
         else {
            ++ outliers;           // Otherwise increment outliers
         } // End if else
         
         studentName = inputFile.nextLine(); // Get the student's name
         studentName = studentName.trim();   // Remove surrounding whitespace
         
         // Write and format data
         output = tools.padString(studentName, 18, "", " "); 
         System.out.print(output); // Print the student's formatted name
         outputFile.print(output); // Echo on console
      
         output = tools.leftPad((double)oneGrade, 5, "#00", " ");
         System.out.print("|" + output); // Print the formatted grade
         outputFile.print("|" + output); // Echo on console
      
         output = tools.padString(getMessage(oneGrade), 13, "", " ");
         System.out.println("  | " + output); // Print the formatted message
         outputFile.println("  | " + output); // Echo on console     
      
      } // End while loop
      
      // Print the table footer
      printFooter(outputFile);
      
      // Print summary information
      printSummary(linesRead, outliers, sumGrades, outputFile);
      
      // Close all the files
      inputFile.close();
      outputFile.close();
		System.exit(0);
   } // End main

   //--------------------------------------------------------------------------
   // Methods section
   //--------------------------------------------------------------------------
   public static String getMessage(int grade) {
      // Local declarations
      String message = "";
      
      // Find the range the grade is in
      if (grade >= 0 && grade < 70) {
         message = "FAILING.";
      }
      else if (grade < 90) {
         message = "SATISFACTORY.";
      }
      else if (grade >= 90) {
         message = "OUTSTANDING.";
      }
      else {
         message = "ERROR...";
      } // End if / else if / else
      
      return(message);
   }
   
   //--------------------------------------------------------------------------
   public static double getAverage(int sumGrades, int linesRead, int outliers) {
      // Local declarations
      double average = 0.0;
      
      average = (double)sumGrades / (double)(linesRead - outliers); 
      
      return(average);
   
   }
   
   //--------------------------------------------------------------------------
   public static void printSummary(int linesRead, int outliers, int sumGrades,
         PrintWriter outputFile) {
      String output = ""; // Output string
      
      output = "There were " + linesRead + " students in the class." + "\r\n"
            + (linesRead - outliers) + " students scored between 69 and 90,"
            + "\r\n" + "and the average grade for these " 
            + (linesRead - outliers) + " students was " 
            + tools.leftPad(getAverage(sumGrades, linesRead, outliers),
                  3, "00.0", "");
      outputFile.println(output); // Print the summary
      System.out.println(output); // Echo on console
      
      return;
   }
   
   //--------------------------------------------------------------------------
   public static void printHeader(PrintWriter outputFile) {
      String output = ""; // Output string
      
      output = "Student Name      | Grade | Assessment   " + "\r\n" 
            + "__________________|_______|______________";
      outputFile.println(output); // Print the header
      System.out.println(output); // Echo on console
      
      return;
   }
   //--------------------------------------------------------------------------
   public static void printFooter(PrintWriter outputFile) {
      String output = ""; // Output string
      
      output = "__________________|_______|______________";
      outputFile.println(output); // Print the footer
      System.out.println(output); // Echo on console
      
      return;
   }
   
   //--------------------------------------------------------------------------

} // End class