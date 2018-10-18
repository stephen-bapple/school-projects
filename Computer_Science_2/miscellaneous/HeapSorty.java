/*
   Program: HeapSorty
   Description: A program to experiment with heap sort, using a max heap, 
      implemented in an array.
   Author: J. Gurka
   Associate Author: Stephen Bapple
   Date: Oct. 2015
   Reference: Introduction to Java Programming, Liang
   Limitations
      1. int data only
      2. perfect user assumed
      3. no preservavtion of results (screen output only)
      4. duplicates not checked
*/
import java.util.ArrayList;

public class HeapSorty {
   private ArrayList<Integer> heap = new ArrayList<Integer>(20);
   private int initialData[] = {5, 2, 9, 7, 3, 6, 999, 22, 67, 4};
   private int sortedData[] = new int[initialData.length];
   //------------------------------------------------------------------------//
   public static void main(String [] args) {
      HeapSorty program = new HeapSorty();
      program.createHeap();
      //program.printData();
      program.getSorted();
   }
   
   //------------------------------------------------------------------------//
   public void printSorted() {
      for (int i = 0; i < heap.size() - 1; ++i) {
         System.out.print(sortedData[i] + ", ");
      }  
      System.out.println(sortedData[heap.size() - 1] + ".");
   }
   
   //------------------------------------------------------------------------//
   public void getSorted() {
      for (int i = 0; i < sortedData.length; ++i) {
         System.out.println(heap.get(0));
         sortedData[i] = heap.get(0);
         
         swap(0, heap.size() - 1);
         heap.remove(heap.size() - 1);
         
         if (heap.size() > 0)
            sort(0);
      }
      for (int i = 0; i < sortedData.length - 1; ++i) {
         System.out.print(sortedData[i] + ", ");
      }
      System.out.println(sortedData[sortedData.length - 1] + "");
   }
   
   //------------------------------------------------------------------------//
   public void sort(int i) {
      int node       = heap.get(i);
      
      if(findLeftChild(i) < heap.size() && findRightChild(i) < heap.size()) {
          int rightChild = heap.get(findRightChild(i));
          int leftChild  = heap.get(findLeftChild(i));
          
          if (node < leftChild || node < rightChild) {
            if (leftChild > rightChild) {
               swap(i, findLeftChild(i));
               sort(findLeftChild(i));
            }
           else if (rightChild > leftChild) {
               swap(i, findRightChild(i));
               sort(findRightChild(i));
            }
         }
      }
      else if(findLeftChild(i) < heap.size()) {
         int leftChild  = heap.get(findLeftChild(i));
         if(node < leftChild) {
            swap(i, findLeftChild(i));
            sort(findLeftChild(i));
         }
      }
      else if(findRightChild(i) < heap.size()) {
         int rightChild = heap.get(findRightChild(i));
         if (node < rightChild) {
            swap(i, findRightChild(i));
            sort(findRightChild(i));
         }
      }
   }
   
   //------------------------------------------------------------------------//
   public void fillData() {
      System.out.println("fillData() called... Does nothing. "
            + "Purpose filled by createHeap.");
   }
   
   //------------------------------------------------------------------------//
   public void printData() {
      for (int i = 0; i < heap.size() - 1; ++ i) {
         System.out.print(heap.get(i) + " ");
      }
      System.out.println(heap.get(heap.size() - 1) + ".");
   }
   
   //------------------------------------------------------------------------//
   public void createHeap() {
      heap.add(initialData[0]); // Root
      
      for (int i = 1; i < initialData.length; i++) {
         heap.add(initialData[i]);
         heapify(heap.size() - 1);
      } // End for
   }
   
   //------------------------------------------------------------------------//
   public int findLeftChild(int parentIndex) {
      return (parentIndex * 2) + 1;
   }
   
   //------------------------------------------------------------------------//
   public int findRightChild(int parentIndex) {
      return (parentIndex * 2) + 2;
   }
   
   //------------------------------------------------------------------------//
   public int findParent(int childIndex) {
      return (childIndex - 1) / 2;
   }
   
   //------------------------------------------------------------------------//
   public void heapify(int i) {
      if (i <= 0) 
         return;
      else if (heap.get(findParent(i)) >= heap.get(i))
         return;
         
      swap(i, findParent(i));
      heapify(findParent(i));
   }
   
   //------------------------------------------------------------------------//
   public void swap(int i, int j) {
      int temp = heap.get(i);      // Parent
      int temp2 = heap.get(j);     // Child
  
      heap.set(i, temp2);          // Set the parent to its child
      heap.set(j, temp);           // Set the child to its parent
      
      heapify(findParent(i));      // Heapify
   
   }
}
