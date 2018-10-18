/** Node

    This class file contains the constructors and data for the int type nodes
    in a singly-linked list.

    Programmer: Stephen Bapple
    Professor:  Judith Gurka
    Class:      CS2, Fall 2015
    Month:      November
    
*/

public class Node {
   int data;
   Node next;
   
   //------------------------------------------------------------------------//
   // Default constructor                                                    //
   //------------------------------------------------------------------------//
   public Node() {
      data = 0;   
      next = null;
   }
   
   //------------------------------------------------------------------------//
   // Initialize with given data                                             //
   //------------------------------------------------------------------------//
   public Node(int data) {
      this();
      this.data = data;
      
   }
   
   //------------------------------------------------------------------------//
   // Initialize with given data and a pointer                               //
   //------------------------------------------------------------------------//
   public Node(int data, Node pointer) {
      this(data);
      next = pointer;
      
   }
   
   //------------------------------------------------------------------------//
   
}