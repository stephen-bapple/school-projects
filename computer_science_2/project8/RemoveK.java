/** RemoveK

    This program gets an index from the user, checks if it is valid,
    and then if it is removes the element at the index from the list.
    Note: indexing starts at 1 for the user instead of zero.

    Programmer: Stephen Bapple
    Professor:  Judith Gurka
    Class:      CS2, Fall 2015
    Month:      November
    
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class RemoveK {
   private ArrayList<DoubleLinkedList> originalLists;
   
   //------------------------------------------------------------------------//
   // Main method                                                            //
   //------------------------------------------------------------------------//
   public static void main(String [] args) throws IOException {
      RemoveK program = new RemoveK();
      program.getLists();
      program.getUserInput();
      
      /* Stub code
      System.out.println("In main method..."); */
   }
   
   //------------------------------------------------------------------------//
   // Get the lists from a file                                              //
   //------------------------------------------------------------------------//
   public void getLists() throws IOException {
      Scanner inputFile = new Scanner(new File("RemoveKLists.txt"));
      originalLists = new ArrayList<>();
      
      // Get every list
      while(inputFile.hasNext()) {
         originalLists.add(new DoubleLinkedList(inputFile.nextLine()));
      }
      
      /* Stub code
      System.out.println("getLists() called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Continously prompt user for input                                      //
   //------------------------------------------------------------------------//
   public void getUserInput() {
      Scanner keyboard = new Scanner(System.in);
      String message;
      int selection;
      int position;
      
      // Loop continuously until the user quits
      while (true) {
         System.out.println("Pick a list: " + "\r\n" + "0: <Exit Program>");
         
         // Print all the options
         for (int i = 0; i < originalLists.size(); ++i) {
            System.out.println((i + 1) + ": " 
                  + originalLists.get(i).toString());
         }

         selection = keyboard.nextInt(); // Get option
         
         // Check what to do for recieved option
         if (selection == 0) {
            break;
         }
         else if (selection > originalLists.size() || selection < 0) {
            System.out.println("Invalid selection. Choose a number.");
         }
         else {
            // If option is valid and not "quit", continue
            System.out.print("Enter a position to remove: ");
            position = keyboard.nextInt();
            
            // Get a copy of the selected list
            DoubleLinkedList currentList = 
                  originalLists.get(selection - 1).copyList();
            
            // Check if position exists in list
            if (position <= 0 || position > currentList.size) { 
               message = "Invalid position selected, does not exist in list.";
            }
            else {
               currentList.removePosition(position);
               message = currentList.toString();
            }
            
            // Print out the results of the attempted removal
            System.out.println("Original list: " 
                  + originalLists.get(selection - 1).toString() + "\r\n"
                  + "Position to delete: " + position + "\r\n"
                  + "New list: " + message + "\r\n");
         }
      
      }
      
      /* Stub code
      System.out.println("getUserInput() called..."); */
   }
   
   //------------------------------------------------------------------------//
}