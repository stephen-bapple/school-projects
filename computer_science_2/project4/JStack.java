/** This class file creates a simple stack using an array.
    The array is of type Integer, so that the methods pop
    and peek return null when called on an empty stack.

    Stephen Bapple
    
    CS 2, Fall 2015
    
    Limitations:
      - Stacks are limited to a maximum size, although perfectly fine for
        BigAddition's implementation, it might not be as useful for another 
        program.
    
*/

public class JStack {
   // Instance data
   Integer[] array;
   int top;
   
   //------------------------------------------------------------------------//
   // Default Constructor                                                    //
   //------------------------------------------------------------------------//
   public JStack() {
      array = new Integer[10];
      top   = -1;
   }
   
   //------------------------------------------------------------------------//
   // Specific size array constructor                                        //
   //------------------------------------------------------------------------//
   public JStack(int size) {
      array = new Integer[size];
      top   = -1;
   }
   
   //------------------------------------------------------------------------//
   // Push                                                                   //
   //------------------------------------------------------------------------//
   public boolean push(int data) {
      if(!isFull()) {
         array[++top] = data;
         return(true);
      }
      else
         return(false);
   }
   
   //------------------------------------------------------------------------//
   // Pop                                                                    //
   //------------------------------------------------------------------------//
   public Integer pop() {
      if(!isEmpty())
         return(array[top--]);
      else
         return(null);
         //return(-1); // Really need to FIX
   }
   
   //------------------------------------------------------------------------//
   // Peek                                                                   //
   //------------------------------------------------------------------------//
   public Integer peek() {
      if(!isEmpty())
         return(array[top]);
      else
         return(null);
      /*
      else
         return(-1); // Really need to FIXME
      */
   }
   
   //------------------------------------------------------------------------// 
   // isFull                                                                 //
   //------------------------------------------------------------------------//
   public boolean isFull() {
      if(top == array.length - 1)
         return(true);
      else
         return(false);
   }
   
   //------------------------------------------------------------------------//
   // isEmpty                                                                //
   //------------------------------------------------------------------------//
   public boolean isEmpty() {
      if(top == -1)
         return(true);
      else
         return(false);
   }
   
   //------------------------------------------------------------------------//
   // This method returns the maximum size of the stack                      //
   //------------------------------------------------------------------------//
   public int getSize() {
      return(array.length);
   }
   
   //------------------------------------------------------------------------//
}
