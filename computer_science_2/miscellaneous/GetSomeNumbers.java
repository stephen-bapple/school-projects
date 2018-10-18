import java.util.*;

public class GetSomeNumbers {
   public static void main(String [] args) {
      Scanner input = new Scanner(System.in);
      boolean badInput = true;
      boolean keepGoing = true;
      int number = -1;

      while(keepGoing) {
         do {
            try { 
               System.out.print("Enter an integer: ");
               number = input.nextInt();
               
               System.out.println(
                  "The number entered is " + number);
               badInput = false;           
            
            }
            catch (InputMismatchException ex) {
               System.out.println("Try again. (" +
                  "Incorrect input: an integer is required)");
               //input.nextLine();
            }
               
         } while(badInput);
         if(number == -1) 
            keepGoing = false;
      }
   }
   
}
