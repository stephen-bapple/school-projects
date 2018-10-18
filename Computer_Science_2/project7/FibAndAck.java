/** FibAndAck
    
    This program calls FibCalculator and AckCalculator to find all the values
    up to the specified maximums of fib(30), and ack(4,15).
    
    Programmer: Stephen Bapple
    Professor:  Judith Gurka
    Class:      CS2, Fall 2015
    Month:      November
    
*/

import java.io.*;

public class FibAndAck {
   public static void main(String [] args) throws IOException{
      FibCalculator fibonacci = new FibCalculator();
      AckCalculator ackermann  = new AckCalculator();
      
      // Get the fibonaccis
      //fibonacci.getInput(); // User input phase
      fibonacci.getFileInput();
      
      // Get the ackermanns
      //ackermann.getInput(); // User input phase
      ackermann.calculateValues();
   }
}