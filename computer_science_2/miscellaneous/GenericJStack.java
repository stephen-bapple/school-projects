/** This class file creates a generic stack using an arraylist..
    The arraylist is of generic type E, so that the methods pop
    and peek return null when called on an empty stack.

    Stephen Bapple
    
    CS 2, Fall 2015
    
    Limitations:
*/
import java.util.ArrayList;

public class GenericJStack<E> {
   // Instance data
   ArrayList<E> array;
   int top;

   //------------------------------------------------------------------------//
   // Default Constructor                                                    //
   //------------------------------------------------------------------------//
   public GenericJStack() {
      array = new ArrayList<E>();
      top = -1;
   }
   
   //------------------------------------------------------------------------//
   // Specific size array constructor                                        //
   //------------------------------------------------------------------------//
   public GenericJStack(int size) {
      array = new ArrayList<E>(size);
      top = -1;
   }
   
   //------------------------------------------------------------------------//
   // Push                                                                   //
   //------------------------------------------------------------------------//
   public boolean push(E data) {
      if (!isFull()) {
         //top++;
         array.add(++top, data);
         return true;
      }
      else
         return false;
   }
   
   //------------------------------------------------------------------------//
   // Pop                                                                    //
   //------------------------------------------------------------------------//
   public E pop() {
      if (!isEmpty())
         return array.get(top--);
      else
         return null;
         //return(-1); // Really need to FIX // Fixed
   }
   
   //------------------------------------------------------------------------//
   // Peek                                                                   //
   //------------------------------------------------------------------------//
   public E peek() {
      if (!isEmpty())
         return array.get(top);
      else
         return null;
   }
   
   //------------------------------------------------------------------------// 
   // isFull                                                                 //
   //------------------------------------------------------------------------//
   public boolean isFull() {
         return false;
   }
   
   //------------------------------------------------------------------------//
   // isEmpty                                                                //
   //------------------------------------------------------------------------//
   public boolean isEmpty() {
      if (top == -1)
         return true;
      else
         return false;
   }
   
   //------------------------------------------------------------------------//
   // This method returns the current size of the stack                      //
   //------------------------------------------------------------------------//
   public int getSize() {
      return array.size();
   }

   //------------------------------------------------------------------------//
   // This method returns a String representing the stack.                   //
   //------------------------------------------------------------------------//
   @Override
   public String toString() {
      if (isEmpty()) return "[<Empty>]";

      String rep = "[ " + array.get(0);
      for (int i = 1; i < array.size() && i <= top; i++) {
         rep += ", " + array.get(i);
      }
      return rep + " ]";
   }

   //------------------------------------------------------------------------//
}
