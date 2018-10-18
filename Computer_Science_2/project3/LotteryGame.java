/** This program holds all the data for the Lottery program
    It gets 6 numbers from the user, then it generates random 
    numbers and runs a game until the user wins.
    
    Stephen Bapple
    
    September 2015
    
    CS 2, Fall 2015
    
    Limitations:
      - Does not check user input for validity
      - Not very fancy output.
*/

import java.util.Scanner;
import java.io.*;
import javax.swing.JOptionPane;

public class LotteryGame {
   // Instance data - constants
   private final int MAX = 42;
   private final int MIN = 1;
   
   // Instance data - variables
   private int[] stats;          // Matches, and net winnings
   private int[] userNumbers;    // The user's numbers
   private int[] winningNumbers; // The winning numbers
   private int netWinnings;      // Net winnings after getting the jackpot
   private int gamesPlayed;      // Number of times it took to get the jackpot
   
   // Instance data - classes / objects
   private Scanner keyboard;
   
   //------------------------------------------------------------------------//
   // Public methods                                                         //
   //------------------------------------------------------------------------//
   // Get the numbers from the user                                          //
   //------------------------------------------------------------------------//
   public void getNumbers() {
      String[] input = new String[6]; 
   
      // Get the numbers from the user and split them into tokens
      input[0] = JOptionPane.showInputDialog("Please input 6 unique numbers "
         + "from 1 to 42:");
      input = input[0].split(" +");
      userNumbers = new int[input.length];
      
      // Assign them to the class field userNumbers
      for(int i = 0; i < input.length; ++i) {
         userNumbers[i] = Integer.parseInt(input[i]);
      }
      
      /* Testing correctness 1:
      for(int i = 0; i < userNumbers.length; ++i) {
         System.out.println(userNumbers[i]);
      } Tests passed */
      
      return;
   }
   
   //------------------------------------------------------------------------//
   // Play the lottery                                                       //
   //------------------------------------------------------------------------//
   public void playGame() {
      stats = new int[5];
      boolean wonJackpot;
      int matches;
      gamesPlayed = 0;
      netWinnings = 0;
      wonJackpot = false;
      
      // Get the numbers to play from the user      
      getNumbers();
         
      // Play until the jackpot is won
      while(!wonJackpot) {
         //System.out.println("Entered while loop");
         ++gamesPlayed;
         generateNumbers();
         matches = testNumbers();
         getNetWinnings(matches);
         //System.out.println(matches);
            
         if(matches == 6) {
            //System.out.println("User won jackpot");
            wonJackpot = true;
         }
      }
      
      /* save the statistics
         index is:
            0: games it took to win jackpot
            1: times matched 3
            2: times matched 4
            3: times matched 5
            4: net winnings  
      */ 
      stats[0] = gamesPlayed;
      stats[4] = netWinnings;
      
      
      return;
   }
   
   //------------------------------------------------------------------------//
   // This method generates 6 unique numbers                                 //
   //------------------------------------------------------------------------//
   public int[] generateNumbers() {
      int randomInt;
      winningNumbers = new int[6];
      boolean hasDuplicate;
      
      // The first number will always be valid
      randomInt = ((int)(Math.random() * (MAX - MIN + 1) + MIN));
      winningNumbers[0] = randomInt;
      
      for(int i = 1; i < winningNumbers.length; ++i) {
         hasDuplicate = true; // Assume number has a duplicate
         randomInt = ((int)(Math.random() * (MAX - MIN + 1) + MIN));
         
         // While number tests false keep looping
         while(hasDuplicate) {
            for(int j = 0; j < i; j++) {
               // If number is not unique get a new one and reset the for loop
               if(randomInt == winningNumbers[j]) {
                  randomInt = ((int)(Math.random() * (MAX - MIN + 1) + MIN));
                  hasDuplicate = true;
                  break;
               }
               else {
                  hasDuplicate = false;
               }
            }
         }
         // If number is unique save it
         winningNumbers[i] = randomInt;
      }
      
      /* Testing correctness 2: 
      for(int i = 0; i < winningNumbers.length; ++i) {
         System.out.println(winningNumbers[i]);
      } Tests passed */
      
      return(winningNumbers);
   }
   
