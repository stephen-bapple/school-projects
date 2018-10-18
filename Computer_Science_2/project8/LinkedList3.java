/** LinkedList3

    This class contains the methods and data to maintain a singly-linked list
    with a head and tail.

    Programmer: Stephen Bapple
    Professor:  Judith Gurka
    Class:      CS2, Fall 2015
    Month:      November
    
    Notes:
      - Athough the variable size is kept track of, the class has been revised
        to not use it in the class but it is still available to other programs
*/

public class LinkedList3 {
   int size;
   Node3 head;
   Node3 tail;
   
   //------------------------------------------------------------------------//
   // Default constructor - creates an empty list                            //
   //------------------------------------------------------------------------//
   public LinkedList3() {
      this.size = 0;
      this.head = null;
      this.tail = null;
      
      //System.out.println("Default constructor called..."); Stub code
   }
   
   //------------------------------------------------------------------------//
   // Constructor with a given line of data                                  //
   // Almost entirely copied from DoubleLinkedList                           //
   //------------------------------------------------------------------------//
   public LinkedList3(String data) {
      this(); // Call empty list constructor
      
      // If incoming data isn't also an empty list, fill the list
      if(!data.equals("")) 
         fillList(data);
         
      //System.out.println("Constructor with given data called..."); Stub code
   }
   
   //------------------------------------------------------------------------//
   // Fill the list with a given line of elements                            //
   // Almost entirely copied from DoubleLinkedList                           //
   //------------------------------------------------------------------------//
   public void fillList(String dataLine) {
      // Seperate the data elements
      String[] elements = dataLine.split(" +");
      
      // Initialize the head
      head = new Node3(elements[0]);
      Node3 current = head;
      ++size;
      
      // Fill the rest of the list
      for (int i = 1; i < elements.length; ++i) {
         current.next = new Node3(elements[i]);
         current = current.next;
         ++size;
      }
      
      tail = current;
      
      //System.out.println("fillList() called..."); Stub code
   }
   
   //------------------------------------------------------------------------//
   // Remove a node from the list if its value matches the parameter         //
   //------------------------------------------------------------------------//
   public boolean remove(String test) {
      // If list is empty return false
      //if (size == 0) {
      if (head == null) {
         return false;
      }
      
      // If the head is to be removed, do so
      if (head.data.equals(test)) {
         head = head.next;
         --size;
         return true;
      }
      
      // Otherwise start looking by initializing two nodes
      Node3 previous = head;
      Node3 current = head.next;
      
      // Search for the node to be removed
      //for (int i = 1; i < size; ++i) {
      while (current != null ) {
         if (current.data.equals(test)) {
               previous.next = current.next;
               --size;
               //System.out.println(size);
               return true;
         }
         
         // Move to next node
         previous = previous.next;
         current = current.next;
      }
      
      //System.out.println("remove() called..."); Stub code
      
      return false; // If node not found - Did not need to be changed from stub
      
   }
   
   //------------------------------------------------------------------------//
   // Append an element to the end of the list                               //
   //------------------------------------------------------------------------//
   public void append(String data) {
      /* Intialize head if list empty, 
         otherwise add node onto and update tail */
      //if (size < 1) {
      if (head == null) {
         head = new Node3(data);
         tail = head;
         ++size;
      }
      else {
         tail.next = new Node3(data);
         tail = tail.next;
         ++size;
      }
      
      //System.out.println("In append()..."); Stub code
   }
   
   //------------------------------------------------------------------------//
   // Add an element to the front of the list                                //
   //------------------------------------------------------------------------//
   public void addInFront(String data) {
      Node3 newHead = new Node3(data);
      newHead.next = head;
      head = newHead;
      ++size;
      
      //System.out.println("In addInFront()..."); Stub code
   }
   
   //------------------------------------------------------------------------//
   // Print the list                                                         //
   // Almost entirely copied from DoubleLinkedList                           //
   //------------------------------------------------------------------------//
   @Override
   public String toString() {
      String listString = "<Empty List>"; // Default list message
      
      //if (size > 0) {
      if (head != null) {
         listString = "" + head.data;
         Node3 current = head.next;
         
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
   // Almost entirely copied from DoubleLinkedList                           //
   //------------------------------------------------------------------------//
   public LinkedList3 copyList() {
      // Return full copy
      //if (size > 0) 
      if (head != null)
         return new LinkedList3(toString());
      
      // Return empty list
      return new LinkedList3();
      
      /* Stub code
      System.out.println("copyList() called..."); */
   
   }
   
   //------------------------------------------------------------------------//
}