package com.codecool.snake;

import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.enemies.SecondEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.enemies.ThirdEnemy;
import com.codecool.snake.entities.powerups.*;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;


import javafx.application.Platform;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;


public class Game extends Pane {
    private Snake snake = null;
    private GameTimer gameTimer = new GameTimer();

    private GameLoop gameLoop = new GameLoop(snake);

    //    Shady type of integer idk what this is but normal int won't work so who cares
    AtomicInteger timer = new AtomicInteger();
    public static boolean powerBoom = false;
    public static boolean speedPotion = false;

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

    void exit() {
        Platform.exit();
        System.exit(0);
    }

    public void restart() {
        Globals.getInstance().stopGame();

        //delete everything
        getChildren().removeIf(child -> child instanceof GameEntity);

        init();
        start();
    }

    private void spawnSnake() {
        snake = new Snake(new Point2D(500, 500));
    }

    private void spawnEnemies(int numberOfEnemies) {
        for (int i = 0; i < numberOfEnemies; ++i) new SimpleEnemy();
    }

    private void spawnSecondEnemies(int numberOfSecondEnemies) {
        for (int i = 0; i < numberOfSecondEnemies; ++i) new SecondEnemy();
    }

    private void spawnThirdEnemies(int numberOfThirdEnemies) {
        for (int i = 0; i < numberOfThirdEnemies; ++i) new ThirdEnemy();
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

            // check if game is running
            if (Globals.getInstance().getGameLoop().isRunning()) {
                // Spawn one SpeedPotion per 25 seconds if SpeedPotion is not already active
                if (timer.intValue() % 25 == 0 && !speedPotion) {
                    new SpeedPotion(speedPotionSpawnDuration);
                }
                // Spawn one PowerBoom per 40 seconds
                if (timer.intValue() % 40 == 0) {
                    new PowerBoom(powerBoomSpawnDuration);
                }
                // Spawn one GoldChest per 100 seconds
                if (timer.intValue() % 100 == 0) {
                    new GoldChest(goldChestSpawnDuration);
                }
                // Spawn one FireBall per 50 seconds
                if (timer.intValue() % 50 == 0) {
                    new FireBall(fireBallSpawnDuration);
                }
                // Spawn 4 Enemies per 10 seconds
                if (timer.intValue() % 10 == 0) {
                    if (Globals.getInstance().getGameLoop().checkSpawnCollisions()) {
                        spawnEnemies(4);
                    }
                }
                if (timer.intValue() % 30 == 0) {
                    spawnSecondEnemies(3);
                }
                if (timer.intValue() % 40 == 0) {
                    spawnThirdEnemies(3);
                }
//                Destroy all enemies if PowerBoom is active
                if (powerBoom) {
                    clearEnemies();
                }
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


    public Snake getSnake() {
        return snake;
    }
    public void clearEnemies() {
        getChildren().removeIf(child -> child instanceof Enemy);

    }
}
