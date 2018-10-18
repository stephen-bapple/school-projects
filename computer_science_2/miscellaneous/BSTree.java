public class BSTree {
// unverified
   protected BSTreeNode root;
   protected int size = 0;
   //
   //
   //
   public BSTree(){}
   //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=//
   // Build the tree from the data input                                     //
   //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=//
   public BSTree(int m) {
      int[] data = getData();
      for (int i = 0; i < data.length; ++i) {
      }
   }
   
   //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=//
   // Get test data for debug mode                                           //
   //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=//
   public int[] getData() {
      return new int[] {10, 17, 4, 20, 21, 30, 9};
   }
   
   
}
