/** AddAreas
    This program adds the areas of many different types of objects
    
    Stephen Bapple
    
    CS2, Fall 2015
    October
    
    Specifications taken directly from Introduction to Java Programming by
    Daniel Liang. Page 530, exercise 13.12. Otherwise, all code here is original
    
    Addition specifications:
      - Uses Triangle and Square objects for variety
      
    Uses Rectangle taken fom page 414 listing 11.3

*/

public class AddAreas {
   /** main method */
   public static void main(String [] args) {
      // Create a test array
      GeometricObject[] objArray = new GeometricObject[] {new Circle(5), 
            new Circle(11.2), new Rectangle(5,2), new Rectangle(10.5, 11),
            new Triangle(), new Square(1)};
       
      // Print the sum of their areas
      System.out.println(sumArea(objArray));
   }
   
   /** return the sum of all areas */
   public static double sumArea(GeometricObject[] a) {
      double area = 0;
      
      for (int i = 0; i < a.length; ++i) {
         area += a[i].getArea();
      }
      
      return(area);
   }
}

