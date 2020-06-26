package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.enemies.SecondEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.enemies.ThirdEnemy;
import com.codecool.snake.entities.powerups.*;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;


public class Game extends Pane {
    private Snake blueSnake = null;
    private Snake redSnake = null;
    private GameTimer gameTimer = new GameTimer();
    private GameLoop gameLoop = new GameLoop(blueSnake, redSnake);

    AtomicInteger timer = new AtomicInteger();
    Timeline timeline;
    public static boolean powerBoom = false;
    public static boolean speedPotion = false;
    public static boolean fireBall = false;

    private static final int speedPotionSpawnDuration = 10;
    private static final int goldChestSpawnDuration = 15;
    private static final int fireBallSpawnDuration = 8;
    private static final int powerBoomSpawnDuration = 5;

    public static boolean redSnakeDead = false;
    public static boolean blueSnakeDead = false;

    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();
    }

    public void init() {
        spawnSnakes();
        spawnEnemies(2);
        spawnFood();
        setupSpawningTimer();

        GameLoop gameLoop = new GameLoop(blueSnake, redSnake);
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
        timeline.stop();
        timer.getAndSet(0);

        blueSnake.changeScore(0);
        blueSnake.setHealth(100);
        redSnake.changeScore(0);
        redSnake.setHealth(100);

        redSnakeDead = false;
        blueSnakeDead = false;

        //delete everything
        getChildren().removeIf(child -> child instanceof GameEntity);

        init();
        start();
    }

    private void spawnSnakes() {
        blueSnake = new Snake(new Point2D(400, 500), "Blue");
        redSnake = new Snake(new Point2D(600, 500), "Red");
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
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timer.addAndGet(1);

            // check if game is running
            if (Globals.getInstance().getGameLoop().isRunning()) {
                // Spawn one SpeedPotion per 25 seconds if SpeedPotion is not already active
                if (timer.intValue() % 25 == 0 && !speedPotion) {
                    new SpeedPotion(speedPotionSpawnDuration);
                }
                // Spawn one PowerBoom per 40 seconds
                if (timer.intValue() % 40 == 0 && !powerBoom) {
                    new PowerBoom(powerBoomSpawnDuration);
                }
                // Spawn one GoldChest per 35 seconds
                if (timer.intValue() % 35 == 0) {
                    new GoldChest(goldChestSpawnDuration);
                }
                // Spawn one FireBall per 30 seconds
                if (timer.intValue() % 30 == 0 && !fireBall) {
                    new FireBall(fireBallSpawnDuration);
                }
                // Spawn 3 Enemies per 5 seconds
                if (timer.intValue() % 5 == 0) {
                    spawnEnemies(3);
                }
                if (timer.intValue() % 15 == 0) {
                    spawnSecondEnemies(2);
                }
                if (timer.intValue() % 20 == 0) {
                    spawnThirdEnemies(1);
                }
//                Destroy all enemies if PowerBoom is active
                if (powerBoom) {
                    clearEnemies();
                }
//               If both snakes are dead, the game is over
                if (redSnakeDead && blueSnakeDead) {
                    Globals.getInstance().stopGame();
                    timeline.stop();
                    String winner = showWinner();
                    Globals.getInstance().showGameWonDialog(winner);
                }
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    private String showWinner() {
        int scoreBlueSnake = blueSnake.getScore();
        int scoreRedSnake = redSnake.getScore();
        if (scoreBlueSnake > scoreRedSnake) {
            return "Blue";
        } else if (scoreBlueSnake < scoreRedSnake){
            return "Red";
        } else {
            return "TIE";
        }
    }

    public void setTableBackground(Image tableBackground) {
        setBackground(new Background(new BackgroundImage(tableBackground,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }


    public Snake getBlueSnake() {
        return blueSnake;
    }

    public Snake getRedSnake() {return redSnake;}

    public void clearEnemies() {
        getChildren().removeIf(child -> child instanceof Enemy);

    }

}
