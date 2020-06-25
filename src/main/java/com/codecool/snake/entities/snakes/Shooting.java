package com.codecool.snake.entities.snakes;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.enemies.SecondEnemy;
import com.codecool.snake.entities.enemies.ThirdEnemy;
import javafx.geometry.Point2D;


public class Shooting extends GameEntity implements Interactable, Animatable {

    private Point2D heading;
    private static final int SPEED = 5;
    private Snake snake;

    Shooting(Snake snake) {
        this.snake = snake;
        if (snake.getName().equals("Red")) {
            if (!snake.getFireBall()) {
                setImage(Globals.getInstance().getImage("FireBall"));
            } else {
                setImage(Globals.getInstance().getImage("FireBallBig"));
            }
            heading = Utils.directionToVector(snake.getHead().getRotate(), snake.getSpeed() + SPEED);
            setX(snake.getHead().getPosition().getX());
            setY(snake.getHead().getPosition().getY());
        }

        else {
            if (!snake.getFireBall()) {
                setImage(Globals.getInstance().getImage("IceBall"));
            } else {
                setImage(Globals.getInstance().getImage("IceBallBig"));
            }
            heading = Utils.directionToVector(snake.getHead().getRotate(), snake.getSpeed() + SPEED);
            setX(snake.getHead().getPosition().getX());
            setY(snake.getHead().getPosition().getY());
        }
    }

    public Snake getSnake() {
        return snake;
    }

    @Override
    public void step() {
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof Enemy) {
            destroy();
            System.out.println(getMessage());
        }
        if (entity instanceof SecondEnemy) {
            destroy();
            System.out.println(getMessage());
        }
    }

    @Override
    public String getMessage() {
        return null;
    }
}


















