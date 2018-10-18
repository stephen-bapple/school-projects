/** Josephus

    This program contains the methods to solve the Josephus problem using the
    classes LinkedCircle and Node.

    Programmer: Stephen Bapple
    Professor:  Judith Gurka
    Class:      CS2, Fall 2015
    Month:      November
    
*/

import java.util.Scanner;
import java.io.*;

public class Josephus {
   String removalOrder;
   
   //------------------------------------------------------------------------//
   // Main method                                                            //
   //------------------------------------------------------------------------//
   public static void main(String [] args) throws IOException {
      Josephus program = new Josephus();
      program.processFile();
      
      /* Stub code
      System.out.println("In main method..."); */
   }
   //------------------------------------------------------------------------//
   // Process all the problems in a file                                     //
   //------------------------------------------------------------------------//
   public void processFile() throws IOException {
      Scanner inputFile = new Scanner(new File("JosephusProblems.txt"));
      int size;
      int skip;
      int safePosition;
      
      // loop through all the problems
      while(inputFile.hasNext()) {
         size = inputFile.nextInt();
         skip = inputFile.nextInt();
         inputFile.nextLine(); // Discard comments
         safePosition = solveProblem(size, skip);
         
         // Print out the results
         System.out.println("For the Josephus problem with: " + "\r\n"
               + "n: " + size + ", " + "skip: " + skip + "\r\n"
               + "The removal order is: " + removalOrder + "\r\n" 
               + "The safe position is: " + safePosition + "\r\n");
      }
      
      inputFile.close();
      
      //System.out.println("processFile() called..."); // Stub code
   }
    
   //------------------------------------------------------------------------//
   // Solve a single Josephus problem                                        //
   //------------------------------------------------------------------------//
   public int solveProblem(int size, int skip) {
      LinkedCircle circle = new LinkedCircle(size);
      removalOrder = "";
      Node previous = circle.tail;
      Node current = circle.head;
      
      while (circle.size > 1) {
         /* TESTING: just print the list
         Node test = circle.head;
         System.out.print("Current list of: ");
         for (int i = 0; i < circle.size; ++i) {
            System.out.print(test.data + " ");
            test = test.next;
         }
         System.out.println();
         TESTING */         
         
         // Skip the correct number of nodes
         for (int i = 1; i < skip; ++i) {
            previous = previous.next;
            current = current.next;
         }
         
         // Remove the losing node
         //System.out.println(circle.remove(current)); //Test code
         removalOrder = removalOrder + circle.remove(previous, current) + " ";
         current = current.next;
         
      }
      
      // When the circle is size 1, the head will have the safe position
      return circle.head.data;
      
      /* Stub code
      System.out.println("Solve problem called!");
      return -1; */
   }
   
   //------------------------------------------------------------------------//
}