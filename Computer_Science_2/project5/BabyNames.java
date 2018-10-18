/** BabyNames

    This program processes baby names in a file, then lets a user search
    for the ranking of a specific baby name.
   
    Stephen Bapple
    
    CS2, Fall 2015
    October
      
    Specifications taken directly from Introduction to Java Programming by
    Daniel Liang. Page 492.
    
    Limitations:
      - Assumes user enters input correctly
      - Does not sort data in any way, although it's still pretty fast
      for small files (like these)
*/
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.io.*;

public class BabyNames {
   // Instance data
   private String[][] names;
   
   //------------------------------------------------------------------------//
   // main method                                                            //
   //------------------------------------------------------------------------//
   public static void main(String [] args) throws IOException {
      BabyNames program = new BabyNames();
      
      // Get the name to search from the user
      program.promptForName();
   
   }
   
   //------------------------------------------------------------------------//
   // promptForName - get a name from the user                               //
   //------------------------------------------------------------------------//
   public void promptForName() throws IOException {
      String input;
      int rank;
      
      // Get first name to search
      input = JOptionPane.showInputDialog("Please enter a name, gender, " +
            "and year to search, or nothing to quit."); 
      
      // Get names and search until user is done
      while(!input.equals("")) {
         rank = getRank(input.split(" ")[0], 
               input.split(" ")[1].toUpperCase().charAt(0), 
               Integer.parseInt(input.split(" ")[2]));
         if (rank != -1) { 
            JOptionPane.showMessageDialog(null, input.split(" ")[0] 
                  + " is ranked #" + rank + " in year " + input.split(" ")[2]);
         }
         else {
            JOptionPane.showMessageDialog(null, "Name not found");
         }
         
         // Search again?
         input = JOptionPane.showInputDialog(
               "Enter another name or nothing to quit.");
      }
      
      return;
   }
   
   //------------------------------------------------------------------------//
   // getRank - take a name and gender and find their rank                   //
   //------------------------------------------------------------------------//
   private int getRank(String name, char gender, int year) throws IOException {
      int i = 0;
      
      processFiles(year);
      
      if (gender == 'M')
         i = 0;
      else if (gender == 'F')
         i = 1;
      for (int j = 0; j < 1000; ++j) {
         if (names[i][j].toUpperCase().equals(name.toUpperCase()))
            return j + 1;
      }
      
      return -1; // Would never be valid data
   }
   
   //------------------------------------------------------------------------//
   // processFiles - index all the entries in the files for easy searching   //
   //------------------------------------------------------------------------//
   private void processFiles(int year) throws IOException {
      // Local declarations
      Scanner fileList;
      String inputLine;
      
      // Initialize the arrays
      names = new String[2][1000];
      
      // Load the list of files
      fileList = new Scanner(new File("FileList.txt"));
      
      // Default to first entry
      Scanner inputFile = new Scanner(new File(fileList.nextLine()));
      
      // Get the correct year
      for (int i = 2; i < 11; ++i) {
         if (year - 2000 != i) {
            fileList.nextLine(); // Discard line
         }
         else {
            // Initialize scanner to correct file
            inputFile = new Scanner(new File(fileList.nextLine()));
            break;
         }
      }
      
      // Process the file
      for (int i = 0; i < 1000; ++i) {
         inputFile.nextInt();              // Get rid of the rank
         inputLine = inputFile.nextLine(); // Get the data
         inputLine = inputLine.trim();     // Get rid of extra ' 's
         
         // Parse boys' data
         names[0][i] = inputLine.split(" ")[0].trim();
         
         // Parse girls' data
         names[1][i] = inputLine.split(" ")[2].trim();
      }
      
      return;
   }
   
   //------------------------------------------------------------------------//
}
