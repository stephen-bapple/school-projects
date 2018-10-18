/**
 * Stack Demo program.
 *
 * Used for testing custom stack.
 */

import java.util.Scanner;

public class TestStack {
   public static void main(String [] args) {
      boolean keepGoing = true;
      String[] input = new String[3];
      Scanner keyboard = new Scanner(System.in);
      GenericJStack<Integer> stack = new GenericJStack<>();
      int data = 0;

      System.out.println("This program tests a custom stack class. Please "
                       + "type push, pop, peek, isFull, isEmpty, show, "
                       + "or nothing to quit");

      // Keep going until the user decide to quit.
      while(keepGoing) {
         input[0] = keyboard.nextLine();
         
         if(input[0].equals("")) {
            keepGoing = false;
            System.out.println("Bye!");
         }
         else {
            
            while(input[0].length() < 4) {
               input[0] = input[0] + " ";
            }
            
            if(input[0].substring(0,4).toLowerCase().equals("push")){
               if(input[0].length() > 5) {
                 input[0] = input[0].trim();   // Remove  whitespace.
                 input = input[0].split(" +"); // Split input into command/data
                 
                 try {
                    data = Integer.parseInt(input[1]); // Parse data into an int
                 }
                 catch(NumberFormatException ex) {
                    System.out.println("Invalid data, please type "
                                     + "an integer to push");
                 }
               }
               else {
                  System.out.println("You need something to push");
                  input[0] = "NOTVALID";
               }
            }
            
            input[0] = input[0].trim();
            input[0] = input[0].toLowerCase();

            switch(input[0]) {
               case "push":
                  //System.out.println("attempting to push");
                  stack.push(data); 
                  break;
                  
               case "pop":
                  //System.out.println("attempting to pop");
                  System.out.println(stack.pop());
                  break;
                  
               case "peek":
                  //System.out.println("peeking ;)");
                  System.out.println(stack.peek());
                  break;
                  
               case "isfull":
                  //System.out.println("checking if full");
                  System.out.println(stack.isFull());
                  break;
                  
               case "isempty":
                  //System.out.println("checking if empty");
                  System.out.println(stack.isEmpty());
                  break;
               case "reverse":
                  //BigAddition big = new BigAddition();
                  //stack = big.reverseStack(stack);
                  stack = reverse(stack);
                  break;
               case "show": 
                  System.out.println(stack);                  
                  break;
               default:
                  System.out.println("Not a valid command.");
               
            } // End switch
         } // End if / else  
      } // End while loop
   } // End main
   
   private static GenericJStack<Integer> reverse(GenericJStack<Integer> stack) {
      int temp = 0;
      GenericJStack<Integer> newStack = new GenericJStack<>();

      while(!stack.isEmpty()) {
         temp = stack.pop();
         newStack.push(temp);
         System.out.print(temp);
      }
      return newStack;
   }
} // End class
