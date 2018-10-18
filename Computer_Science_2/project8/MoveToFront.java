/** MoveToFront

    This program gets a String from the user and searches a selected
    linked list from an input file. If the input String is found it is 
    moved to the front of the list.

    Programmer: Stephen Bapple
    Professor:  Judith Gurka
    Class:      CS2, Fall 2015
    Month:      November
    
    Limition:
      - Case sensitive; all input data is all lowercase.
*/

import java.util.Scanner;
import java.io.*;

public class MoveToFront {
   
   //------------------------------------------------------------------------//
   // Main method                                                            //
   //------------------------------------------------------------------------//
   public static void main(String [] args) throws IOException {
      MoveToFront program = new MoveToFront();
      program.searchLists();
      
      //System.out.println("In main method..."); Stub code
   }
   
   //------------------------------------------------------------------------//
   // Get the lists from a file and prompt user to search each one           //
   //------------------------------------------------------------------------//
   public void searchLists() throws IOException {
      Scanner inputFile = new Scanner(new File("MoveToLists.txt"));
      LinkedList3 originalList;
      LinkedList3 modifiedList;
      boolean wasRemoved;
      
      // Prompt user for a term to search
      System.out.print("Type an entry to search and add or move"
            + " to the front in each list: ");
      String searchTerm = new Scanner(System.in).nextLine();
      
      // Search every list in the file for the term
      while(inputFile.hasNext()) {
         originalList = new LinkedList3(inputFile.nextLine());
         System.out.println("List read: " + originalList.toString()); 
         
         // Create a copy of the list and try to remove the search term
         modifiedList = originalList.copyList();         
         wasRemoved  = modifiedList.remove(searchTerm);
         
         // Add search term to front if removed or the back if not
         if(wasRemoved) {
            modifiedList.addInFront(searchTerm);
         }
         else {
            modifiedList.append(searchTerm);
         }
         
         // Print the results 
         System.out.println("Item to remove: " + searchTerm 
               + ((wasRemoved)? ", moved to front" : ", added to end of list")
               + "\r\n" + "New list: " + modifiedList.toString() + "\r\n");
      }
     
      /* Stub code
      System.out.println("searchLists() called..."); */
   }
   
   //------------------------------------------------------------------------//
}