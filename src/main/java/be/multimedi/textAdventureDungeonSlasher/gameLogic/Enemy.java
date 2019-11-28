package be.multimedi.textAdventureDungeonSlasher.gameLogic;

import java.util.Random;

/**
 * DungeonSlasher Enemy class
 */
public class Enemy {
   //# variables
   private int health;
   private int maxDamage;

   private String type;

   //# constructor(s)
   public Enemy(String type, int health, int maxDamage) {
      this.type = type;
      this.health = health;
      this.maxDamage = maxDamage;
   }

   //# actions
   public int getRandomDamage() {
      return new Random().nextInt(maxDamage);
   }

   public void addHealth(int hp) {
      health += hp;
   }

   //# getters
   public int getHealth() {
      return health;
   }

   public int getMaxDamage() {
      return maxDamage;
   }

   public String getType() {
      return type;
   }
}
