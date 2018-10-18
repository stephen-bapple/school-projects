/** This class contains all the methods and data for the big addition program
    The big addition program adds two numbers of arbitrary length, bypassing 
    size limits such as int and even long. 
    
    Stephen Bapple
    
    CS 2, Fall 2015
    
    Current Limitations: 
      - Only gets files in the same directory
      - Does not check if a file is valid, will crash given an invalid filename.
*/

import java.util.Scanner;
import java.io.*;

public class BigAddition {
   // Instance data
      // The carry digit
      // Two strings to hold the numbers
      // String holding the author and programmer
      // String holding test case description
      // String holding expected output
      // String to hold calculated output
      // maybe an array of Strings to make it easier?
   
   // Class declarations
   private JStack stack1;          // Stack for one number
   private JStack stack2;          // Stack for the other number
   private JStack result;          // Stack for the result
   private Scanner inputFile;      // Reads file
   private PrintWriter outputFile; // Writes to file
   
   /* Strings read from file
      Maybe put these in an array */
   private String number1;
   private String number2;
   private String authors;
   private String testDescription;
   private String expectedResult;
   
   //------------------------------------------------------------------------//
   /* Process problems Method
         * Get filenames
         * Read test case
         * Print header
         * Read the two numbers (place in strings)
         * Push the two numbers onto their stacks
         * Add them into the third stack
         * Reverse the stack
         * Print the results
         * Close the files
   */
   //------------------------------------------------------------------------//
   public void processProblems()throws IOException {
      // Get the file names from the user
      getFileNames();
      
      // Get the authors from the file
      authors = inputFile.nextLine();
      
      // Print the header
      printHeader();
      
      // Process every problem
      while(inputFile.hasNext()) {
         readProblem();                // Read a problem
         stack1 = pushNumber(number1); // Put the first number in a stack
         stack2 = pushNumber(number2); // Put the second number in a stack
         
         /* Tests to see if emptyStack is working
            which means pushNumber is also working. Tests Passed
         outputFile.println(emptyStack(stack1));
         outputFile.println(emptyStack(stack2)); */
         
         /* Test code: test two strings to see if pass / failure output works
            Tests passed.
         actualResult   = "This test should pass";
         expectedResult = "This test should pass";
         outputFile.println(testResult());
         
         actualResult   = "This is another test";
         expectedResult = "This one should fail";
         outputFile.println(testResult()); */         

         addDigits();    // Perform addition
         
         /* Testing addDigits: Test passed
         outputFile.println(emptyStack(stack1));
         outputFile.println(emptyStack(stack2));
         outputFile.println(emptyStack(result)); */
         
         /* Testing addCommas initial: Passed
         outputFile.println(addCommas("1000"));
         outputFile.println(addCommas("10000"));
         outputFile.println(addCommas("100000")); */
         
         writeProblem(); // Write the result
      }
      
      // Close the files
      inputFile.close();
      outputFile.close();
      
      return;
   }
   
   //------------------------------------------------------------------------//
   // Method to get input and output files
   //------------------------------------------------------------------------//
   public void getFileNames()throws IOException {
      Scanner keyboard = new Scanner(System.in);
      
      // Get the input file - Fix to actually take input when AOK
      System.out.print("Please enter the name of the file to read from: ");
      inputFile = new Scanner(new File(keyboard.nextLine())); 
      //inputFile = new Scanner(new File(InitialTest.txt"));

      // Get the output file - Fix to actually take input when AOK
      System.out.print("Please enter the name of the file to write to: ");
      outputFile = new PrintWriter(new File(keyboard.nextLine()));
      //outputFile = new PrintWriter(new File(OutputTest.txt"));
      
      /* Test code: copy input to output: tests passed
      while(inputFile.hasNext()) {
         outputFile.println(inputFile.nextLine());
      }
      inputFile.close();
      outputFile.close(); 
      */
      
      return;
   }
   
   //------------------------------------------------------------------------//
   // Method to read one problem                                             //
   //------------------------------------------------------------------------//
   public void readProblem() {
      // Description
      testDescription = inputFile.nextLine();
      
      // First number
      number1         = inputFile.nextLine();
      number1 = number1.replace(",", "");
      
      // Second number
      number2         = inputFile.nextLine();
      number2 = number2.replace(",", "");
      
      // Expected output
      expectedResult  = inputFile.nextLine();
      expectedResult = expectedResult.replace(",", "");
      
      return;
   }
   //------------------------------------------------------------------------//
   // Method to print the header                                             //
   //------------------------------------------------------------------------//
   public void printHeader() {
      outputFile.println("Big Addition Program." + "\r\n" + "\r\n" 
            + "This program performs addition on numbers, but isn't limited by"
            + " int or long's size constraints." + "\r\n" + "\r\n"
            + "Authors: " + authors + "\r\n"
            + "CS 2, Fall 2015" + "\r\n");
            
      
      return;
   }
   
