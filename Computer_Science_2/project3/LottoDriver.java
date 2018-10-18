/** This is the driver for the LotteryGame class
    
    This class gets a file and a number of games to play from the user,
    then it runs each game calling the LotteryGame class file, and then
    finally prints out the output.
    
    Stephen Bapple
    
    September 2015
    
    CS 2, Fall 2015

*/

import java.io.*;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;

public class LottoDriver { 
   private PrintWriter outputFile;
   private int[] averages;
   private DecimalFormat addCommas = new DecimalFormat("###,###,###,###");
   
   public static void main(String [] args) throws IOException {
      int[][] gameStats;
      int gamesToPlay;
      PrintWriter outputFile;
      LotteryGame game;
      
      // Initialize the class and LotteryGame objects
      LottoDriver program = new LottoDriver();
      game = new LotteryGame();
      
      // Get the number of games to play from the user
      gamesToPlay = program.getUserData();
      
      // Initialize the stats array
      gameStats = new int[gamesToPlay][5];
      
      /* Just testing 1: *//* game.getNumbers(); Tests passed*/
      /* Just testing 2: *//* game.generateNumbers(); Tests passed*/
      
      // Print the header
      program.printHeader();
      
      for(int i = 0; i < gamesToPlay; ++ i) {
         game.playGame();
         gameStats[i]= game.getStats();
         program.printGame(i, gameStats, game);
      }
      
      program.averageGames(gamesToPlay, gameStats);
      program.printAverages(gamesToPlay);
      //outputFile.println(getAverages(game, gamesToPlay));
   }
   
   //------------------------------------------------------------------------//
   // Methods                                                                //
   //------------------------------------------------------------------------//
   // This method gets the file and number of games to play from the user    //
   //------------------------------------------------------------------------//
   public int getUserData() throws IOException {
      // Get the output file and games to play 
      outputFile = new PrintWriter(new File(
            JOptionPane.showInputDialog(
            "Please enter the name of the file to send the report to: "))); 
      
      return(Integer.parseInt(JOptionPane.showInputDialog(
         "Please enter the number of games to play: ")));
   }
   //------------------------------------------------------------------------//
   // This method prints the header                                          //
   //------------------------------------------------------------------------//
   public void printHeader() {
      outputFile.println("Lottery program." + "\r\n" + "Stephen Bapple" 
            + "CS 2, Fall 2015" + "\r\n");
      
      return;
   }
   
   //------------------------------------------------------------------------//
   // This method prints the stats for one game                              //
   //------------------------------------------------------------------------//
   public void printGame(int i, int[][] gameStats, LotteryGame game) {
      //System.out.println(gameStats[0][0]);
      outputFile.println("User's numbers: " + game.getUserNumbers() + "\r\n"
            + "\r\n"
            + "Stats for game " + (i + 1) + ":" + "\r\n" 
            + "Plays to win lotto: " + addCommas.format(gameStats[i][0]) 
            + "\r\n"
            + "Match 3 numbers: " + addCommas.format(gameStats[i][1]) + "\r\n"
            + "Match 4 numbers: " + addCommas.format(gameStats[i][2]) + "\r\n"
            + "Match 5 numbers: " + addCommas.format(gameStats[i][3]) + "\r\n"
            + "Net winnings: " + addCommas.format(gameStats[i][4]) + "\r\n");
      
      return;
   }
   
   //------------------------------------------------------------------------//
   // This method computes the averages of all the stats                     //
   //------------------------------------------------------------------------//
   public void averageGames(int gamesToPlay, int[][] gameStats) {
      int total;
      averages = new int[5];
      String writeLine = "";
      
      for(int i = 0; i < 5; ++i) { 
         total = 0;
         for(int j = 0; j < gamesToPlay; ++j) {
            //System.out.println(gamesToPlay + " " + i + " " + j);
            total += gameStats[j][i];
         }
         averages[i] = (int)Math.round(((double)total / gamesToPlay));
      }
      
      return;
   }
   
   //------------------------------------------------------------------------//
   // This method print the average stats to the file                        //
   //------------------------------------------------------------------------//
   public void printAverages(int gamesToPlay) {
      outputFile.println("Averages across the " + gamesToPlay
            + " games played:" + "\r\n"
            + "Average plays: " + averages[0] + "\r\n"
            + "Average match 3s: " + averages[1] + "\r\n"
            + "Average match 4s: " + averages[2] + "\r\n"
            + "Average match 5s: " + averages[3] + "\r\n"
            + "Average winnings: " + averages[4] + "\r\n");
      
      outputFile.close();
      
      return;
   }
   
   //------------------------------------------------------------------------//
}

