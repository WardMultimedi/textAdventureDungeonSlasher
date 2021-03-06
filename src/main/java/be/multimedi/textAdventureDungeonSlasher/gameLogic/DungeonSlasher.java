package be.multimedi.textAdventureDungeonSlasher.gameLogic;

import be.multimedi.textAdventureDungeonSlasher.tools.ConsoleColors;
import be.multimedi.textAdventureDungeonSlasher.tools.ConsoleInputTool;
import be.multimedi.textAdventureDungeonSlasher.tools.ConsoleOutputTool;

import java.util.Random;

public class DungeonSlasher {
   /**
    * The DungeonSlasher text adventure start instanceMethod(), start point
    */
   public void startGame() throws InterruptedException {
      // System objects
      Random rand = new Random();

      // Player variables
      Player player = new Player();

      // Game variables
      String[] enemyTypes = {"Skeleton", "Zombie", "Warrior", "Giant snake",
              "Assasin", "Spider", "Minotour", "Dark templar", "Goblin", "Troll"};
      int enemyMaxHealth = 60;
      int enemyMinHealth = 0;
      Enemy enemy = null;
      int healthPotionDropChance = 50; // percentage

      // Start of the game
      System.out.println("\n\n");
      System.out.println("Welcome to Dungeon slasher!");
      System.out.println("~=========================~");
      Thread.sleep(500);
      System.out.println("\nAfter days of traveling through the woods, you arrive at a clearing.");
      System.out.println("You enter something that looks like the messy ruins of a giant graveyard.");
      System.out.println("In the center is something resembling the remains of a chapel.");
      System.out.println("And next to it, behind some bushes is a stairway down in the ground.");
      System.out.print("It suddenly starts ");
      ConsoleOutputTool.printInColorAndReset( "raining", ConsoleColors.FG_BLUE);
      System.out.println(" hard, you look around but see no shelter nearby.");
      System.out.println("You inspect the structure fast, it seems stable, and you go down the stairs.\n");
      ConsoleInputTool.askPressEnterToContinue();
      boolean running = true;

      GAME_LOOP:
      while (running) {
         // new enemy/round
         player.incrementEnemiesEncountered();
         ConsoleOutputTool.printlnInColorAndReset("##|==========> " + player.getEnemiesEncountered(), ConsoleColors.FG_RED);
         //System.out.println("##|==========> " + player.getEnemiesEncountered());
         boolean specialRound = player.getEnemiesEncountered() % 20 == 0;
         int difficulty = player.getEnemiesEncountered() / 20;
         if (specialRound) {
            enemy = new Enemy("Dragon",
                    100 * difficulty * 1 + rand.nextInt(enemyMaxHealth), 50);
            //increase difficulty of next enemy's
            enemyMaxHealth = 60 + 20 * difficulty;
            enemyMinHealth = 20 * difficulty;
         } else {
            enemy = new Enemy(enemyTypes[rand.nextInt(enemyTypes.length)],
                    enemyMinHealth + 1 + rand.nextInt(enemyMaxHealth), 25);
         }
         System.out.println("\t# " + enemy.getType() + " has appeared! #\n");

         FIGHT_LOOP:
         while (enemy.getHealth() > 0) {
            // do battle
            Thread.sleep(500);

            System.out.println("\tYour HP: " + player.getHealth());
            System.out.println("\t" + enemy.getType() + "'s HP: " + enemy.getHealth());
            System.out.println("\n\tWhat would you like to do?");
            System.out.println("\t1. Attack");
            System.out.println("\t2. Drink health potion (" + player.getInventoryPotionCount() + ")");
            if (!specialRound)
               System.out.println("\t3. Run!");

            int choice = ConsoleInputTool.askUserInteger(
                    "\t** Your choice: ", 1, specialRound ? 2 : 3);
            switch (choice) {
               case 1:
                  // Attack
                  int playerDoDamage = player.getRandomDamage();
                  int enemyDoDamage = enemy.getRandomDamage();

                  enemy.addHealth(-playerDoDamage);
                  player.addHealth(-enemyDoDamage);

                  System.out.println("\t> You strike the " + enemy.getType() + " for " + playerDoDamage + " damage.");
                  System.out.println("\t> You recieve " + enemyDoDamage + " in retaliation!");

                  if (player.getHealth() < 1) {
                     System.out.println("\t> You have taken too much damage, you are too weak to go on!");
                     System.out.println("\t> You run away from the " + enemy.getType() + ".\n");
                     break FIGHT_LOOP;//GAME_LOOP;
                  }
                  break;
               case 2:
                  // Take Health potion
                  if (player.drinkHealthPotion()) {
                     System.out.println("\t> You drink a health potion, healing yourself for " + player.getHealthPotionHealAmount() + ".");
                     System.out.println("\t> You now have " + player.getHealth() + " HP.");
                     System.out.println("\t> You have " + player.getInventoryPotionCount() + " health potions left.\n");
                  } else {
                     System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!\n");
                  }
                  break;
               case 3:
                  // Run
                  System.out.println("\t> You run away from the " + enemy.getType() + "!\n");
                  continue GAME_LOOP;
               default:
                  System.out.println("Error: Invalid input! Number expected between 1 and 3.");
            }
         }// --> FIGHT_LOOP // while (enemy.getHealth() > 0)

         //Fight result
         if (player.getHealth() < 1) {
            // flee from battles
            System.out.println("> You limp out of the dungeon, weak from battle.");
            if (enemy.getHealth() > 0 && rand.nextInt(100) < 50) {
               System.out.println("> The " + enemy.getType() + " attacks you from behind!");
               System.out.println("> The dark dungeon will be your final resting place.");
            } else {
               System.out.println("> With some extreme luck you reach safety, outside in the rain.");
            }
            break;
         }
         ConsoleOutputTool.setPrintColor(ConsoleColors.FG_GREEN);
         ConsoleOutputTool.printAlert(null); //System.out.println("-!!------------------------!!-");
         if (enemy.getHealth() < 1) {
            ConsoleOutputTool.printAlert(enemy.getType() + " was defeated!");
            player.incrementBattlesWon();
            int goldWon = (1 + rand.nextInt(10)) * 10;
            if (specialRound)
               goldWon += 10_000;
            ConsoleOutputTool.printAlert("gold found: " + goldWon + " gold coins.");
            player.addGold(goldWon);
            ConsoleOutputTool.printAlert("You now have " + player.getGold() + " gold coins.");
         }
         ConsoleOutputTool.printAlert("You have " + player.getHealth() + " HP left.");
         if (enemy.getHealth() < 1 && (rand.nextInt(100) < healthPotionDropChance || specialRound)) {
            player.incrementPotions();
            ConsoleOutputTool.printAlert("The " + enemy.getType() + " dropped a health potion!");
            ConsoleOutputTool.printAlert("You now have " + player.getInventoryPotionCount() + " health potion(s).");
         }
         ConsoleOutputTool.printAlert(null); //System.out.println("-!!------------------------!!-");
         ConsoleOutputTool.resetPrintColor();

         // Future action
         System.out.println("What would you like to do now?");
         System.out.println("1. Continue fighting");
         System.out.println("2. Exit dungeon");

         int choice = ConsoleInputTool.askUserInteger("** Your choice: ", 1, 2);
         switch (choice) {
            case 1:
               System.out.println("\n> You continue on your adventure!");
               break;
            case 2:
               System.out.println("\n> You exit the dungeon, successful from your adventures!");
               break GAME_LOOP;
         }
      }// --> GAME_LOOP // while(running)
      System.out.println("Statisics");
      System.out.println("=========");
      ConsoleOutputTool.printlnInColorAndReset("> Gold coins: " + player.getGold(), ConsoleColors.FG_YELLOW);
      System.out.println("> Enemies encountered: " + player.getEnemiesEncountered());
      System.out.println("> Enemies defeated: " + player.getBattlesWon());
      System.out.println("> Health remaining: " + player.getHealth());
      System.out.println("> Health potions remaining: " + player.getInventoryPotionCount());
      System.out.println("> Health potions used: " + player.getInventoryPotionUsed());
      System.out.println("#######################");
      System.out.println("# THANKS FOR PLAYING! #");
      System.out.println("#######################");
   }
}