   //------------------------------------------------------------------------//
   // This method tests how many of the users numbers match the winning      //
   // numbers, and returns how many matches there were                       //
   //------------------------------------------------------------------------//
   public int testNumbers() {
      int matches = 0;
      /*System.out.println("" + userNumbers[0] + " "
            + userNumbers[1] + " "
            + userNumbers[2] + " "
            + userNumbers[3] + " "
            + userNumbers[4] + " "
            + userNumbers[5]);
      System.out.println("" + winningNumbers[0] + " "
            + winningNumbers[1] + " "
            + winningNumbers[2] + " "
            + winningNumbers[3] + " "
            + winningNumbers[4] + " "
            + winningNumbers[5]); */
      for(int i = 0; i < winningNumbers.length; ++i) {
         for(int j = 0; j < userNumbers.length; ++j) {
            if(winningNumbers[i] == userNumbers[j])
               ++matches;
            
         }
      }
      
      return(matches);
   }
   
   //------------------------------------------------------------------------//
   // This method checks how much the player won                             //
   //------------------------------------------------------------------------//
   public void getNetWinnings(int matches) {
      switch(matches) {
         case 3:
            netWinnings += 10;
            ++stats[1];
            break;
            
         case 4:
            netWinnings += 50;
            ++stats[2];
            break;
         
         case 5:
            netWinnings += 1000;
            ++stats[3];
            break;
         
         case 6:
            netWinnings += 2000000; // Jackpot $2,000,000
            break;
             
      }
      
      netWinnings -= 1; // One dollar to play
      
      return;
   }
   
   //------------------------------------------------------------------------//
   // This method returns the stats for one game                             //
   //------------------------------------------------------------------------//
   public int[] getStats() {
      return(stats);
   }
   //------------------------------------------------------------------------//
   // This method returns the user's numbers for one game                    //
   //------------------------------------------------------------------------//
   public String getUserNumbers() {
      String tempString;
      
      tempString = "" + userNumbers[0];
      
      for(int i = 1; i < userNumbers.length; ++i) {
         tempString = tempString + " " + userNumbers[i];
      } 
      return(tempString);
   }
   
   //------------------------------------------------------------------------//
   // Accessors - Obsolete                                                   //
   //------------------------------------------------------------------------//
   // Get the number of games it took to get the jackpot                     //
   //------------------------------------------------------------------------//
   /*
   public int getGamesPlayed() {
      return(stats[0]);
   }
   
   //------------------------------------------------------------------------//
   // Get the number of times user numbers matched the specified amount      //
   //------------------------------------------------------------------------//
   public int getMatches(int amountMatched) {
      switch(amountMatched) {
         case 3:
            return(stats[1]);
         case 4:
            return(stats[2]);
         case 5:
            return(stats[3]);
      }
      return(-1); // -1 will never be valid data
   }
   
   //------------------------------------------------------------------------//
   // Get the net winnings                                                   //
   //------------------------------------------------------------------------//
   public int getWinnings() {
      return(stats[4]);
   }
   
   //------------------------------------------------------------------------//
   // Get the average of a specified stat                                    //
   //------------------------------------------------------------------------//
   // Moved to driver
   public int getAverage(String whichAverage) {
      double total = 0;
      int j = 0;
      
      switch(whichAverage) {
         case "games":
           j = 0;
           break;
         case "match3":
            j = 1;
            break;
         case "match4":
            j = 2;
            break;
         case "match5":
            j = 3;
            break;
         case "winnings":
            j = 4;
            break;
      }
      
      //System.out.println(stats[0].length);
      for(int i = 0; i < gamesToPlay; ++i) {
         total += stats[i][j];
      }
      
      return((int)(Math.round((total / gamesToPlay)))); 
   }
   */
   //------------------------------------------------------------------------//
   
}

