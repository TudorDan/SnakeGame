package com.codecool.snake.entities.snakes;

import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.powerups.*;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import javax.swing.text.html.parser.Entity;
import java.util.concurrent.atomic.AtomicInteger;


public class SnakeHead extends GameEntity implements Interactable {
    private static final float turnRate = 2;
    private Snake snake;

    public SnakeHead(Snake snake, Point2D position) {
        this.snake = snake;
        setImage(Globals.getInstance().getImage("SnakeHead"));
        setPosition(position);
    }

    public void updateRotation(SnakeControl turnDirection, float speed) {
        double headRotation = getRotate();

        if (turnDirection.equals(SnakeControl.TURN_LEFT)) {
            headRotation = headRotation - turnRate;
        }
        if (turnDirection.equals(SnakeControl.TURN_RIGHT)) {
            headRotation = headRotation + turnRate;
        }

        // set rotation and position
        setRotate(headRotation);
        Point2D heading = Utils.directionToVector(headRotation, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        System.out.println(this.getMessage());

        if(entity instanceof Enemy){
//            Decrease snake health with a value equal to the damage produced by the enemy
            snake.changeHealth(((Enemy) entity).getDamage());
            snake.getHealthBar().changeHealthBar();
//            Spawn life if health is below 30
            if (snake.getHealth() < 30) {
                new Life(10);
            }
        }

        if(entity instanceof Food){
//            Increase snake size
            snake.addPart(1);
//            Spawn new food after eating
            new Food();
//            Update score
            snake.changeScore(1);
        }

        if (entity instanceof Life){
//            Restore snake life to 100%
            snake.restoreHealth();
        }

        if (entity instanceof SpeedPotion) {
            Game.speedPotion = true;
//            Double the snake default speed
            snake.changeSpeed(2);
//            The effect lasts for 10 seconds
            AtomicInteger spawnedTimer = new AtomicInteger();
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                spawnedTimer.addAndGet(1);
                if (spawnedTimer.intValue() == 10) {
                    snake.changeSpeed(-1);
                    Game.speedPotion = false;
                }
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();

        }

        if (entity instanceof GoldChest) {
//            Add 100 points to snake score
            snake.changeScore(100);
        }

        if (entity instanceof FireBall) {
//            TODO
        }

        if (entity instanceof PowerBoom) {
            Game.powerBoom = true;
//            The effect lasts for 3 seconds
            AtomicInteger spawnedTimer = new AtomicInteger();
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                spawnedTimer.addAndGet(1);
                if (spawnedTimer.intValue() == 3) {
                    Game.powerBoom = false;
                }
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }
    }

    @Override
    public String getMessage() {
        return "Snake head collision with game entity";
    }
}
