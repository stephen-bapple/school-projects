/** Binary to Decimal conversion program
    
    Original program by Daniel Liang.
    Conversions done by Stephen Bapple.
    
    CS 2, Fall 2015.
    
    The program was converted from listing 6.8 in Introduction to 
    Java Programming by Y. Daniel Liang, on page 218 
    
    The original program coverted hexadecimal to decimal. 
    The new program converts binary to decimal, as well as handles bad input.
    All symbols and methods have been changed to reflect the different numbers 
    handled. 
      Class: Hex2Dec -> Bin2Dec
      Methods:
         -hexToDecimal     -> binToDecimal
         -hexCharToDecimal -> binCharToDecimal
      Variables:
         -hex -> bin
         -Added: dec
    All other changeds are noted with "Enhancement:" or "Change: "
    Specification limitations:
      - The values are limited by max int
      - Does not process negative values, as 2's complement needs s specified length.
*/

import java.util.*; // Change: from util.Scanner, in order to use exceptions

public class Bin2Dec {
   /** Main method **/
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      
      // Enhancement: try / catch block in case of invalid input
      try {
         // Prompt the user to enter a String
         System.out.print("Enter a binary number: ");
         String bin = input.nextLine();
         int dec = binToDecimal(bin); // Enhancement: Added for exceptions
         
         System.out.println("The decimal value for binary number " 
               + bin + " is " + dec);
      }
      catch(NumberFormatException e){
         System.out.println("Error: " + e);
      } // End try / catch block
   }
   
   /* binToDecimal */
   public static int binToDecimal(String bin){
      int decimalValue = 0;
      
      // Enhancement: try / catch block
      try {
         for (int i = 0; i < bin.length(); i++) {
            // Try to convert
            decimalValue = (decimalValue * 2) + binCharToDecimal(bin.charAt(i));
         }
      }
      catch(NumberFormatException e) {
         throw e; // Rethrow the exception if it occurs
      } // End try / catch block
      
      return decimalValue;
   }

   /* binCharToDecimal */
   public static int binCharToDecimal(char ch) {
      if (ch >= '0' && ch <= '1')
         return ch - '0';
      else // Throw an exception if the number isn't 1 or 0
         throw new NumberFormatException("Binary can only have 1's and 0's");
   }
   
}
