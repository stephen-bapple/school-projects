/** Car
    
    This class contains the data for one car in the database CarDatabase.
    Included fields:
      - Make
      - Model
      - Year
      - Color
      - License (and normalized license)
      
    Programmer: Stephen Bapple
    Professor:  Judith Gurka
    Class:      CS2, Fall 2015
    Month:      December
    
    Limitations:
      
*/

public class Car implements Comparable<Car>, Cloneable {
   private String make;
   private String model;
   private int year;
   private String color;
   private String license;
   private String searchLicense; // All lowercase, no spaces
   
   //------------------------------------------------------------------------//
   // Default blank constructor                                              //
   //------------------------------------------------------------------------//
   public Car() {
      this.make = "";
      this.model = "";
      this.year = 0;
      this.color = "";
      this.license = "";
      this.searchLicense = "";
      
      /* Stub code
      System.out.println("Car: Default constructor called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Constructor with given data                                            //
   //------------------------------------------------------------------------//
   public Car(String make, String model, int year, String color,
         String license) {
      this.make = make;
      this.model = model;
      this.year = year;
      this.color = color;
      this.license = license;
      this.searchLicense = 
            license.toLowerCase().replace("-", "").replace(" ","");
      
      /* Stub code
      System.out.println("Car: Given data constructor called..."); */
   }
   
   //------------------------------------------------------------------------//
   // Print out all the data of this car                                     //
   //------------------------------------------------------------------------//
   @Override
   public String toString() {
      return make + " " + model + " " + year + " " + color + " " + license 
            /*+ " " + searchLicense*/;
      
      /* Stub code
      System.out.println("toString() in Car called...");
      return "FIXME"; */
   }
   
   //------------------------------------------------------------------------//
   // Compare cars based on the lexicographic value of the make              //
   // (ignoring case)                                                        //
   //------------------------------------------------------------------------//
   @Override
   public int compareTo(Car secondCar) {
      return make.toLowerCase().compareTo(secondCar.getMake().toLowerCase());
      
      /* Stub code
      System.out.println("Car: compareTo() called...");
      return -1; */
   }
   
   //------------------------------------------------------------------------//
   // Create a deep copy of the current car                                  //
   //------------------------------------------------------------------------//
   @Override
   public Car clone() {
      return new Car(make, model, year, color, license);
      //return new Car();
   }
   
   //------------------------------------------------------------------------//
   // Return the car's make                                                  //
   //------------------------------------------------------------------------//
   public String getMake() {
      return make;
      
      /* Stub code
      System.out.println("getMake() called...");
      return "FIXME"; */
   }
   
   //------------------------------------------------------------------------//
   // Return the car's model                                                 //
   //------------------------------------------------------------------------//
   public String getModel() {
      return model;
      
      /* Stub code
      System.out.println("getModel() called...");
      return "FIXME"; */
   }
   
   //------------------------------------------------------------------------//
   // Return the car's year                                                  //
   //------------------------------------------------------------------------//   
   public int getYear() {
      return year;
      
      /* Stub code
      System.out.println("getYear() called...");
      return -1; */
   }
   
   //------------------------------------------------------------------------//
   // Return the car's color                                                 //
   //------------------------------------------------------------------------//
   public String getColor() {
      return color;
      
      /* Stub code
      System.out.println("getColor() called..");
      return "FIXME"; */
   }
   
   //------------------------------------------------------------------------//
   // Return the car's license                                               //
   //------------------------------------------------------------------------//
   public String getLicense() {
      return license;
      
      /* Stub code
      System.out.println("getLicense() called...");
      return "FIXME"; */
   }
  
   //------------------------------------------------------------------------//
   // Return the car's simplyfied license                                    //
   //------------------------------------------------------------------------//
   public String getSearchLicense() {
      return searchLicense;
      
      /* Stub code
      System.out.println("getSearchLicense() called...");
      return "FIXME"; */
   }
   
   //------------------------------------------------------------------------//
}