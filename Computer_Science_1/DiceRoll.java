import java.util.Random;

public class DiceRoll {
   public static void main (String[] args) {
      Random randGen = new Random();  // New random number generator

      System.out.println("Four rolls of a dice...");

      // randGen.nextInt(6) yields 0, 1, 2, 3, 4, or 5
      // so + 1 makes that 1, 2, 3, 4, 5, or 6
      System.out.println(randGen.nextInt(6) + 1);
      System.out.println(randGen.nextInt(6) + 1);
      System.out.println(randGen.nextInt(6) + 1);
      System.out.println(randGen.nextInt(6) + 1);

      return;
   }
}