/** This program first reads from a file with employee names, wages, and hours.
    Then it calculates employee payment and taxes. Lastly the program prints 
    a table with columns for employee names and corresponding payment data, 
    summarizes the information, and notes the number of people employeed.
    
    Stephen Bapple
    
    Program #10, CS 1050, Section 2.
    jGrasp v2.0.1_01, Sony Vaio laptop, Windows 7.
    
    Tenacious: not easily stopped; determined.
    
    "Failure will never overtake me if my determination to succeed is strong
    enough."
    - Augustine Mandino II (1923 - 1996)
    
*/

import java.util.Scanner;
import java.io.*;

public class StephenBapple_2_10 {
   // Static declarations
   static Toolkit tools = new Toolkit();    // Toolkit class
   final static double FEDERAL_TAX = 0.18;  // Federal tax
   final static double STATE_TAX   = 0.045; // State tax
   final static int MAX_EMPLOYEES  = 30;    // Maximum employees in the company
   
   public static void main(String [] args)throws IOException {
      // Files to read from and write to
      final String INPUT_FILE  = "C:\\201530\\CS1\\StephenBapple_2_10_Input.txt\\";
      final String OUTPUT_FILE = "C:\\201530\\CS1\\StephenBapple_2_10_Output.txt\\";
      
      // Access the input/output files
      File inputDataFile        = new File(INPUT_FILE);
      Scanner inputFile         = new Scanner(inputDataFile);
      FileWriter outputDataFile = new FileWriter(OUTPUT_FILE);
      PrintWriter outputFile    = new PrintWriter(outputDataFile);
      
      // Declarations
      double[][] payments = new double[MAX_EMPLOYEES][6]; // Payment data
      String[] names = new String[MAX_EMPLOYEES];         // Employee names
      int numEmployees = 0;                               // People employed
      
      // Read the file
      numEmployees = readData(inputFile, payments, names, outputFile);
      
      // Calculate values
      getGrossPay(payments, numEmployees);               // Get gross pay
      getWithholding(payments, numEmployees, "Federal"); // Get federal tax
      getWithholding(payments, numEmployees, "State");   // Get state tax
      getNetPay(payments, numEmployees);                 // Get net pay
      
      // Print the report
      outputFile.println("Employees not sorted:");
      printReport(payments, names, numEmployees, outputFile);
      
      // Print the aphabetized report
      tools.selectionSortStringWithNumbers(names, payments, numEmployees, -1);
      outputFile.println("Employees sorted alphabetically:");
      printReport(payments, names, numEmployees, outputFile);
      
      // Print the ascending payrate report 
      tools.selectionSortStringWithNumbers(names, payments, numEmployees, 2);
      outputFile.println("Employees sorted by gross pay, lowest to highest:");
      printReport(payments, names, numEmployees, outputFile);
      
      // Close all the files
      inputFile.close();
      outputFile.close();
      System.exit(0);
      
   } // End main
   
   //------------------------------------------------------------------------//
   // Methods section                                                        //
   //------------------------------------------------------------------------//
   public static void printReport(double[][] payments, 
         String[] names, int numEmployees, PrintWriter outputFile) {
      
      // Print header
      printHeader(outputFile);
      
      // Print detail lines
      printData(payments, names, numEmployees, outputFile);
      
      // Print Summary
      printSummary(payments, names, numEmployees, outputFile);
      
      return;
   }
   
   //------------------------------------------------------------------------//
   public static void printHeader(PrintWriter outputFile) {
      // Print the header
      outputFile.println( 
            "                                  Fabulous Furniture Company"
            + "                                   "
            + "\r\n"
            + "________________________________________Payroll Report"
            + "_________________________________________"       
            + "\r\n"
            + "Employee name     | Net payment | Gross pay |"
            + " Federal tax | State tax | Hours worked | Pay rate"
            + "\r\n"
            + "__________________|_____________|___________|"
            + "_____________|___________|______________|_________");
      
      return;
   }
   
   //------------------------------------------------------------------------//
   public static int readData(Scanner inputFile, double[][] payments, 
         String[] names, PrintWriter outputFile) {
      String dataLine   = ""; // String to hold the data for an employee
      String[] elements;      // Elements in the line of data
      int numEmployees  = 0;  // Actual number of employees
      int i             = 0;  // Index for while loop
      while(inputFile.hasNext()) {
         try { 
            ++numEmployees;
            
            dataLine = inputFile.nextLine();
            dataLine = dataLine.trim();
            elements = dataLine.split(" +");
               
            // Parse out the hours worked
            payments[i][0] = 
                  Double.parseDouble(elements[elements.length - 2]);
            // Parse out the hourly wage
            payments[i][1] = 
                  Double.parseDouble(elements[elements.length - 1]);
               
            // Recombine the first two names
            names[i] = elements[0] + " " + elements[1]; 
            // If the employee an initial / three names, add the third name
            if(elements.length > 4) {
               names[i] = names[i] + " " + elements[2]; 
            } // End if statement
            
            ++i; // Increment counter
         } // End try
         catch(ArrayIndexOutOfBoundsException e) {
            // Print error message to file
            outputFile.println("Warning: the file has more than " 
               + MAX_EMPLOYEES + " employees listed."
               + "\r\n"
               + "Fabulous Furniture Company employs a maximum of " 
               + MAX_EMPLOYEES + " employees."
               + "\r\n"
               + "Please check the file. Program exiting.");
            
            // Close program
            inputFile.close();
            outputFile.close();
            System.exit(0);
         } // End try / catch
            
      } // End while loop      
      
      return(numEmployees);
   }
   
