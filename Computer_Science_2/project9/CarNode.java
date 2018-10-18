/** CarNode
    
    Simple node for a linked-list of cars, singly linked with head & tail.
    
    Programmer: Stephen Bapple
    Professor:  Judith Gurka
    Class:      CS2, Fall 2015
    Month:      December
    
    Limitations:

*/

import java.util.Scanner;

public class CarNode {
   private Car thisCar;
   private CarNode next;
   
   //------------------------------------------------------------------------//
   // Default constructor - create an empty node                             //
   //------------------------------------------------------------------------//
   public CarNode() {
      this.thisCar = null;
      this.next = null;
      
      /* Stub code
      System.out.println("CarNode: Default constructor called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Constructor with given data                                            //
   //------------------------------------------------------------------------//
   public CarNode(String data) {
      this();
      parseData(data);
      
      /* Stub code
      System.out.println("CarNode: Constructor with given data called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Constructor with an already initialized car                            //
   //------------------------------------------------------------------------//
   public CarNode(Car newCar) {
      this();
      this.thisCar = newCar;
      
      /* Stub code
      System.out.println("CarNode: Constructor with given Car called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Accessors                                                              //
   //------------------------------------------------------------------------//
   public Car getCarData() {
      return thisCar;
      
      /* Stub code
      System.out.println("CarNode: getCarData() called...");
      return new Car(); */
   }
   
   //------------------------------------------------------------------------//
   public CarNode getNext() {
      return next; 
      
      /* Stub code
      System.out.println("CarNode: getNext() called...");
      return new Car(); */
   }
   
   //------------------------------------------------------------------------//
   // Mutators                                                               //
   //------------------------------------------------------------------------//
   public void setNext(CarNode next) {
      this.next = next;
      
      //System.out.println("CarNode: setNext() called..."); // Stub code
   }

   //------------------------------------------------------------------------//
   // Private helper methods                                                 //
   //------------------------------------------------------------------------//
   private void parseData(String data) {
      Scanner parser = new Scanner(data);
     
      // Make a new Car with all the parameters in the given data
      thisCar = new Car(parser.next(), parser.next(), parser.nextInt(),
            parser.next(), parser.nextLine().trim());
      
      //System.out.println("CarNode: parseData() called..."); // Stub code
   }
   
   //------------------------------------------------------------------------//
   // toString(): print out the car in the node                              //
   //------------------------------------------------------------------------//
   @Override
   public String toString() {
      return thisCar.toString();
      
      /*
      System.out.println("CarNode: toString() called...");
      return "FIXME"; */
   }
   
   //------------------------------------------------------------------------//
}