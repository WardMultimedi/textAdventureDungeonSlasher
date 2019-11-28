package be.multimedi.textAdventureDungeonSlasher;

import be.multimedi.textAdventureDungeonSlasher.gameLogic.DungeonSlasher;
import be.multimedi.textAdventureDungeonSlasher.tools.ConsoleTool;

/**
 * The DungeonSlasher text adventure class, start point
 */
public class App {

   /**
    * The DungeonSlasher text adventure main(), start point
    *
    * @param args Not used
    */
   public static void main(String[] args) {
      //System.out.println("Java version: " + System.getProperty("java.version"));
      DungeonSlasher app = new DungeonSlasher();
      do {
         app.startGame();
      } while (ConsoleTool.askUserYesNoQuestion(
              "Would you like to play again? (y/n default:no): ",
              true, false));
   }
}