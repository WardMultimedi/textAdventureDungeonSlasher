package be.multimedi.textAdventureDunguonSlasher;

import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("Java version: " + System.getProperty("java.version"));
//        System.out.println("Java runtime version: " + System.getProperty("java.runtime.version"));

        // System objects
        Scanner keyb = new Scanner(System.in);
        Random rand = new Random();

        // Player variables
        int playerHealth = 100;
        int playerAttackDamage = 50;
        int playerNumHealthPotions = 3;

        // Game variables
        String[] enemyTypes = { "Skeleton", "Zomie", "Warrior", "Assasin"};
        int maxEnemyHealth = 75;
        int enemyAttackDamage = 25;

        int healthPotionHealAmount = 30;
        int healthPotionDropChance = 50; // percentage
        int encounters = 0;
        int battlesWon = 0;

        // Start of the game
        System.out.println("Welcome to Dungeon slasher!");
        System.out.println("~=========================~");
        boolean running = true;

        GAME_LOOP:
        while(running){
            // new enemy/round
            System.out.println("##|==========>");
            encounters++;
            int enemyHealth = rand.nextInt(maxEnemyHealth);
            String enemyType =  enemyTypes[rand.nextInt( enemyTypes.length)];
            System.out.println("\t# " + enemyType + " has appeared! #\n");

            FIGHT_LOOP:
            while (enemyHealth>0){
                // do battle

                System.out.println("\tYour HP: " + playerHealth);
                System.out.println("\t" + enemyType + "'s HP: " + enemyHealth);
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink health potion");
                System.out.println("\t3. Run!");

                System.out.print("\t** Your choice: ");
                String input = keyb.nextLine();
                int inputValue = 0;
                try {
                    inputValue = Integer.parseInt(input);
                }catch (Exception e){
                    System.out.println("Error: Number expected between 1 and 3");
                }
                switch (inputValue){
                    case 1:
                        // Attack
                        int damageDealt = rand.nextInt(playerAttackDamage);
                        int damageTaken = rand.nextInt(enemyAttackDamage);

                        enemyHealth -= damageDealt;
                        playerHealth -= damageTaken;

                        System.out.println("\t> You strike the " + enemyType + " for " + damageDealt + " damage.");
                        System.out.println("\t> You recieve " + damageTaken + " in retaliation!");

                        if( playerHealth < 1 ){
                            System.out.println("\t> You have taken too much damage, you are too weak to go on!\n");
                            break GAME_LOOP;
                        }
                        break;
                    case 2:
                        // Health potion
                        if(playerNumHealthPotions > 0){
                            playerHealth += healthPotionHealAmount;
                            playerNumHealthPotions--;
                            System.out.println("\t> You drink a health potion, healing yourself for " + healthPotionHealAmount + ".");
                            System.out.println("\t> You now have " + playerHealth + " HP.");
                            System.out.println("\t> You have " + playerNumHealthPotions + " health potions left.\n");
                        }else{
                            System.out.println("\t> You have no health potions left! Defeat enemies for a chance to get one!\n");
                        }
                        break;
                    case 3:
                        // Run
                        System.out.println("\t> You run away from the " + enemyType + "!\n");
                        continue GAME_LOOP;
                        //break FIGHT_LOOP;
                    default:
                        System.out.println("Error: Invalid input! Number expected between 1 and 3.");
                }

            }// --> FIGHT_LOOP

            //Fight result
            if(playerHealth < 1) {
                System.out.println("You limp out of the dungeon, weak from battle.");
                break;
            }
            System.out.println("-!!------------------------!!-");
            if(enemyHealth<1){
                System.out.println(" # " + enemyType + " was defeated! #");
                battlesWon++;
            }
            System.out.println(" # You have " + playerHealth + " HP left. #");
            if(enemyHealth<1 && rand.nextInt(100) < healthPotionDropChance){
                playerNumHealthPotions++;
                System.out.println(" # The " + enemyType + " dropped a health potion! #");
                System.out.println(" # You now have " + playerNumHealthPotions + " health potion(s). #");
            }
            System.out.println("-!!------------------------!!-");

            // Future action
            System.out.println("What would you like to do now?");
            System.out.println("1. Continue fighting");
            System.out.println("2. Exit dungeon");

            System.out.print("** Your choice: ");
            String input = keyb.nextLine();

            while(!input.equals("1") && !input.equals("2")){
                System.out.println("Error: Invalid input! Number expected between 1 and 2.");
                System.out.print("** Your choice: ");
                input = keyb.nextLine();
            }
            switch (input){
                case "1":
                    System.out.println("You continue on your adventure!");
                    break;
                case "2":
                    System.out.println("You exit the dungeon, successful from your adventures!");
                    break GAME_LOOP;
            }
        }// --> GAME_LOOP
        System.out.println("Enemies encountered: " + encounters);
        System.out.println("Enemies defeated: " + battlesWon);
        System.out.println("#######################");
        System.out.println("# THANKS FOR PLAYING! #");
        System.out.println("#######################");
    }
}
