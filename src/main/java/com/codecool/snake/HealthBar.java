package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.Snake;

public class HealthBar extends GameEntity {
    private final int Max_Health = 100;
    private int health = 100;
    private Snake snake;

    public HealthBar() {
        setImage(Globals.getInstance().getImage("Health100"));
        setX(10);
        setY(30);
    }

    public void changeHealthBar() {
        setImage(Globals.getInstance().getImage("Health" + getHealth()));
    }

    public Snake getSnake() {
        return snake;
    }

    public int getHealth() {
        return health;
    }

    public void changeHealth(int health) {
        this.health += health;
    }
}