   //------------------------------------------------------------------------//
   // Method to write one problem
   //------------------------------------------------------------------------//
   public void writeProblem() {
      String actualResult = emptyStack(result);
      
      outputFile.println("Test case description: " + testDescription + "\r\n"
            + "Number 1: " + addCommas(number1) + "\r\n" 
            + "Number 2: " + addCommas(number2) + "\r\n" 
            + "Answer: " + addCommas(actualResult) + "\r\n" 
            + "Expected answer: " + addCommas(expectedResult) + "\r\n" 
            + testResult(actualResult) + "\r\n");
      
      return;
   }
   
   //------------------------------------------------------------------------//
   // Method to test if answer is as expected
   //------------------------------------------------------------------------//
   public String testResult (String actualResult) {
      if(actualResult.equals(expectedResult))
         return("Test passed.");
      else
         return("Test FAILED"); // Caps because it's super important
   }
   
   //------------------------------------------------------------------------//
   // Method to add commas and format
   //------------------------------------------------------------------------//
   public String addCommas(String number) {
      // Local declarations
      String withCommas;
    
      // Pad string with whitespace to get the commas in the correct places
      while(number.length() % 3 != 0) {
         number = " " + number;
      }
      toString();
      /* Initialize the string to the first 3 characters 
         some could be whitespace if the string doesn't split into 3 evenly */
      withCommas = number.substring(0,3);
      
      // Add commas every 3 digits
      for(int i = 3; i < number.length(); i += 3) {
         withCommas = withCommas + "," + number.substring(i, i + 3);
      }
      
      // Remove the formatting whitespace
      withCommas = withCommas.trim();      
      
      return(withCommas);
   }
   
   //------------------------------------------------------------------------//
   // Method to add two digits together
   //------------------------------------------------------------------------//
   public void addDigits() {
      // Local declarations
      int tempNumber; // One digit
      int carry = 0;  // Carry bit will zero at start
      
      /* Instantiate the result stack.
         The stack is set to the larger stack's size, or if they are equal
         stack1's size (since it won't matter) plus one more element
         in case a carry results in a larger number. */
      if(stack1.getSize() >= stack2.getSize())
         result = new JStack(stack1.getSize() + 1);
      else
         result = new JStack(stack2.getSize() + 1);
      
      // While at least one stack, or the carry, are not empty, continue
      while(!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            tempNumber = 0; // Clear the digit holder
            
            // Add the numbers from the non empty stacks
            if(!stack1.isEmpty())
               tempNumber = tempNumber + (int)stack1.pop();
            if(!stack2.isEmpty())
               tempNumber = tempNumber + (int)stack2.pop();
            
            // Add the carry
            tempNumber += carry;
            
            // Set the carry and reduce the number below 10
            carry = tempNumber / 10;
            tempNumber = tempNumber % 10;
            
            // Push the sum onto the result stack
            result.push(tempNumber);      
      }
      
      // Reverse the stack for easy reading
      result = reverseStack(result);
      
      return;
   }
   
   //------------------------------------------------------------------------//
   // Method to push a number onto a specified stack
   //------------------------------------------------------------------------//
   public JStack pushNumber(String number) {
      JStack stack = new JStack(number.length());
      for(int i = 0; i < number.length(); ++i) {
         stack.push(number.charAt(i) - '0');
      }
      
      return(stack);
   }
   
   //------------------------------------------------------------------------//
   // Method to reverse the stack: returns the pointer of the reversed stack //
   //------------------------------------------------------------------------//
   public JStack reverseStack(JStack stack) {
      // Declare and instantiate a new stack
      JStack tempStack = new JStack(stack.getSize());
      
      // Push every entry onto the new stack
      while(!stack.isEmpty()) {
         tempStack.push(stack.pop());
      }
      
      // Return the pointer for the new stack
      return(tempStack);
   }
   
   //------------------------------------------------------------------------//
   // Method to empty a stack into a string
   //------------------------------------------------------------------------//
   public String emptyStack(JStack stack) {
      String tempString;
      
      tempString = stack.pop() + "";
      
      while(!stack.isEmpty()) {
         tempString = stack.pop() + tempString;
      }
      
      return(tempString);
   }
   
   //------------------------------------------------------------------------//
   // toString
   //------------------------------------------------------------------------//
   public String toString() {
      System.out.println("The first stack is: " + emptyStack(stack1) + "\r\n"
            + "The second stack is: " + emptyStack(stack2) + "\r\n"
            + "The result stack is: " + emptyStack(result));
      return("Should toString be void?");
      }
}
  
