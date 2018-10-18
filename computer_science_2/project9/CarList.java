/** CarList
    
    This class contains the data and methods to manage the list of cars
    in the database.
    
    Programmer: Stephen Bapple
    Professor:  Judith Gurka
    Class:      CS2, Fall 2015
    Month:      December
    
*/

public class CarList {
   private CarNode head;
   private CarNode tail;
   private CarNode pointer; // Pointer for searching the list
   
   //------------------------------------------------------------------------//
   // Default constructor                                                    //
   //------------------------------------------------------------------------//
   public CarList() {
      this.head = null;
      this.tail = null;
      this.pointer = null;
      
      /* Stub code
      System.out.println("CarList: Default constructor called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Constructor with given data for the head                               //
   //------------------------------------------------------------------------//
   public CarList(String dataLine) {
      this();
      add(new CarNode(dataLine));
      this.pointer = head; 
      
      /* Stub code
      System.out.println("CarList: Constructor with given data called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Constructor with given CarNode for the head                            //
   //------------------------------------------------------------------------//
   public CarList(CarNode newNode) {
      this();
      add(newNode);
      this.pointer = head;
      
      /* Stub code
      System.out.println("CarList: Constructor with given CarNode called...");
      */
   }
   
   //------------------------------------------------------------------------//
   // Create a new node from a given car and call .add to continue adding    //
   //------------------------------------------------------------------------//
   public void add(Car newCar) {
      add(new CarNode(newCar));
      
      /* Stub code
      System.out.println("CarList: add() called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Add a node to the end of the list                                      //
   //------------------------------------------------------------------------//
   public void add(CarNode newNode){
      // Set head if this is first node
      if (head == null) {
         head = newNode;
         tail = newNode;
         pointer = head;
      }
      else { // Otherwise update the tail
         tail.setNext(newNode);
         tail = newNode;
      }
      
      /* Stub code
      System.out.println("CarList: add() called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Get the current car being examined                                     //
   //------------------------------------------------------------------------//
   public Car getCurrentCar() {
      return pointer.getCarData();
      
      /* Stub code
      System.out.println("CarList: getCurrentCar() called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Get the next car in the list: return null if at last car               //
   // Will return null if no next car, but will keep pointer on last car     //
   //------------------------------------------------------------------------//
   public Car getNextCarInList() {
      if (pointer.getNext() != null) {
         pointer = pointer.getNext();
         return pointer.getCarData();
      }
      else { 
         return null;
      }
      
      /* Stub code
      System.out.println("CarList: getNextCarInList() called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Delete the car in the list pointer is referencing                      //
   //------------------------------------------------------------------------//
   public void deleteCurrentCar() {
      // Update head if it is to be removed
      if (pointer == head) {
         head = head.getNext();
         if (pointer == tail)
            tail = null;
      }
      else {
         CarNode previous = head; // Start at the head
         
         // Walk previous until it encounters the pointer
         while (previous.getNext() != pointer) {
            previous = previous.getNext();
         }
         
         // Update tail if it is to be removed
         if (pointer == tail) {
            previous.setNext(null);
            tail = previous;
         }
         else {
            previous.setNext(pointer.getNext());
         }
      }
   }
   
   //------------------------------------------------------------------------//
   // Reset the pointer to the beginning of the list                         //
   //------------------------------------------------------------------------//
   public void resetPointer() {
      pointer = head;
      
      //System.out.println("CarList: resetPointer() called..."); // Stub code
   }
   
   //------------------------------------------------------------------------//
   // Return true if list is empty.                                          //
   //------------------------------------------------------------------------//
   public boolean isEmpty() {
      return (head == null);
      
      //System.out.println("CarList: isEmpty() called..."); // Stub code
   }
   
   //------------------------------------------------------------------------//
   // toString(): Store the list of cars in a string                         //
   //------------------------------------------------------------------------//
   @Override
   public String toString() {
      String carsInList = "";
      CarNode pointer = head;
      
      // Get every car's toString()
      while (pointer != null) {
         carsInList = carsInList.equals("") ? pointer.toString() : carsInList 
               + "\r\n" + pointer.toString();
         
         /* Testing...
         carsInList = carsInList + " " + pointer.toString() + " ";
         System.out.println("Is this line the problem?" + "Current car: " 
            + pointer.getCarData().getLicense()); */
         
         pointer = pointer.getNext();
      }
      
      return carsInList;   
      
      /* Stub code
      System.out.println("CarList: toString() called...");
      return "FIXME"; */
   }
   
   //------------------------------------------------------------------------//
}