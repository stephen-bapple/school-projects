/** DoubleLinkedList

    This class file contains the methods and data to implement the doubly-
    linked list used in RemoveK.

    Programmer: Stephen Bapple
    Professor:  Judith Gurka
    Class:      CS2, Fall 2015
    Month:      November
    
    Limitations:
      - removePosition() does not return a boolean because in removeK it will
        never be called on an invalid position
*/

public class DoubleLinkedList {
   DoubleNode head;
   int size;
   
   //------------------------------------------------------------------------//
   // Default constructor for an empty list                                  //
   //------------------------------------------------------------------------//
   public DoubleLinkedList() {
      this.head = null;
      this.size = 0;
      
      /* Stub code
      System.out.println("Default / empty list constructor called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Constructor that creates a list from a line of Strings                 //
   //------------------------------------------------------------------------//
   public DoubleLinkedList(String data){
      this(); // Call empty list constructor
      
      // If incoming data isn't also an empty list, fill the list
      if(!data.equals(""))
         fillList(data);
      
      /* Stub code
      System.out.println("Constructor with given line of data called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Method that splits a line into linked Nodes of type String             //
   //------------------------------------------------------------------------//
   public void fillList(String dataLine) {
      // Seperate the data elements
      String[] elements = dataLine.split(" +");
      
      // Initialize the head
      head = new DoubleNode(elements[0]);
      DoubleNode current = head;
      ++size;
      
      // Fill the rest of the list
      for (int i = 1; i < elements.length; ++i) {
         current.next = new DoubleNode(elements[i]);
         current.next.last = current;
         current = current.next;
         ++size;
      }
      
      //System.out.println("fillList() called..."); Stub code
   }
   
   //------------------------------------------------------------------------//
   // Delete an element at a specified position                              //
   //------------------------------------------------------------------------//
   public void removePosition(int position) {
      DoubleNode current = head;
      
      // Loop until position found
      for (int i = 1; i < position; ++i) {
         current = current.next;
      }
      
      /* First check if the head or the last element (no tail variable) is
         to be removed, otherwise do the default removal */
      if (position == 1) {
         head = head.next;
      }
      else if (position == size) {
         current.last.next = null;
      }
      else {
         current.last.next = current.next;
         current.next.last = current.last;
      }
      
      --size;
      
      /* Stub code
      System.out.println("removePosition() called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Print the list                                                         //
   //------------------------------------------------------------------------//
   @Override
   public String toString() {
      String listString = "<Empty List>"; // Default list message
      
      //if (size > 0) { 
      if (head != null) {
         listString = head.data;
         DoubleNode current = head.next;
         
         //for (int i = 1; i < size; ++i) {
         while (current != null) {
            listString = listString + " " + current.data;
            current = current.next;
         }

      }
      
      return listString;
      
      /* Stub code
      return "FIXME";
      System.out.println("toString() called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Make a deep copy of the list                                           //
   //------------------------------------------------------------------------//
   public DoubleLinkedList copyList() {
      // Return full copy
      //if (size > 0) 
      if (head != null)
         return new DoubleLinkedList(toString());
      
      // Return empty list   
      return new DoubleLinkedList();
      
      /* Stub code
      System.out.println("copyList() called..."); */
   
   }
   
   //------------------------------------------------------------------------//

}