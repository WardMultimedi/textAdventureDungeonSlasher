package be.multimedi.textAdventureDungeonSlasher;

import java.util.Random;

public class Enemy {
    //# variables
    private int health;
    private int maxDamage;

    private String enemyType;

    //# constructor(s)
    public Enemy(String enemyType, int maxHealth, int maxDamage) {
        this.enemyType = enemyType;
        health = new Random().nextInt(maxHealth) + 1;
        this.maxDamage = maxDamage;
    }

    //# actions
    public int getRandomDamage() {
        return new Random().nextInt(maxDamage);
    }

    //# getters
    public int getHealth() {
        return health;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public String getEnemyType() {
        return enemyType;
    }
}
