package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.Snake;

public class HealthBar extends GameEntity {
    private final int Max_Health = 100;
    private int health = 100;
    private Snake snake;

    public HealthBar(Snake snake) {
        setImage(Globals.getInstance().getImage("Health100"));
        setX(10);
        setY(30);
        this.snake = snake;
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
        changeHealthBar();
    }

    public void restoreHealth() {
        this.health = 100;
        changeHealthBar();
    }
}