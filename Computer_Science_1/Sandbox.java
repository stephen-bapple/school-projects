/**
 * Sandbox for testing miscellaneous things.
 */
  
import java.util.Scanner;
  
public class Sandbox {
   static Toolkit tools = new Toolkit();
   
   public static void main (String [] args) {
      Scanner keyboard = new Scanner(System.in);
      char character = ' ';
      int shift = 0;

      int[][] array = new int[5][5];   
      /*
      System.out.println("Please enter an uppercase character");
      character = keyboard.next().charAt(0);
      System.out.println("Please enter how many characters you want "
            + "to shift right or left (use negative for left)");
      shift = keyboard.nextInt();
   
      character = shiftChar(character, shift);
      System.out.println(character);
      */
   
   
   } // end main
   public static char shiftChar(char inputChar, int shift) {
      char answer = ' ';
      
      
      if (inputChar >= 'A' && inputChar <= 'Z') {
         answer = (char)((int)inputChar + shift);
      }
      
      return(answer);
   }
   
   //double3 = double1 - double2;
   
   //tools.leftPad(,,,)
   
   
} // end class

   /* Code for testing if a triangle can be made from 3 given sides
   public static boolean isReal(double a, double b, double c) {
      boolean realTriangle = false;
      if (a > 0 && b > 0 && c > 0) {
         if (a > b && a > c){
            if (a < (b + c)) {
               realTriangle = true;
            }
            else {
               realTriangle = false;
            }
         }
         else if (b > a && b > c){
            if (b < (a + c)) {
               realTriangle = true;
            }
            else {
               realTriangle = false;
            }
         }
         else if (c > a && c > b){
            if (c < (a + b)) {
               realTriangle = true;
            }
            else {
               realTriangle = false;
            }
         }
      }
      else {
         realTriangle = false;
         System.out.println("Real triangles cannot have negative sides.");   
      }
   return(realTriangle);   
   }
   //------------------------------------------------------------------------//
   */
