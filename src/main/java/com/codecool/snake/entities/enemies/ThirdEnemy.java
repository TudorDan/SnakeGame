package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;

import java.util.Random;

public class ThirdEnemy extends Enemy implements Animatable, Interactable {
    private Point2D heading;
    private static Random rnd = new Random();

    private double direction;
    private final int speed;
    private int temp;

    public ThirdEnemy() {
        super(-20);

        setImage(Globals.getInstance().getImage("Spaceship"));
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        // ensure enemy is not spawn on snake
        Snake snake = Globals.getInstance().game.getSnake();
        while(snake.isTouchedBy(this)) {
            setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
            setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        }

        direction = Globals.WINDOW_HEIGHT;
        setRotate(direction);

        speed = 3;

        temp = 1;
    }

    @Override
    public void step() {
        if(temp < 100) {
            temp++;
        } else {
            temp = 1;
            direction = rnd.nextDouble() * 360;
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
//        if (entity instanceof SnakeBody) {
//            destroy();
//        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
