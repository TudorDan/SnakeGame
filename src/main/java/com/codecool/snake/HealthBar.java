package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.Snake;

public class HealthBar extends GameEntity {
    private final int Max_Health = 100;
    private int health = 100;
    private Snake snake;

    public HealthBar(Snake snake) {
        this.snake = snake;
        switch (this.snake.getName()) {
            case "Red":
                setImage(Globals.getInstance().getImage("Health100Red"));
                setX(10);
                setY(30);
                break;
            case "Blue":
                setImage(Globals.getInstance().getImage("Health100Blue"));
                setX(10);
                setY(100);
                break;
        }
    }

    public void changeHealthBar() {
        setImage(Globals.getInstance().getImage("Health" + getHealth() + this.snake.getName()));
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

    public void setHealth(int health) {
        this.health = health;
        changeHealthBar();
    }
}