package com.codecool.snake;

import com.codecool.snake.resources.Resources;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

// class for holding all static stuff
public class Globals {
    private static Globals instance = null;

    public static final double WINDOW_WIDTH = 1400;
    public static final double WINDOW_HEIGHT = 800;

    public Display display;
    public Game game;

    private GameLoop gameLoop;
    private Resources resources;


    public static Globals getInstance() {
        if(instance == null) instance = new Globals();
        return instance;
    }

    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public void setupResources() {
        resources = new Resources();
        resources.addImage("SnakeHead", new Image("dragon-head.png"));
        resources.addImage("SnakeBody", new Image("dragon-body.png"));
        resources.addImage("SimpleEnemy", new Image("simple_enemy.png"));
        resources.addImage("Food", new Image("sheep.png"));
        resources.addImage("Potion", new Image("potion.png"));
        resources.addImage("GoldChest", new Image("gold-chest.png"));
        resources.addImage("PowerBoom", new Image("power-boom.png"));
        resources.addImage("FireBall", new Image("ball-of-fire.png"));
        resources.addImage("Life", new Image("heart.png"));
        resources.addImage("Bird", new Image("greenBird.png"));
        resources.addImage("Spaceship", new Image("spaceship3.png"));
    }

    public Image getImage(String name) { return resources.getImage(name); }

    public void startGame() { gameLoop.start(); }

    public void stopGame() { gameLoop.stop(); }

    private Globals() {
        // singleton needs the class to have private constructor
    }
}
