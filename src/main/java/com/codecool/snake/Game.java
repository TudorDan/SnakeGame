package com.codecool.snake;

import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.*;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;


public class Game extends Pane {
    private Snake snake = null;
    private GameTimer gameTimer = new GameTimer();
//    Shady type of integer idk what this is but normal int won't work so who cares
    AtomicInteger timer = new AtomicInteger();

    private static final int speedPotionSpawnDuration = 10;
    private static final int goldChestSpawnDuration = 15;
    private static final int fireBallSpawnDuration = 8;
    private static final int powerBoomSpawnDuration = 5;


    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();
    }

    public void init() {
        spawnSnake();
        spawnEnemies(4);
        spawnFood();
        setupSpawningTimer();

        GameLoop gameLoop = new GameLoop(snake);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }

    private void spawnSnake() {
        snake = new Snake(new Point2D(500, 500));
    }

    private void spawnEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) new SimpleEnemy();
    }

    private void spawnFood() {
        new Food();
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }

//    This function counts the seconds in the game and stores the value into the "timer" variable.
//    Based on "timer", we decide what game entities to spawn.
    private void setupSpawningTimer() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timer.addAndGet(1);

//            Spawn one SpeedPotion per 25 seconds
            if (timer.intValue() % 25 == 0) {
                new SpeedPotion(speedPotionSpawnDuration);
//            Spawn one PowerBoom per 40 seconds
            } else if (timer.intValue() % 40 == 0) {
                new PowerBoom(powerBoomSpawnDuration);
            } else if (timer.intValue() % 100 == 0) {
                new GoldChest(goldChestSpawnDuration);
            } else if (timer.intValue() % 50 == 0) {
                new FireBall(fireBallSpawnDuration);
            }

            if (timer.intValue() % 10 == 0) {
                spawnEnemies(4);
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void setTableBackground(Image tableBackground) {
        setBackground(new Background(new BackgroundImage(tableBackground,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }
}
