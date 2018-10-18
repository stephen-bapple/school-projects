/** Node3

    This class file contains the constructors and data 
    for the String type nodes in a singly-linked list.

    Programmer: Stephen Bapple
    Professor:  Judith Gurka
    Class:      CS2, Fall 2015
    Month:      November
    
    Limitations:
      - List is singly-linked for simplicity
*/

public class Node3 {
   String data;
   Node3 next;
   
   //------------------------------------------------------------------------//
   // Default constructor                                                    //
   //------------------------------------------------------------------------//
   public Node3() {
      this.data = "";
      this.next = null;
   }
   
   //------------------------------------------------------------------------//
   // Constructor with given data                                            //
   //------------------------------------------------------------------------//
   public Node3(String data) {
      this();
      this.data = data;
   }
   
   //------------------------------------------------------------------------//
}