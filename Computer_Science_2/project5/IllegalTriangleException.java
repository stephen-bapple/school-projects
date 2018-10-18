/** IllegalTriangleException
    A simple exception to throw when a triangle doesn't have valid sides.
    Based off of Java Exception API.

    Stephen Bapple
    
    CS 2, Fall 2015
    October 2015
*/

public class IllegalTriangleException extends Exception {
   //------------------------------------------------------------------------//
   // Exception constructors                                                 //
   //------------------------------------------------------------------------//
   // Default constructor                                                    //
   //------------------------------------------------------------------------//
   public IllegalTriangleException() {
      super("Invalid sides entered."); 
   }
   //------------------------------------------------------------------------//
   // Custom message constructor                                             //
   //------------------------------------------------------------------------//
   public IllegalTriangleException(String customMessage) {
      super(customMessage);
   }
   //------------------------------------------------------------------------//
   // Cause only constructor                                                 //
   //------------------------------------------------------------------------//
   public IllegalTriangleException(Throwable cause) {
      super("Invalid sides entered.", cause);
   }
   
   //------------------------------------------------------------------------//
   // Custom message and cause constructor                                   //
   //------------------------------------------------------------------------//
   public IllegalTriangleException(String customMessage, Throwable cause){
      super(customMessage, cause);
   }
   
   //------------------------------------------------------------------------//
}