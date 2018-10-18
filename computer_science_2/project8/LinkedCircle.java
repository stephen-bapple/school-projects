/** LinkedCircle

    This class contains the methods and data to implement the list of nodes 
    used in the Josephus class.
    
    Programmer: Stephen Bapple
    Professor:  Judith Gurka
    Class:      CS2, Fall 2015
    Month:      November
    
    Limitations:
      - removeNode() used to always return true since it would never be called
        on an invalid case, it was changed after the walkthrough to return the
        value of the node removed.
*/

public class LinkedCircle {
   int size;
   Node head;
   Node tail;
   
   //------------------------------------------------------------------------//
   // Default constructor                                                    //
   //------------------------------------------------------------------------//
   public LinkedCircle() {
      this.size = 0;
      this.head = null;

      /* Stub code
      System.out.println("Default constructor called..."); */
   }
   

   //------------------------------------------------------------------------//
   // Constructor with given size                                            //                                     
   //------------------------------------------------------------------------//
   public LinkedCircle(int size) {
      this.size = size;
      fillCircle(size);
      
      /* Stub code
      System.out.println("Constructor with given size and skip called...");*/
   
   }
   
   //------------------------------------------------------------------------//
   // Fill the circle with a given size                                      //
   // Each Node recieves an index as data                                    //
   //------------------------------------------------------------------------//
   public void fillCircle(int size) {
      // Add the head and second node
      head = new Node(0);
      Node current = head;
      
      // Fill in the rest of the circle
      for (int i = 1; i < size; ++i) {
         current.next = new Node(i);
         current = current.next;
      }
      
      // Link the last node to the first
      tail = current;
      current.next = head;
      
      /* Stub code
      System.out.println("fillCircle() called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Remove the node that is given as a parameter from the list             //
   // changed after walkthrough to pass in previous as well, removeNode      //
   // changed to current, and some uneccesary code removed                   //
   //------------------------------------------------------------------------//
   public int remove(Node previous, Node current) {
      /* Following code removed after walkthrough:
      // Starting nodes to check for removal
      Node previous = tail;
      Node current  = head;
      
      // Search until the correct node is found
      while (current != removeNode){
         previous = current;
         current = current.next;
      }
         End removed code*/
      
      // If the head or tail are to be removed, update them
      if (current == head)
         head = head.next;
      if (current == tail)
         tail = previous;
      
      // Remove the node
      previous.next = current.next;
      //System.out.println(current.data); //Test code
      --size;
         
      return current.data;
      
      /* Stub code
      System.out.println("remove() called...");
      return false; Stub return*/
   }
   //------------------------------------------------------------------------//
}