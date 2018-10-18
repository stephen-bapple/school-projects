/**
 * Expression evaluation program.
 *
 * Program copied from Introduction to Java Programming by
 * Daniel Liang.
 *
 * Slightly modified to process exponentiation by Stephen Bapple.
 */

import java.util.Stack;

public class EvaluateExpression {
   public static void main(String[] args) {
      // Check number of arguments passed
      if (args.length != 1) {
         System.out.println(
            "Usage: java EvaluateExpression \"expression\"");
            System.exit(1);
      }
      
      /* Try to evaluate the expression and throw an exception if 
         any problems occur */
      try {
         System.out.println(evaluateExpression(args[0]));
      }
      catch(Exception ex) {
         System.out.println("Wrong expression: " + args[0]);
      }
      
   } // End main
   
   //------------------------------------------------------------------------//
   // Methods                                                                //
   //------------------------------------------------------------------------//
   // evaluateExpression:                                                    //
   //    This method evaluates a mathematical expression                     //
   //------------------------------------------------------------------------//
   public static int evaluateExpression(String expression) {
      // Create operandStack of ints to store operands
      Stack<Integer> operandStack = new Stack<>();
      
      // Create operatorStack of characters to store operators
      Stack<Character> operatorStack = new Stack<>();
      
      // Insert Blanks
      expression = insertBlanks(expression);
      
      // Extract operands and operators by splitting around blanks
      String[] tokens = expression.split(" ");
      
      /* Phase 1: Scan tokens
         Enhanced for loop, puts every element of tokens in token each time
         Like writing token = tokens[i] every time
      */
      for (String token: tokens) { 
         if(token.length() == 0) // It's a blank
            continue; // Go to the while loop
         else if (token.charAt(0) == '+' || token.charAt(0) == '-') {
            // process all +, -, *, / in the top of the operator stack
            while (!operatorStack.isEmpty() && // While stack isn't empty
               (operatorStack.peek() == '+' || // and has an operator on top
                operatorStack.peek() == '-' || // Like if you had (2 * 3) + 5  
                operatorStack.peek() == '*' || // Or if you had (2 - 3) + 5, do that last no matter what b/c +- is lowest priority
                operatorStack.peek() == '/' ||
                operatorStack.peek() == '^')) {
               processAnOperator(operandStack, operatorStack);
            }
            
            // After processing the all the previous operations, push the +- operator onto the stack
            operatorStack.push(token.charAt(0));
         }
         else if (token.charAt(0) == '*' || token.charAt(0) == '/') {
            // Proess all *, / in the top of the operator stack
            while(!operatorStack.isEmpty() &&
               (operatorStack.peek() == '*' ||
                operatorStack.peek() == '/' ||
                operatorStack.peek() == '^')) {
               processAnOperator(operandStack, operatorStack);
            }
            
            //Push the * or / onto the stack
            operatorStack.push(token.charAt(0));
         }
         else if (token.charAt(0) == '^') {
            // Proess all ^ in the top of the operator stack
            while(!operatorStack.isEmpty() &&
               (operatorStack.peek() == '^')) {
               processAnOperator(operandStack, operatorStack);
            }
            
            //Push the ^ onto the stack
            operatorStack.push(token.charAt(0));
         }
         else if(token.trim().charAt(0) == '(') {
            operatorStack.push('(');
         } 
         else if(token.trim().charAt(0) == ')') {
            while(operatorStack.peek() != '(') {
               processAnOperator(operandStack, operatorStack);
            }
            operatorStack.pop(); // pop
         }
         else {
            operandStack.push(new Integer(token));
         }
      }
      // Phase two: process everything left over
      while (!operatorStack.isEmpty()) {
         processAnOperator(operandStack, operatorStack);
      }
      
      return operandStack.pop();   
   }
   
   //------------------------------------------------------------------------//
   public static void processAnOperator(
          Stack<Integer> operandStack, Stack<Character> operatorStack) {
      char op = operatorStack.pop();
      int op1 = operandStack.pop();
      int op2 = operandStack.pop();
      
      if (op == '+')
         operandStack.push(op2 + op1);
      else if (op == '-')
         operandStack.push(op2 - op1);
      else if (op == '*')
         operandStack.push(op2 * op1);
      else if (op == '/')
         operandStack.push(op2 / op1);
      else if (op == '^') 
         operandStack.push((int)(Math.pow(op2, op1)));
   }
   
   //------------------------------------------------------------------------//
   public static String insertBlanks(String s) {
      String result = "";
      
      for (int i = 0; i < s.length(); ++i) {
         if (s.charAt(i) == '(' || s.charAt(i) == ')' ||
             s.charAt(i) == '+' || s.charAt(i) == '-' ||
             s.charAt(i) == '*' || s.charAt(i) == '/' ||
             s.charAt(i) == '^')
            result += " " + s.charAt(i) + " ";
         else 
            result += s.charAt(i);
      }
      return result;
   }
   
   //------------------------------------------------------------------------//

} // End class
