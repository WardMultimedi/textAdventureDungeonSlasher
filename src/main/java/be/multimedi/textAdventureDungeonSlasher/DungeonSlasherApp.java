package be.multimedi.textAdventureDungeonSlasher;

import be.multimedi.textAdventureDungeonSlasher.gameLogic.DungeonSlasher;
import be.multimedi.textAdventureDungeonSlasher.tools.ConsoleInputTool;

/**
 * The DungeonSlasher text adventure class, start point
 */
public class DungeonSlasherApp {
   DungeonSlasher ds = new DungeonSlasher();
   /**
    * The DungeonSlasher text adventure main(), start point
    *
    * @param args Not used
    */
   public static void main(String[] args) {
      //System.out.println("Java version: " + System.getProperty("java.version"));
      DungeonSlasherApp app = new DungeonSlasherApp();
      app.startGame();

   }

   void startGame(){
      do {
         try {
            ds.startGame();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      } while (ConsoleInputTool.askUserYesNoQuestion(
              "Would you like to play again? (y/n default:no): ",
              true, false));
   }
}