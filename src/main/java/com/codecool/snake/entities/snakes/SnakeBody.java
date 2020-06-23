package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.powerups.Life;
import javafx.geometry.Point2D;

import java.util.LinkedList;
import java.util.Queue;



public class SnakeBody extends GameEntity implements Interactable {
    private Queue<Point2D> history = new LinkedList<>();
    private static final int historySize = 10;
    private Snake snake = Globals.getInstance().game.getSnake();

    public SnakeBody(Point2D coord) {
        setImage(Globals.getInstance().getImage("SnakeBody"));
        setX(coord.getX());
        setY(coord.getY());

        for (int i = 0; i < historySize; i++) {
            history.add(coord);
        }
    }

    @Override
    public void setPosition(Point2D pos) {
        Point2D currentPos = history.poll(); // remove the oldest item from the history
        setX(currentPos.getX());
        setY(currentPos.getY());
        history.add(pos); // add the parent's current position to the beginning of the history
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof Enemy){
//            Decrease snake health with a value equal to the damage produced by the enemy
            snake.changeHealth(((Enemy) entity).getDamage());
            System.out.println("BODY contact");
//            Spawn life if health is below 30
            if (snake.getHealth() < 30) {
                new Life(10);
            }
        }
    }

    @Override
    public String getMessage() {
        return null;
    }
}