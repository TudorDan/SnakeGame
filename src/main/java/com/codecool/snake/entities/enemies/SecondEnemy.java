package com.codecool.snake.entities.enemies;

import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.entities.snakes.Shooting;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.geometry.Point2D;



public class SecondEnemy extends Enemy implements Animatable, Interactable {

    private Point2D heading;
    private static Random rnd = new Random();
    private int hitCount = 2;

    public SecondEnemy() {
        super(-20);

        setImage(Globals.getInstance().getImage("Bird"));
        setX(rnd.nextDouble() * (Globals.WINDOW_WIDTH - 20));
        setY(rnd.nextDouble() * (Globals.WINDOW_HEIGHT - 20));

        // ensure enemy is not spawn on snake
        Snake blueSnake = Globals.getInstance().game.getBlueSnake();
        Snake redSnake = Globals.getInstance().game.getRedSnake();
        while(blueSnake.isTouchedBy(this) && redSnake.isTouchedBy(this)) {
            setX(rnd.nextDouble() * (Globals.WINDOW_WIDTH - 20));
            setY(rnd.nextDouble() * (Globals.WINDOW_HEIGHT - 20));
        }

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);

        int speed = 1;
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead || entity instanceof Shooting){
            if (hitCount <= 0) {
                if (entity instanceof SnakeHead || entity instanceof Shooting) {
                    destroy();
                    if (entity instanceof Shooting) {
                        ((Shooting) entity).getSnake().changeScore(20);
                    }
                }
            }
            else {
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
