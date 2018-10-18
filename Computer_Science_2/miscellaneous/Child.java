public class Child extends Parent{
   private int y;
   
   public static void main(String [] args) {
      Child prog = new Child();
      prog.printY();
   }
   public void printY() {
      y = 2;
      System.out.println(super.y);
   }
}