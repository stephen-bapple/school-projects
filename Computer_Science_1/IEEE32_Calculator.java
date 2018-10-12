/**
  * This program calculates the main four mathematical operations on IEEE 
  * 32-bit floating point numbers.
  * 
  * Currently incomplate and unlikely to be completed.
  *
  * Stephen Bapple 
  *
  */
  
import java.util.Scanner;

public class IEEE32_Calculator {

   public static void main (String [] args) {
      // Declarations
      Scanner keyboard = new Scanner(System.in);
      int[] num1       = new int[32]; // Input number one
      int[] num2       = new int[32]; // Input number two
      int[] answer     = new int[32]; // The answer!!!
      char operation   = ' ';         // Desired math operation
      String opPerformed = "Something went wrong..."; // Bad form
      
      // Explain the program to the user
      explainProgram();
      
      // Get the two numbers
      getNumber(num1, keyboard, "first");  // First number
      operation = getOperation(keyboard);  // Operation to perform
      getNumber(num2, keyboard, "second"); // Second number
      
      switch(operation) {
         case 'A':
            answer = add(num1, num2);
            opPerformed = "plus";
            break;
            
         case 'S':
            answer = subtract(num1, num2);
            opPerformed = "minus";
            break;
            
         case 'M':
            answer = multiply(num1, num2);
            opPerformed = "multiplied by";
            break;
            
         case 'D':
            answer = divide(num1, num2);
            opPerformed = "divided by";
            break;
      }
      outputResults(num1, num2, answer, opPerformed);
   } // End main
   
   //------------------------------------------------------------------------//
   public static void explainProgram() {
      System.out.println("Yeah, so like, this program does the four main "
            + "mathematical operations on 32-bit IEEE numbers, and stuff.");
            String testString = "012345678901234567890123456789012";
      System.out.println(testString.charAt(0));
      return;
   }
   
   //------------------------------------------------------------------------//
   public static void getNumber(int[] number, Scanner keyboard, 
         String keyword) {
      // Declarations
      boolean isIEEE = false;
      char charForm = ' ';
      String input = "";
      int intForm = 0;
      String tempString = "";
      char tempChar = ' ';
      
      while(!isIEEE) {
         System.out.print("Please enter your " + keyword + 
               " 32-bit IEEE number using only 1s and 0s: ");
         input = keyboard.nextLine();
         
         for(int i = 0; i < 32; ++i) {
            tempChar =input.charAt(i);
            tempString = "" + tempChar;
             
            intForm  = Integer.parseInt(tempString);
            intForm += 0; 
            number[i] = intForm;         
         }   
         
         for(int i = 0; i < 32; ++i) {
            if ((number[i] != 1) && (number[i] != 0)) {
               System.out.println("Number is not in binary form, " 
                     + "please enter your number in binary or press Q to quit");      
               System.out.println(number[i]);
               isIEEE = false;
               break;
            }
            else {
               isIEEE = true;
            } // End if / else
         
         } // End for loop
      
      } // End while loop
      
   
   }
   
   //------------------------------------------------------------------------//
   public static char getOperation(Scanner keyboard) {
      String input   = "";
      char operation = ' ';
      
      System.out.println("Please type the operation you which to perform."
            + "\r\nSupported operations are addition, subtraction, division, "
            + " and multiplication.");
      input = keyboard.nextLine();
      input = input.toUpperCase();
      operation = input.charAt(0);
      
      return(operation);
   }
   
   //------------------------------------------------------------------------//
   public static int[] crunchNumbers(int[] num1, int[] num2, int[] answer) {
   
      return(answer);
   }
   
   //------------------------------------------------------------------------//
   public static int[] add(int[] num1, int[] num2) {
      int[] answer = new int[32];
      int carryBit = 0;
      boolean isSubtraction = false;
      
      // Decide if operation is really addition or subtraction
      if(num1[0] == 1 && num2[0] == 1) {
         // Then make first bit of answer a 1 and continue addition
      }
      else if(num1[0] == 1 && num2[0] == 0) {
         isSubtraction = true; 
         // sign bit of num1 zero
         // call subtraction and switch the number's places
         // e.g. subtract(num2, num1);
         // Finish with the subtraction method
         
      }
      else if(num1[0] == 0 && num2[0] == 1) {
         isSubtraction = true;
         // set sign bit of num2 to zero
         // call subtraction normally
         // Finish with the subtraction method
      }
      else {
         // 0 and 0
         // No changes, standard addition
      }
      
      for(int i = 1; i < 9; ++i) {
         if ((num1[i] + num2[i] + carryBit) > 1) {
            answer[i] = (num1[i] + num2[i] + carryBit - 2);
            carryBit = 1;
         }
         else {
            answer[i] = (num1[i] + num2[i] + carryBit);
            carryBit = 0;
         }
      } // End for Loop
      if(carryBit != 0) {
         System.out.println("Warning: overflow occured, the resulting number "
               + "is too large for 32-bit IEEE.");
      
      } // End if statement
      
      return(answer);
   }

   //------------------------------------------------------------------------//
   public static int[] subtract(int[] num1, int[] num2) {
      int[] answer = new int[32];
      return(answer);
   
   }

   //------------------------------------------------------------------------//
   public static int[] multiply(int[] num1, int[] num2) {
      int[] answer = new int[32];
      return(answer);
  
   }
   //------------------------------------------------------------------------//

   public static int[] divide(int[] num1, int[] num2) {
      int[] answer = new int[32];
      return(answer);
   
   } 
   
   //-------------------------------------------------------------------------//
   public static void outputResults(int[] num1, int[] num2, int[] result, 
         String opPerformed) {
      // Declarations - String forms of the parts of the IEEE number
      String signBit  = ""; // 1st bit
      String exponent = ""; // 2nd through 8th bits
      String mantissa = ""; // 9th through 32nd bits
      
      // Get the sign bit
      signBit = "" + result[0];
      
      // Get the exponent
      for(int i = 1; i < 9; ++i) {
         exponent = exponent + result[i] + "";
      }
      
      // Get the mantissa
      for(int i = 9; i < 32; ++i) {
         mantissa = mantissa + result[i] + "";
      }
      
      // Print the string form of the result
      System.out.println(num1 + " " + opPerformed + " " + num2 
            + ",\r\nis equal to [" + signBit + "] [" + exponent + "] [" 
            + mantissa + "] in IEEE.");
            
      return;
   }
   
   //------------------------------------------------------------------------//

} // End class
