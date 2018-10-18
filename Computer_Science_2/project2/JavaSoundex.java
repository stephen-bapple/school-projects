/** Java Soundex

    This program prompts a user for the names of files to read from and write
    to, then reads all the names in the input file, converts them to Soundex,
    and writes them to the output file. The program originally used the console
    and the relevant code has been commented out/on.
    
    Stephen Bapple
    
    September 2015
    
    CS 2, Fall 2015
    
    Extra Credit:
      - Full rule 4
      - Prefix handling
      
    Limitations:
      - Cannot get full file addresses from the user, just files in the same
        directory
      - Assumes a valid file name is typed
       
*/
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class JavaSoundex {

   // Instance declarations
   // private String input;      // Phase 2 - obsolete
   // private String nameString; // Used only locally in phase 2
   // private String soundexStr; // Phase 2 - obsolete 
   private Scanner inputFile;
   private PrintWriter output;
   private ArrayList<String> names;
   private String[][] codes;
   
   //------------------------------------------------------------------------//
   // Public methods                                                         //
   //------------------------------------------------------------------------//
   // This method calls all the appropriate methods to encode names          //
   //------------------------------------------------------------------------//
   public void convertFile()throws IOException {
      getFileNames();
      readFile();
      toSoundex();
      outputData();         
      
      /* Obsolete in phase 2
      inputName();
      
      if(!input.equals("")) {
         toSoundex();
         outputResults();
         
      }*/
      
      return;   
   } 
   
   //------------------------------------------------------------------------//
   // This method gets user input from the console- Obsolete                 //
   //------------------------------------------------------------------------//
   /*
   public void inputName() {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("Please enter a surname, or nothing to quit:");
      input = keyboard.nextLine();
   }
   */
   //------------------------------------------------------------------------//
   // This method gets the input and output file names from the user and     //
   // instantiates the Scanner and PrintWriter to use them                   //
   //------------------------------------------------------------------------//
   public void getFileNames()throws IOException {
      Scanner keyboard = new Scanner(System.in);
      
      // Get the input file
      System.out.print("Please type the name of the file to read from: ");
      inputFile = new Scanner(new File(keyboard.nextLine()));
      
      // Get the output file
      System.out.print("Please type the name of the file to write to: ");
      output = new PrintWriter(new FileWriter(keyboard.nextLine()));
      
      return;
   }
   
   //------------------------------------------------------------------------//
   // This method adds all the names in the input file to an ArrayList       //
   //------------------------------------------------------------------------//
   public void readFile()throws IOException {
      String inputStr;
      names = new ArrayList<String>();
      
      // Read the file, but ignore empty lines and those that start with: '/'
      while(inputFile.hasNext()) {
         inputStr = inputFile.nextLine();
         if(!inputStr.isEmpty()) {
            if(inputStr.charAt(0) != '/') {
               names.add(inputStr); 
            }
         }
      }
      
      inputFile.close();
      
      return;
   }
   
   //------------------------------------------------------------------------//
   // This method implements the Soundex algorithm                           //
   // The program loops through the ArrayList of names, first getting the    //
   // regular Soundex code from another method, then checking if the name has//
   // a prefix, and if so it removes the prefix and gets the alt code from   //
   // the encoding method                                                    //
   //------------------------------------------------------------------------//
   public void toSoundex() {
      String nameString;
      codes = new String[names.size()][2];
      
      for(int i = 0; i < names.size(); ++i) { 
         // Ignore case
         //nameString = input.toUpperCase(); - Obsolete
         nameString = names.get(i).toUpperCase();
         // Note: all the code in getCode() was here in phase 1
         codes[i][0] = getCode(nameString, codes[i][0]);
         
         // If the name has a prefix get the alternate code without it
         if(hasPrefix(nameString)) {
            nameString = removePrefix(nameString);
            codes[i][1] = getCode(nameString, codes[i][1]);
         }
         
      } // End for loop
   
      return;
   }
   //------------------------------------------------------------------------//
   // This method prints the names and their codes to the selected file      //
   //------------------------------------------------------------------------//
   public void outputData() {
      for(int i = 0; i < names.size(); ++i) {
         output.println("Name: " + names.get(i) + "\r\n" + "Soundex code: " 
               + codes[i][0]);
         if(codes[i][1] != null) {
            output.println("Alt code: " + codes[i][1]);
         }
         output.println();
      }
      
      output.close();
      
      return;
   }
   //------------------------------------------------------------------------//
   // This method gets the code of one name                                  //
   //------------------------------------------------------------------------//
   public String getCode(String name, String code) {
      // Local declarations
      int tempInt;
      
      // Always get the first character
      //soundexStr = "" + input.charAt(0); // Obsolete in phase 2
      code = "" + name.charAt(0);
         
      // W and H are ignored when encoding and checking for repeat characters
      name = name.replace("W", "");
      name = name.replace("H", "");
     
      // Get the first four codes of all non-adjacent consonants
      for(int i = 1; code.length() < 4; ++i) {
         // If there are at least 4 characters in the name get their codes 
         if(i < name.length()) {   
            // Only record consonants
            if(!isVowel(name.charAt(i))) {
               // Get code of letter
               tempInt = getIntCode(name.charAt(i));
               
               // Only record the code if it doesn't repeat itself.
               if(tempInt != getIntCode(name.charAt(i - 1))) {
                  code = code + tempInt;
               
               } // End if 
                  
            } // End if
         } 
         // Else pad zeros
         else {
            code = code + 0;
            
         } // End if / else  
               
      } // End for loop

      return(code);
   }
   
   //------------------------------------------------------------------------//
   // This method prints the name and code to the console - Obsolete         //
   //------------------------------------------------------------------------//
   /*
   public void outputResults() {
      System.out.println("Name: " + input + "\r\n" + "Soundex code: " 
            + soundexStr);
      return;
   }
   */
   //------------------------------------------------------------------------//
   // Private helper methods                                                 //
   //------------------------------------------------------------------------//
   // This method tests if the character is a removable character or vowel   //
   //------------------------------------------------------------------------//
   private boolean isVowel(char test) {
      return("AEHIOUWY".indexOf(test) != -1);
   }

   //------------------------------------------------------------------------//
   // This method tests each char against multiple lists of letters, if      //
   // the char is in the list the method returns the appropriate code        // 
   // according to the Soundex algorithm                                     //
   //------------------------------------------------------------------------//
   private int getIntCode(char test) {
      if ("BFPV".contains("" + test)) {
         return(1);
      }
      else if("CGJKQSXZ".contains("" + test)) {
         return(2);
      }
      else if("DT".contains("" + test)) {
         return(3);
      }
      else if("L".contains("" + test)) {
         return(4);
      }
      else if("MN".contains("" + test)) {
         return(5);
      }
      else if("R".contains("" + test)) {
         return(6);
      }
      return(-1); // Character not allowed
    
   }  
   
   //------------------------------------------------------------------------//
   // This method checks a name against a list of prefixes and returns true  //
   // if there is a match.                                                   //
   // indexOf() is used to ensure the prefix is at the beginning of the name //
   //------------------------------------------------------------------------//
   private boolean hasPrefix(String name) {
      return(name.indexOf("VAN") == 0 || name.indexOf("CON") == 0 
            || name.indexOf("DE") == 0 || name.indexOf("DI") == 0 
            || name.indexOf("LA") == 0);  
   }
   
   //------------------------------------------------------------------------//
   // This method removes the prefix from a name and returns the new name,   //
   // indexOf() is used to ensure that only characters matching a prefix at  //
   // the beginning of the name are removed                                  //
   //------------------------------------------------------------------------//
   private String removePrefix(String name) {
      if(name.indexOf("VAN") == 0) {
         name = name.replace("VAN", "");
      }
      else if(name.indexOf("CON") == 0) {
         name = name.replace("CON", "");
      }
      else if(name.indexOf("DE") == 0) {
         name = name.replace("DE", "");
      }
      else if(name.indexOf("DI") == 0) {
         name = name.replace("DI", "");
      }
      else if(name.indexOf("LA") == 0) {
         name = name.replace("LA", "");
      }   
      
      return(name);
   }
   
   //------------------------------------------------------------------------//
}

