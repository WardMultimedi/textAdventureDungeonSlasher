package be.multimedi.textAdventureDungeonSlasher;

import java.util.Random;
import java.util.Scanner;

public class App {

    public static void main(String[] args){
        //System.out.println("Java version: " + System.getProperty("java.version"));
        App app = new App();
        String answer;
        do {
            app.startGame();
        }while(ConsoleTool.askUserYesNoQuestion(
                "Would you like to play again? (y/n default:no)",
                true, false));
    }

    public void startGame() {
        // System objects
        Random rand = new Random();

        // Player variables
        Player player = new Player();

        // Game variables
        String[] enemyTypes = { "Skeleton", "Zomie", "Warrior",
                "Assasin", "Spider", "Minotour", "Dark templar"};
        int enemyMaxHealth = 60;
        Enemy enemy = null;
        int healthPotionDropChance = 50; // percentage

        // Start of the game
        System.out.println("Welcome to Dungeon slasher!");
        System.out.println("~=========================~");
        System.out.println("\nAfter days of traveling through the woods, you arrive at a clearing.");
        System.out.println("You enter something that looks like the messy ruins of a giant graveyard.");
        System.out.println("In the center is something resembling the remains of a chapel.");
        System.out.println("And next to it, behind some bushes is a stairway down in the ground.");
        System.out.println("It suddenly starts raining hard, you look around but see no shelter nearby.");
        System.out.println("You inspect the structure fast, it seems stable, and you go down the stairs.\n");
        ConsoleTool.askPressEnterToContinue();
        boolean running = true;

        GAME_LOOP:
        while(running){
            // new enemy/round
            System.out.println("##|==========>");
            player.incrementEnemiesEncountered();
            if(player.getEnemiesEncountered()%20==0){
                enemy = new Enemy("Dragon", 100+ rand.nextInt(enemyMaxHealth), 50);
            }else{
                enemy = new Enemy(enemyTypes[rand.nextInt(enemyTypes.length)], 1+ rand.nextInt(enemyMaxHealth-1), 50);
            }
            System.out.println("\t# " + enemy.getType() + " has appeared! #\n");

            FIGHT_LOOP:
            while (enemy.getHealth()>0){
                // do battle

                System.out.println("\tYour HP: " + player.getHealth());
                System.out.println("\t" + enemy.getType() + "'s HP: " + enemy.getHealth());
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink health potion");
                System.out.println("\t3. Run!");

                int choice = ConsoleTool.askUserInputInteger(
                        "\t** Your choice: ", 1, 3);
                switch (choice){
                    case 1:
                        // Attack
                        int playerDoDamage = player.getRandomDamage();
                        int enemyDoDamage = enemy.getRandomDamage();

                        enemy.addHealth(-playerDoDamage);
                        player.addHealth(-enemyDoDamage);

                        System.out.println("\t> You strike the " + enemy.getType() + " for " + playerDoDamage + " damage.");
                        System.out.println("\t> You recieve " + enemyDoDamage + " in retaliation!");

                        if( player.getHealth()< 1 ){
                            System.out.println("\t> You have taken too much damage, you are too weak to go on!\n");
                            break GAME_LOOP;
                        }
                        break;
                    case 2:
                        // Health potion
                        if(player.drinkHealthPotion()){
                            System.out.println("\t> You drink a health potion, healing yourself for " + player.getHealthPotionHealAmount() + ".");
                            System.out.println("\t> You now have " + player.getHealth() + " HP.");
                            System.out.println("\t> You have " + player.getInventoryPotionCount() + " health potions left.\n");
                        }else{
                            System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!\n");
                        }
                        break;
                    case 3:
                        // Run
                        System.out.println("\t> You run away from the " + enemy.getType() + "!\n");
                        continue GAME_LOOP;
                        //break FIGHT_LOOP;
                    default:
                        System.out.println("Error: Invalid input! Number expected between 1 and 3.");
                }

            }// --> FIGHT_LOOP

            //Fight result
            if(player.getHealth() < 1) {
                System.out.println("You limp out of the dungeon, weak from battle.");
                break;
            }
            System.out.println("-!!------------------------!!-");
            if(enemy.getHealth()<1){
                System.out.println(" # " + enemy.getType() + " was defeated! #");
                player.incrementBattlesWon();
            }
            System.out.println(" # You have " + player.getHealth() + " HP left. #");
            if(enemy.getHealth()<1 && rand.nextInt(100) < healthPotionDropChance){
                player.incrementPotions();
                System.out.println(" # The " + enemy.getType() + " dropped a health potion! #");
                System.out.println(" # You now have " + player.getInventoryPotionCount() + " health potion(s). #");
            }
            System.out.println("-!!------------------------!!-");

            // Future action
            System.out.println("What would you like to do now?");
            System.out.println("1. Continue fighting");
            System.out.println("2. Exit dungeon");

            int choice = ConsoleTool.askUserInputInteger("** Your choice: ",1,2);
            switch (choice){
                case 1:
                    System.out.println("You continue on your adventure!");
                    break;
                case 2:
                    System.out.println("You exit the dungeon, successful from your adventures!");
                    break GAME_LOOP;
            }
        }// --> GAME_LOOP
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
