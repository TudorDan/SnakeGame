package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;

import java.util.Random;

import javafx.geometry.Point2D;

public class SecondEnemy extends Enemy implements Animatable, Interactable {
    private Point2D heading;
    private static Random rnd = new Random();

    private double direction;
    private int speed;

    public SecondEnemy() {
        super(-20);

        setImage(Globals.getInstance().getImage("Bird"));
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        direction = 0;
        setRotate(direction);

        speed = 3;

    }

    @Override
    public void step() {
        if (direction < 360) {
            direction++;
        } else {
            direction = 0;
        }
        heading = Utils.directionToVector(direction, speed);

        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof SnakeHead) {
            destroy();
        }
        if (entity instanceof SnakeBody) {
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
