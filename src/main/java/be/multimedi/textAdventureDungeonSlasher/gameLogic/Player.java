package be.multimedi.textAdventureDungeonSlasher.gameLogic;

import java.util.Random;

/**
 * DungeonSlasher Enemy class
 */
public class Player {
   //# variables
   private int health = 100;
   private int maxDamage = 50;

   private int healthPotionHealAmount = 30;
   private int inventoryPotionCount = 3;
   private int inventoryPotionUsed = 0;
   private int battlesWon = 0;
   private int enemiesEncountered = 0;
   private int gold = 0;

   //# actions
   public boolean drinkHealthPotion() {
      if (inventoryPotionCount > 0) {
         inventoryPotionCount--;
         health += healthPotionHealAmount;
         inventoryPotionUsed++;
         return true; //--> ok
      }
      return false; //--> not ok
   }

   public void addHealth(int hp) {
      health += hp;
   }

   public void incrementEnemiesEncountered() {
      enemiesEncountered++;
   }

   public void incrementBattlesWon() {
      battlesWon++;
   }

   public void incrementPotions() {
      inventoryPotionCount++;
   }

   public void addGold(int gold) {
      this.gold += gold;
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

   public int getHealthPotionHealAmount() {
      return healthPotionHealAmount;
   }

   public int getInventoryPotionUsed() {
      return inventoryPotionUsed;
   }

   public int getGold() {
      return gold;
   }
}
