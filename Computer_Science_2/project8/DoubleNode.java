/** DoubleNode

    This class file contains the constructors and data for the nodes
    in a doubly-linked list.

    Programmer: Stephen Bapple
    Professor:  Judith Gurka
    Class:      CS2, Fall 2015
    Month:      November
    
*/

public class DoubleNode {
   String data;
   DoubleNode last;
   DoubleNode next;
   
   //------------------------------------------------------------------------//
   // Default constructor                                                    //
   //------------------------------------------------------------------------//
   public DoubleNode(){
      this.data = "";
      this.last = null; 
      this.next = null;
      
      /* Stub code
      System.out.println("Default constructor called..."); */
   }
   //------------------------------------------------------------------------//
   // Constructor with given data                                            //
   //------------------------------------------------------------------------//
   public DoubleNode(String data){
      this();
      this.data = data;
      
      /* Stub code
      System.out.println("Constructor with given data called..."); */
   }
   
   //------------------------------------------------------------------------//
}