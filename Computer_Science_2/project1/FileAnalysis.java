/** File analysis program.
   
    This program gets a file from the user, and then counts the lines, digits,
    and letters in the file.
   
    Stephen Bapple
   
    August 2015
   
    CS 2, Fall 2015
   
    Limitations:
       - Does not handle improper file names.
       - Assumes file is in same directory as the program.
       - Throws non-existent file exceptions (fails silently).
       - Ignores special characters.
*/

import java.io.*;
import java.util.Scanner;

public class FileAnalysis {
   // Analysis instance data
   private String fileName  = "";
   private int lines   = 0;
   private int digits  = 0;
   private int letters = 0;
   
   // Input instance data
   private Scanner keyboard = new Scanner(System.in); // Console scanner
   private File inputText;                            // File scanner
   private Scanner inputFile;                         // File object
   
   public static void main(String [] args)throws IOException  {
      // Declare the class object
      FileAnalysis program = new FileAnalysis();
      
      // Get the file name from the user
      program.getFile();
      
      // Count the lines, digits, and characters 
      program.readFile();
      
      // Summarize the data
      program.printReport();
            
   } // End main

   //------------------------------------------------------------------------//
   // Methods section                                                        //
   //------------------------------------------------------------------------//
   public void getFile()throws IOException  {
      // Prompt the user to enter the file name
      System.out.print("Please enter the name of the file you want to read: ");
      fileName = keyboard.nextLine();
      
      // Instantiate a scanner to read from that file
      inputText = new File(fileName);
      inputFile = new Scanner(inputText);
      
      return;
   } 
   
   //------------------------------------------------------------------------//
   public void readFile() {
      // Local variables
      String tempLine = "";
      int i           = 0;
      
      while(inputFile.hasNextLine()) {
         // First get one line of the file
         tempLine = inputFile.nextLine();
         ++lines;
         
         // Then check if each character is a digit or a letter
         for(i = 0; i < tempLine.length(); ++i) {
            if(Character.isDigit(tempLine.charAt(i))) {
               ++digits; 
            }
            else if (Character.isLetter(tempLine.charAt(i))) {
               ++letters;
            }
         
         } // End for
      
      } // End while
      
      return;
   }
   
   //------------------------------------------------------------------------//
   public void printReport() { 
      // Print the data
      System.out.println("The file analyzed was called: " + fileName + "\r\n" 
            + "In this file there were " + lines + " lines, consisting of: " 
            + "\r\n" + digits + " digits, " + "and " + letters + " letters.");
      
      return;
   }
   
   //------------------------------------------------------------------------//
   
} // End class

