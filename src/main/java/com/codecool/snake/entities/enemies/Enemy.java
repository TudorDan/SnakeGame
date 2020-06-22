package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class Enemy extends GameEntity{
    private final int damage;

    private List<Enemy> enemies = new ArrayList<>();

    public Enemy(int damage) {
        this.damage = damage;
        enemies.add(this);
    }

    public int getDamage() {
        return damage;
    }

    public void clearAll() {
        for (Enemy enemy : enemies) {
            enemy.destroy();
        }
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
}
