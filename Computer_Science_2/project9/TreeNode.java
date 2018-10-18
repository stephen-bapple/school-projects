/** TreeNode 
    
    This class contains the data and methods for a tree type node
    used by the DataBaseTree class.
    
    Programmer: Stephen Bapple
    Professor:  Judith Gurka
    Class:      CS2, Fall 2015
    Month:      December

*/

public class TreeNode {
   private CarList thisList;
   private TreeNode left;
   private TreeNode right;
   
   //------------------------------------------------------------------------//
   // Default constructor                                                    //
   //------------------------------------------------------------------------//
   public TreeNode() {
      this.thisList = new CarList();
      this.left = null;
      this.left = null;
      
      /* Stub code
      System.out.println("TreeNode: Default constructor called..."); */
   }
 
   //------------------------------------------------------------------------//
   // Constructor with given data for the head                               //
   //------------------------------------------------------------------------//
   public TreeNode(String dataLine) {
      this();                           // Initialize an empty node
      thisList = new CarList(dataLine); // Create a list of cars
      
      /* Stub code
      System.out.println("TreeNode: Constructor with given data called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Constructor given an already instantiated CarNode                      //
   //------------------------------------------------------------------------//
   public TreeNode(CarNode newNode) {
      this();
      this.thisList = new CarList(newNode);
      
      /* Stub code
      System.out.println("TreeNode: Constructor with given CarNode called...");
      */
   }
   
   //------------------------------------------------------------------------//
   // Add a car to this Node's list                                          //
   //------------------------------------------------------------------------//
   public void addCarToList(CarNode newNode) {
      thisList.add(newNode);
      
      //System.out.println("TreeNode: addCarToList() called..."); // Stub code
   }
   
   //------------------------------------------------------------------------//
   // Get the current car being examined in the list                         //
   //------------------------------------------------------------------------//
   public Car getCarData() {
      return thisList.getCurrentCar();
      
      //System.out.println("TreeNode: getCarData() called..."); // Stub code
   }
   
   //------------------------------------------------------------------------//
   // Get the next car in the list                                           //
   //------------------------------------------------------------------------//
   public Car getNextCarInList() {
      return thisList.getNextCarInList();
      
      /* Stub code
      System.out.println("TreeNode: getNextCarInList() called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Get left node                                                          //
   //------------------------------------------------------------------------//
   public TreeNode getLeft() {
      return left;
      
      //System.out.println("TreeNode: getLeft() called..."); // Stub code
   }
   
   //------------------------------------------------------------------------//
   // Get right node                                                         //
   //------------------------------------------------------------------------//
   public TreeNode getRight() {
      return right;
      
      //System.out.println("TreeNode: getRight() called..."); // Stub code
   }
   
   //------------------------------------------------------------------------//
   // Reset the pointer in this node's list                                  //
   //------------------------------------------------------------------------//
   public void resetPointer() {
      thisList.resetPointer();
      
      //System.out.println("TreeNode: resetPointer() called..."); // Stub code
   }
   
   //------------------------------------------------------------------------//
   // Set the left node                                                      // 
   //------------------------------------------------------------------------//
   public void setLeft(TreeNode left) {
      this.left = left;
      
      //System.out.println("TreeNode: setLeft() called..."); // Stub code
   }
   
   //------------------------------------------------------------------------//
   // Set the right node                                                     //
   //------------------------------------------------------------------------//
   public void setRight(TreeNode right) {
      this.right = right;
      
      //System.out.println("TreeNode: setRight() called..."); // Stub code
   }
   
   
   //------------------------------------------------------------------------//
   // toString(): calls this node's list's toString()                        //
   //------------------------------------------------------------------------//
   @Override
   public String toString() {
      return thisList.toString();
      
      /* Stub code
      System.out.println("TreeNode: toString() called...");
      return "FIXME"; */
   }
   
   //------------------------------------------------------------------------//
   
}