package com.codecool.snake;

import com.codecool.snake.resources.Resources;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

// class for holding all static stuff
public class Globals {
    private static Globals instance = null;

    public static final double WINDOW_WIDTH = 1400;
    public static final double WINDOW_HEIGHT = 800;

    public Display display;
    public Game game;
    public Stage stage;

    private GameLoop gameLoop;
    private Resources resources;


    public static Globals getInstance() {
        if(instance == null) instance = new Globals();
        return instance;
    }

    public GameLoop getGameLoop() {
        return gameLoop;
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
    }

    public Image getImage(String name) { return resources.getImage(name); }

    public void startGame() { gameLoop.start(); }

    public void stopGame() { gameLoop.stop(); }

    public void showGameWonDialog(int snakeLength) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);

        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("GAME OVER!"));
        dialogVbox.getChildren().add(new Text("(snake length is " + snakeLength + ")"));

        Button restartButton = new Button("PLAY AGAIN");
        restartButton.setOnAction(event -> {
            dialog.close();
            game.restart();
        });

        Button closeGameButton = new Button("EXIT");
        closeGameButton.setOnAction(event -> game.exit());

        StackPane layout = new StackPane();
        layout.getChildren().add(dialogVbox);
        layout.getChildren().add(restartButton);
        layout.getChildren().add(closeGameButton);

        StackPane.setAlignment(dialogVbox, Pos.TOP_CENTER);
        StackPane.setAlignment(restartButton, Pos.CENTER_LEFT);
        StackPane.setAlignment(closeGameButton, Pos.CENTER_RIGHT);

        Scene dialogScene = new Scene(layout, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private Globals() {
        // singleton needs the class to have private constructor
    }
}
