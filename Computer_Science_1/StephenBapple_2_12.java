/** This program reads from a file containing dollar and cent amounts, converts
    the amounts to words, and prints them in the style checks are written to
    another file, along with the total values processed, the total valid
	 values, and the largest valid value.
    
    Stephen Bapple
    
    Program #12, CS 1050, Section 2.
    jGrasp v2.0.1_01, Sony Vaio laptop, Windows 7.
    
    Prodigious: remarkable or impressive.
    
    "Perserverence is failing 19 times and succeeding the 20th."
	 -Julie Andrews (b. 1935)

*/
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class StephenBapple_2_12 {
   static Toolkit tools = new Toolkit();
   
   public static void main(String [] args) throws IOException {
      
      // Files to read from and write to
      final String INPUT_FILE  = ".\\StephenBapple_2_12_Input.txt";
      final String OUTPUT_FILE = ".\\StephenBapple_2_12_Output.txt";
      
      // Access the input/output files
		File inputDataFile        = new File(INPUT_FILE);
		Scanner inputFile         = new Scanner(inputDataFile);
		FileWriter outputDataFile = new FileWriter(OUTPUT_FILE);
		PrintWriter outputFile    = new PrintWriter(outputDataFile);
      
      // The arrays
      ArrayList<Double> dollars = new ArrayList<Double>(1);
      String words[];
      
      // Print the header
      printHeader(outputFile);
      
      // Read the data and get the array dimensions
      readData(dollars, inputFile);
      words = new String[dollars.size()];
      getWords(dollars, words);
      
      // Write the detail lines
      writeData(dollars, words, outputFile);
      
      // Print the summary data
      printSummary(dollars, outputFile);
      
      // Close all the files
      inputFile.close();
      outputFile.close();
		System.exit(0);
   
   } // End main
   
   //------------------------------------------------------------------------//
   // Methods section                                                        //
   //------------------------------------------------------------------------//
   public static void printHeader(PrintWriter outputFile) {
      String output = "Data      | In Words" 
            + "\r\n" 
            + "__________|__________________________"
            + "____________________________________";
      
      outputFile.println(output); // Write to file
      System.out.println(output); // Echo on console
      
      return;
   }
   //------------------------------------------------------------------------//
   public static void printSummary(ArrayList<Double> dollars, 
         PrintWriter outputFile) {
      String output = "__________|______________________"
		      + "________________________________________" 
				+ "\r\n"
            + "There were " + dollars.size() + " dollar values read, "
            + "\r\n" 
				+ countValid(dollars) + " values were in the range from 0.01 to "
            + "9999.99," 
				+ "\r\n" 
				+ "and the largest valid dollar amount read was " 
            + findLargest(dollars); 
            
      outputFile.println(output); // Write to file
      System.out.println(output); // Echo on console
   }
   //------------------------------------------------------------------------//
   public static void readData(ArrayList<Double> dollars, 
         Scanner inputFile) {
      
      while(inputFile.hasNextDouble()) {
         dollars.add(inputFile.nextDouble());
      } // End while loop

      return;
   }
   
   //------------------------------------------------------------------------//
   public static void getWords(ArrayList<Double> dollars, String[] words) {
      
      for (int i = 0; i < words.length; ++i) {
         words[i] = parseDollars(dollars.get(i));
      } // End for loop
      
      return;
      
   }
   
   //------------------------------------------------------------------------//
   public static String parseDollars(double number) {
      // Word equivalents of the type double dollar amount
      String numInWords  = "*** Number out of range"; // All the phrases
      String thousands   = "";                        // Thousands phrase 
      String hundreds    = "";                        // hundreds phrase
      String tensAndOnes = "";                        // Tens and ones phrases
      
      // Equivalent phrase arrays
      String[] unitPhrases = {"", "One", "Two", "Three", "Four", "Five", "Six",
            "Seven", "Eight", "Nine"};
      String[] teensPhrases = {"Ten", "Eleven", "Twelve", "Thirteen", 
            "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", 
				"Nineteen"};
      String[] highTensPhrases = {"", "", "Twenty", "Thirty", "Fourty", 
		      "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
      
      // If the number is in the desired range get the phrases
      if (number >= 0.01 && number <= 9999.99) {
      
         // If number has thousands get the thousands
         if(((int)number / 1000) > 0) {
            thousands = unitPhrases[((int)number / 1000)] + " Thousand ";
         }
         
         // If number has hundreds get the hundreds
         if(((int)(number / 100) % 10) > 0) {
            hundreds = unitPhrases[((int)(number / 100) % 10)] + " Hundred ";
         }
         
         // If the number has tens get the tens and ones
         if(((int)(number / 10) % 10) > 0) {
         
            // If the tens and ones are from 10-19 get the phrase
            if(((int)(number / 10) % 10) < 2) {
               tensAndOnes = teensPhrases[((int)(number) % 10)] + " and ";
            }
            // Else if the number is not a "teen"
            else {
      
               // If there are tens and ones get both
               if(((int)(number) % 10) > 0) {
                  tensAndOnes = highTensPhrases[((int)(number / 10) % 10)] 
                        + "-" + unitPhrases[((int)(number) % 10)] + " and ";
               }     
               // Else get the tens
               else {
                  tensAndOnes = highTensPhrases[((int)(number / 10) % 10)]
						      + " and ";
               } // End if / else
            } // End if / else
         }
         // If there are ones but no tens get the ones
         else if(((int)(number) % 10) > 0) {
            tensAndOnes = unitPhrases[((int)(number) % 10)] + " and ";
         } // End if / else if
         
         numInWords = thousands + hundreds + tensAndOnes 
               + tools.padNumber(((number * 100) % 100), 2, "00", "", "") 
               + "/100 dollars";
                
      } // End if
      
      return(numInWords);
   }
   
   //------------------------------------------------------------------------//
   public static void writeData(ArrayList<Double> dollars, String[] words,
         PrintWriter outputFile) {
      String output = ""; // String for writing data
      
      for(int i = 0; i < dollars.size(); ++i) {
         output = tools.leftPad(dollars.get(i), 9, "#####0.00", " ") + " | "
               + tools.padString(words[i], 62, "", " "); 
         
         outputFile.println(output); // Write to file
         System.out.println(output); // Echo on console
      } // End for loop
   }
   
   //------------------------------------------------------------------------//
   public static int countValid(ArrayList<Double> dollars) {
      int validDollars = 0; // Number of valid entries
		
      // Count the valid dollars
      for (int i = 0; i < dollars.size(); ++i) {
         if (dollars.get(i) <= 9999.99 && dollars.get(i) >= 0.01) {
            ++validDollars;
         }
      }
      
      return(validDollars);
   }
   
   //------------------------------------------------------------------------//
   public static double findLargest(ArrayList<Double> dollars) {
      double largest = dollars.get(0); // Largest entry
		
		// Search the dollar array for the largest valid entry
		for (int i = 1; i < dollars.size(); ++i) {
		   if ((dollars.get(i) > largest) 
			      && (dollars.get(i) <= 9999.99 && dollars.get(i) >= 0.01)) {
			   largest = dollars.get(i);
		   }
      }
		
      return(largest);
   }
   
   //------------------------------------------------------------------------//

} // End class