   //------------------------------------------------------------------------//   
   public static void getWithholding(double[][] payments, 
         int numEmployees, String fedOrState){
      double withholding = 0.0; // Withholding percentage 
      int whichTax       = 0;   // Which element the withholding is stored in
      
      switch (fedOrState) {
         case "Federal": withholding = FEDERAL_TAX;
            whichTax = 4;
            break;
         case "State": withholding   = STATE_TAX;
            whichTax = 5;
            break;
      } // End switch
      
      for(int i = 0; i < numEmployees; ++i) {
         payments[i][whichTax] = 
               tools.roundNumber((payments[i][2] * withholding), 2); 
      } // End for loop
         
      return;
   }
   
   //------------------------------------------------------------------------//
   public static void getGrossPay(double[][] payments, 
         int numEmployees) {
      
      for (int i = 0; i < numEmployees; ++i) {
         // Payment calculations without overtime
         if(payments[i][0] <= 40.0) {
            payments[i][2] = payments[i][1] * payments[i][0];
         }
         // Payment calculations with overtime
         else if (payments[i][0] <= 50.0) {
            payments[i][2] = payments[i][1] 
                  * (40.0
                  + ((payments[i][0] - 40.0) * 1.5));
         }
         // Payment calculations with overtime and doubletime
         else {
            payments[i][2] = payments[i][1] 
                  * (40.0 
                  + ((payments[i][0] - 50.0) * 2.0)
                  + ((payments[i][0] - (payments[i][0] - 50.0) - 40.0) * 1.5));
         } // End if / else if / else payment table
        
      } // End for loop
      
      return;
   }
   //------------------------------------------------------------------------//
   public static void getNetPay(double[][] payments, 
         int numEmployees) {
         
      for(int i = 0; i < numEmployees; ++i) {
         // Subtract the federal and state taxes from gross pay
         payments[i][3] = (payments[i][2] - payments[i][4] 
               - payments[i][5]);
      } // End for loop   
      
      return;  
   }
   
   //------------------------------------------------------------------------//
   public static void printData(double[][] payments, String[] names,
         int numEmployees, PrintWriter outputFile) {
      String output = ""; // String for writing output
      
      // Round values and seperate into columns
      for(int i = 0; i < numEmployees; ++i) {
         outputFile.println(tools.padString(names[i], 17, "", " ") + " | " 
               + tools.leftPad(payments[i][3], 11, "0.00", " ") + " | "
               + tools.leftPad(payments[i][2], 9, "0.00", " ") + " | " 
               + tools.leftPad(payments[i][4], 11, "0.00", " ") + " | "
               + tools.leftPad(payments[i][5], 9, "0.00", " ") + " | " 
               + tools.leftPad(payments[i][0], 12, "0.00", " ") + " | "
               + tools.leftPad(payments[i][1], 8, "0.00", " "));
      } // End for loop
      
      return;
   }
   
   //------------------------------------------------------------------------//
   public static void printSummary(double[][] payments, 
         String[] names, int numRows, PrintWriter outputFile) {
      String output = ""; // String for writing output
      
      // Print the summary and footer
      outputFile.println("------------------|-------------|-----------|"
         + "-------------|-----------|--------------|---------" 
         + "\r\n"
         + "Payment totals    | Net payment | Gross pay |"
         + " Federal tax | State tax | Hours worked | Pay rate"
         + "\r\n"
         + "and average wage  | "
         // Organize totals into columns
         + tools.leftPad(sumArray(payments, numRows, 3), 11, "0.00", " ")
         + " | "
         + tools.leftPad(sumArray(payments, numRows, 2), 9, "0.00", " ")
         + " | "
         + tools.leftPad(sumArray(payments, numRows, 4), 11, "0.00", " ")
         + " | "
         + tools.leftPad(sumArray(payments, numRows, 5), 9, "0.00", " ")
         + " | "
         + tools.leftPad(sumArray(payments, numRows, 0), 12, "0.00", " ")
         + " | "
         + tools.leftPad(averageArray(payments, numRows, 1), 8, "0.00", " ")
         + "\r\n"
         // Define footer
         + "__________________|_____________|___________|"
         + "_____________|___________|______________|_________"
         + "\r\n"
         // Note the actual and maximum employees
         + "Note: these payments are for " + numRows + " employees, out of a "
         + "possible " + MAX_EMPLOYEES + " employees."
         + "\r\n");
      
      return;
   }
   
   //------------------------------------------------------------------------//
   public static double sumArray(double[][] array, int numRows, 
         int columnToSum) {
      double sum = 0.0; // Total of all values
      
      // Sum the array
      for (int i = 0; i < numRows; ++i) {
         sum += array[i][columnToSum];
      }
      
      return(sum);
   }
   
   //------------------------------------------------------------------------//
   public static double averageArray(double[][] array, int numRows, 
         int columnToSum) {
      double average = 0.0; // Average of all the rows in the column
      
      // Use sumArray to get the sum and divide it by numRows for the average
      average = sumArray(array, numRows, 1) / numRows;
      
      return(average);
   
   }
   
   //------------------------------------------------------------------------//

} // End class