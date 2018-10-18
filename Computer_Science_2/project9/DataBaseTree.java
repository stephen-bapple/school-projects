/** DataBaseTree
   
    This class contains the data and methods to manage the
    tree type database of cars used in the SearchCars class.
    
    Programmer: Stephen Bapple
    Professor:  Judith Gurka
    Class:      CS2, Fall 2015
    Month:      December
    
    Limitations:
*/

public class DataBaseTree {
   private TreeNode root;
   private TreeNode pointer;
   private int totalCars;
   
   //------------------------------------------------------------------------//
   // Default constructor - Create an empty tree                             //
   //------------------------------------------------------------------------//
   public DataBaseTree() {
      this.root = null;
      this.pointer = null;
      this.totalCars = 0;
      
      /* Stub code
      System.out.println("DataBase: Default constructor called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Constructor with given data for the root                               //
   //------------------------------------------------------------------------//
   public DataBaseTree(String dataLine) {
      this();
      this.root = new TreeNode(dataLine);
      this.pointer = root;
      this.totalCars = 1;
      
      /* Stub code
      System.out.println("DataBase: Constructor with given root called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Public handler to add a node to the tree                               //
   //------------------------------------------------------------------------//
   public void addCar(String dataLine) {
      CarNode newCarNode = new CarNode(dataLine);
      addCar(newCarNode, root);
      ++totalCars;
      
      //System.out.println("DataBase: addCar() called..."); // Stub code
   }
   
   //------------------------------------------------------------------------//
   // Private helpter to add a node to the tree                              //
   //------------------------------------------------------------------------//
   private void addCar(CarNode temp, TreeNode pointer) {
      // Create the root if tree is empty
      if (pointer == null) {
         root = new TreeNode(temp);
         this.pointer = root;
      }
      // Add car to list if this node has the same make
      else if (temp.getCarData().compareTo(pointer.getCarData()) == 0) {
         pointer.addCarToList(temp);
      }
      // Check left and right nodes, add new node if one is null,
      else if (temp.getCarData().compareTo(pointer.getCarData()) < 0) {
         if (pointer.getLeft() == null)
            pointer.setLeft(new TreeNode(temp));
         else 
            addCar(temp, pointer.getLeft());
      }
      else { // Will be > 0 by triviality
         if (pointer.getRight() == null)
            pointer.setRight(new TreeNode(temp));
         else
            addCar(temp, pointer.getRight());
      }
      
      /* Stub code
      System.out.println("DataBase: addCar() auxillary called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Get the current car selected                                           //
   //------------------------------------------------------------------------//
   public Car getCurrentCar() {
      if (pointer != null)
         return pointer.getCarData();
      return null;
      
      /* stub code
      System.out.println("DataBase: getCurrentCar() called");
      return new Car(); */
      
   }
   
   //------------------------------------------------------------------------//
   // Get the next car in the list                                           //
   // Gets the next car, but will not go past the current make list          //
   //------------------------------------------------------------------------//
   public Car getNextCarInList() {   
      return pointer.getNextCarInList();
      
      /* Stub code
      System.out.println("DataBase: getNextCarInList() called...");
      return new Car(); */
   }
   
   //------------------------------------------------------------------------//
   // Get the next alphabetically lesser carList (left) in the tree          //
   //------------------------------------------------------------------------//
   public TreeNode getLeftNode() {
      if (pointer.getLeft() != null) {
         pointer = pointer.getLeft();
         pointer.resetPointer(); // Reset the node's list
         return pointer;
      }
      else {
         return null;
      }
      
      /* Stub code
      System.out.println("DataBase: getLeftNode() called..."); */
   }
      
   //------------------------------------------------------------------------//
   // Get the next alphabetically greater carList (right) in the tree        //
   //------------------------------------------------------------------------//
   public TreeNode getRightNode() {
      if (pointer.getRight() != null) {
         pointer = pointer.getRight();
         pointer.resetPointer(); // Reset the node's list
         return pointer;
      }
      else {
         return null;
      }
      
      /* Stub code
      System.out.println("DataBase: getRightNode() called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Reset the pointer to the root                                          //
   //------------------------------------------------------------------------//
   public void resetPointer() {
      pointer = root;
      root.resetPointer();
      
      /* Stub code
      System.out.println("Database: resetPointer() called..."); */
   }
   
   //------------------------------------------------------------------------//
   // toString(): print all cars in the list in order (LVR)                  //
   //------------------------------------------------------------------------//
   @Override
   public String toString() {
      String report = "All cars in database with makes in alphabetical order." 
            + "\r\n" + "Total cars: " + totalCars + "\r\n" 
            + "Make, Model, Year, Color, License:" + "\r\n";
      return report + toStringAux(root);
      
      /* Stub code
      System.out.println("DataBase: toString() called..."); */
   }
   
   //------------------------------------------------------------------------//
   // recursive helper for toString()                                        //
   //------------------------------------------------------------------------//
   private String toStringAux(TreeNode pointer) {
      if (pointer == null)
         return "";
         
      return toStringAux(pointer.getLeft()) + "\r\n" 
            + "Make: " + pointer.getCarData().getMake() + "\r\n"
            + pointer.toString() + "\r\n"  
            + toStringAux(pointer.getRight());
      
      /* Stub code      
      System.out.println("DataBase: toStringAux() called...");
      return "FIXME"; */
   }
   
   //------------------------------------------------------------------------//
   // Get pointer node                                                       //
   //------------------------------------------------------------------------//
   public TreeNode getPointer() {
      return pointer;
      
      /* Stub code 
      System.out.println("DataBase: getPointer() called...");
      return new TreeNode(); */
   }
   
   //------------------------------------------------------------------------//
   // Set pointer node
   //------------------------------------------------------------------------//
   public void setPointer(TreeNode pointer) {
      this.pointer = pointer;
      
      /* Stub code
      System.out.println("DataBase: setPointer() called..."); */
   }
   
   //------------------------------------------------------------------------//
}