package be.multimedi.textAdventureDungeonSlasher;

import java.util.Random;

public class Player {
    //# variables
    private int health = 100;
    private int maxDamage = 50;

    private int healthPotionHealAmount = 30;
    private int inventoryPotionCount = 3;
    private int battlesWon = 0;
    private int enemiesEncountered = 0;

    //# actions
    public boolean drinkHealthPotion() {
        if (inventoryPotionCount > 0) {
            inventoryPotionCount--;
            health += healthPotionHealAmount;
            return true; //--> ok
        }
        return false; //--> not ok
    }

    public void incrementEnemiesEncountered() {
        enemiesEncountered++;
    }

    public void incrementBattlesWon() {
        battlesWon++;
    }

    //# getters
    public int getHealth() {
        return health;
    }

    public int getInventoryPotionCount() {
        return inventoryPotionCount;
    }

    public int getBattlesWon() {
        return battlesWon;
    }

    public int getEnemiesEncountered() {
        return enemiesEncountered;
    }

    public int getRandomDamage() {
        return new Random().nextInt(maxDamage);
    }

    public int getMaxDamage() {
        return maxDamage;
    }
}
