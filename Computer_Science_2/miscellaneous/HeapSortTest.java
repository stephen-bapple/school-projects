/*
   Program: HeapSortTest
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

/* Doesn't compile. It is included here just as a reference for HeapSorty.java
   to show where the instructions for HeapSorty.java came from. */

import java.util.ArrayList;    

public class HeapSortTest {
/*
   private ArrayList<Integer> heap = new ArrayList<Integer>(20);
   private int initialData[] = {2, 17, 5, 33, 55, -12, 80, 32};

   public void fillData() {
   //   System.out.println("fillData method");
      
   }
   
   // methods to be written
   //     findParent (parent's index)
   //     findLeftChild (index)
   //     findRightChild (index)
   //     swap

   private void createHeap() {
      heap.add(initialData[0]);  // root
      for (int i = 1; i < initialData.length; i++) {
         heap.add(initialData[i]);
         // heapify
         int current = heap.size()-1;
         boolean done = false;
         while (!done) {
            // test #1: at the root?
            if (current != 0) {
             //  check if position is correct in the max heap
             //  if not, swap with parent
               done = (current == 0) || 
                    heap.get(findParent(current)) > heap.get(current);
            }
         }  // heapify 1 value
      }
   }  // createHeap

   // extract data method: ArrayList / heap --> data array 

   public void sort() {
      // call 2-part sort - build heap and extract data (private)
   }  // sort

   public void printHeap(String message) {
      System.out.println(message);
      for (int i = 0; i < heap.size()-1; i++) {
         System.out.print(heap.get(i) + ", ");
      System.out.println();
      }  // end printing heap
   }  // printHeap

   public void printList(String message) {
      System.out.println(message);
      for (int num : initialData) {
         System.out.print(num + "  ");
         // method done??
      // System.out.println(heap.get(heap.size()-1));
      }  // end printing heap
   }  // printHeap


   public static void main (String args[]) {
      
      HeapSortTest tester = new HeapSortTest();
      
      tester.fillData();
      
      tester.createHeap();
      tester.printHeap("Heap after initial creation");

      tester.printList("Data before sorting");
      tester.sort(); 
      tester.printHeap("Heap after sorting (should be empty)");
      tester.printList("Data after sorting (should be sorted");
     
   }  // main
*/
}  // heapSortTest
