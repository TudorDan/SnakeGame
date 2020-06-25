package com.codecool.snake;

import com.codecool.snake.Game;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameInfoBox {
    static VBox gameInfoBox;
    static VBox snake1InfoBox;
    static VBox snake2InfoBox;
    static Text titleSnake1;
    static Text titleSnake2;
    static HBox speedPotionBox;
    static HBox fireBallBox;
    static Text speedPotionCounter;
    static Text fireBallCounter;
    static Game game;

    public GameInfoBox(Game game) {
        createSnake1InfoBox();
        createSnake2InfoBox();
        createGameInfoBox(game);
        GameInfoBox.game = game;
    }

    private void createGameInfoBox(Game game) {
        gameInfoBox = new VBox();
        gameInfoBox.setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 40, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));
        gameInfoBox.setPrefSize(200, 100);
        gameInfoBox.setLayoutX(30);
        gameInfoBox.setLayoutY(200);

        gameInfoBox.getChildren().add(snake1InfoBox);
        gameInfoBox.getChildren().add(snake2InfoBox);

        game.getChildren().add(gameInfoBox);
    }

    private void createSnake1InfoBox() {
        snake1InfoBox = new VBox();
        snake1InfoBox.setPadding(new Insets(10));
        snake1InfoBox.setSpacing(8);

        titleSnake1 = new Text("BLUE DRAGON: 0");
        titleSnake1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 18));
        titleSnake1.setFill(Color.TURQUOISE);

        snake1InfoBox.getChildren().add(titleSnake1);
    }

    private void createSnake2InfoBox() {
        snake2InfoBox = new VBox();
        snake2InfoBox.setPadding(new Insets(10));
        snake2InfoBox.setSpacing(8);

        titleSnake2 = new Text("RED DRAGON: 0");
        titleSnake2.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 18));
        titleSnake2.setFill(Color.INDIANRED);

        snake2InfoBox.getChildren().add(titleSnake2);
    }

    public static void changeTitleSnake1(String newTitle) {
        titleSnake1.setText(newTitle);
    }

    public static void changeTitleSnake2(String newTitle) {
        titleSnake2.setText(newTitle);
    }

    public static void createSpeedPotionBox(String snakeName) {
        speedPotionBox = new HBox();
        speedPotionBox.setPadding(new Insets(10));
        speedPotionBox.setSpacing(8);

        Image img = new Image("potion.png");
        ImageView imgView = new ImageView(img);

        speedPotionCounter = new Text("10");
        speedPotionCounter.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        speedPotionCounter.setFill(Color.WHITE);

        speedPotionBox.getChildren().add(imgView);
        speedPotionBox.getChildren().add(speedPotionCounter);

        switch (snakeName) {
            case "Blue":
                snake1InfoBox.getChildren().add(speedPotionBox);
                break;
            case "Red":
                snake2InfoBox.getChildren().add(speedPotionBox);
                break;
        }
    }

    public static void createFireBallBox(String snakeName) {
        fireBallBox = new HBox();
        fireBallBox.setPadding(new Insets(10));
        fireBallBox.setSpacing(8);

        Image img = new Image("ball-of-fire.png");
        ImageView imgView = new ImageView(img);

        fireBallCounter = new Text("10");
        fireBallCounter.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        fireBallCounter.setFill(Color.WHITE);

        fireBallBox.getChildren().add(imgView);
        fireBallBox.getChildren().add(fireBallCounter);

        switch (snakeName) {
            case "Blue":
                snake1InfoBox.getChildren().add(fireBallBox);
                break;
            case "Red":
                snake2InfoBox.getChildren().add(fireBallBox);
                break;
        }
    }

    public static void changeSpeedPotionCounter(String counter) {
        speedPotionCounter.setText(counter);
    }

    public static void changeFireBallCounter(String counter) {
        fireBallCounter.setText(counter);
    }

    public static void destroySpeedPotionBox(String snakeName) {
        switch (snakeName) {
            case "Blue":
                snake1InfoBox.getChildren().remove(speedPotionBox);
                break;
            case "Red":
                snake2InfoBox.getChildren().remove(speedPotionBox);
                break;
        }
    }

    public static void destroyFireBallBox(String snakeName) {
        switch (snakeName) {
            case "Blue":
                snake1InfoBox.getChildren().remove(fireBallBox);
                break;
            case "Red":
                snake2InfoBox.getChildren().remove(fireBallBox);
                break;
        }
    }
}
