/** Driver class for the Soundex program.
    
    This program simply creates an object of type JavaSoundex and calls the
    convertFile() method. See JavaSoundex for its explanation
    
    September 2015
    
    CS 2, Fall 2015

*/

import java.io.*;

public class SoundexDriver {

   public static void main(String [] args)throws IOException {
      JavaSoundex program = new JavaSoundex();
      program.convertFile();
      
   }
}

