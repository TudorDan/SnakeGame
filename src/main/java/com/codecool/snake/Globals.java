package com.codecool.snake;

import com.codecool.snake.resources.Resources;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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
        if (instance == null) instance = new Globals();
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
//        SNAKES
        resources.addImage("SnakeHeadBlue", new Image("snake_head_ice.png"));
        resources.addImage("SnakeBodyBlue", new Image("snake_body_ice.png"));

        resources.addImage("SnakeHeadRed", new Image("dragon-head.png"));
        resources.addImage("SnakeBodyRed", new Image("dragon-body.png"));
//        ENEMIES
        resources.addImage("SimpleEnemy", new Image("simple_enemy.png"));
        resources.addImage("Bird", new Image("greenBird.png"));
        resources.addImage("Spaceship", new Image("spaceship3.png"));
        resources.addImage("Bird", new Image("greenBird.png"));
        resources.addImage("Boss", new Image("boss.png"));
//        POWER-UPS
        resources.addImage("Food", new Image("sheep.png"));
        resources.addImage("Potion", new Image("potion.png"));
        resources.addImage("GoldChest", new Image("gold-chest.png"));
        resources.addImage("PowerBoom", new Image("power-boom.png"));
        resources.addImage("FireBall", new Image("fireball.png"));
        resources.addImage("FireBallBig", new Image("fireballbig.png"));
        resources.addImage("Life", new Image("heart.png"));
        resources.addImage("IceBall", new Image("iceball.png"));
        resources.addImage("IceBallBig", new Image("iceballbig.png"));
//        HEALTH BAR RED
        resources.addImage("Health100Red", new Image("healthfull.png"));
        resources.addImage("Health90Red", new Image("health90.png"));
        resources.addImage("Health80Red", new Image("health80.png"));
        resources.addImage("Health70Red", new Image("health70.png"));
        resources.addImage("Health60Red", new Image("health60.png"));
        resources.addImage("Health50Red", new Image("health50.png"));
        resources.addImage("Health40Red", new Image("health40.png"));
        resources.addImage("Health30Red", new Image("health30.png"));
        resources.addImage("Health20Red", new Image("health20.png"));
        resources.addImage("Health10Red", new Image("health10.png"));
        resources.addImage("Health0Red", new Image("health0.png"));
//        HEALTH BAR BLUE
        resources.addImage("Health100Blue", new Image("hfull.png"));
        resources.addImage("Health90Blue", new Image("h9.png"));
        resources.addImage("Health80Blue", new Image("h8.png"));
        resources.addImage("Health70Blue", new Image("h7.png"));
        resources.addImage("Health60Blue", new Image("h6.png"));
        resources.addImage("Health50Blue", new Image("h5.png"));
        resources.addImage("Health40Blue", new Image("h4.png"));
        resources.addImage("Health30Blue", new Image("h3.png"));
        resources.addImage("Health20Blue", new Image("h2.png"));
        resources.addImage("Health10Blue", new Image("h1.png"));
        resources.addImage("Health0Blue", new Image("h0.png"));
    }

    public Image getImage(String name) {
        return resources.getImage(name);
    }

    public void startGame() {
        gameLoop.start();
    }

    public void stopGame() {
        gameLoop.stop();
    }

    public void showGameWonDialog(String winner) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);

        VBox dialogVbox = new VBox(20);
        if (winner != "TIE") {
            dialogVbox.getChildren().add(new Text(winner + " Dragon has won the game!"));
        } else {
            dialogVbox.getChildren().add(new Text("It's a tie!"));
        }


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
