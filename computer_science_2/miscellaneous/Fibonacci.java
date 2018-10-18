import java.util.Scanner;

public class Fibonacci {
   public static void main(String [] args) {
      Scanner keyboard = new Scanner(System.in);
      
      System.out.print("Please enter a position to get a fibonacci from: ");
      System.out.println(fib(keyboard.nextInt()));
      System.exit(0);
      
   }
   public static long fib(int n) {
      return(fib(n, 0));
   }
   public static long fib(int n, int c) {
      //System.out.print("Calling:::");
      //printDots(c);      
      
      if(n == 2 || n == 1) { 
         //System.out.print("Returning");
         //printDots(c);
         return(1);
      }
      
      //System.out.print("Returning");
      //printDots(c);
      return( fib(n-1, c + 1) + fib(n-2, c + 1));
   }

   private static void printDots(int dots) {
      for (int i = 0; i < dots; ++i) {
         System.out.print('.');
      }
      System.out.println();
      return;
   }
}