package com.codecool.snake.entities.enemies;

import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.entities.snakes.Shooting;
import javafx.geometry.Point2D;

import java.util.Random;

public class ThirdEnemy extends Enemy implements Animatable, Interactable {
    private Point2D heading;
    private static Random rnd = new Random();

    private double direction;
    private final int speed;
    private int temp;
    private int hitCount = 4;

    public ThirdEnemy() {
        super(-10);

        setImage(Globals.getInstance().getImage("Boss"));
        setX(rnd.nextDouble() * (Globals.WINDOW_WIDTH - 20));
        setY(rnd.nextDouble() * (Globals.WINDOW_HEIGHT - 20));

        // ensure enemy is not spawn on snake
        Snake blueSnake = Globals.getInstance().game.getBlueSnake();
        Snake redSnake = Globals.getInstance().game.getRedSnake();
        while(blueSnake.isTouchedBy(this) && redSnake.isTouchedBy(this)) {
            setX(rnd.nextDouble() * (Globals.WINDOW_WIDTH - 20));
            setY(rnd.nextDouble() * (Globals.WINDOW_HEIGHT - 20));
        }

        direction = Globals.WINDOW_HEIGHT;
        setRotate(direction);

        speed = 2;
        temp = 1;
    }

    @Override
    public void step() {
        if (temp < 70) {
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
        if (entity instanceof SnakeHead || entity instanceof Shooting) {
            if (hitCount == 0) {
                if (entity instanceof SnakeHead || entity instanceof Shooting) {
                    destroy();
                    if (entity instanceof Shooting) {
                        ((Shooting) entity).getSnake().changeScore(40);
                    }
                }
            } else {
                if (!Game.fireBall) {
                    hitCount --;
                } else {
                    hitCount -= 2;
                }
            }
        }
    }
    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
