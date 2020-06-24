package com.codecool.snake.resources;

import com.codecool.snake.Game;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameInfoBox {
    VBox gameInfoBox;
    VBox snake1InfoBox;
    VBox snake2InfoBox;

    public GameInfoBox(Game game) {
        createSnake1InfoBox();
        createSnake2InfoBox();
        createGameInfoBox(game);
    }

    private void createGameInfoBox(Game game) {
        gameInfoBox = new VBox();
        gameInfoBox.setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 40, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));
        gameInfoBox.setPrefSize(200, 100);
        gameInfoBox.setLayoutX(30);
        gameInfoBox.setLayoutY(120);

        gameInfoBox.getChildren().add(snake1InfoBox);
        gameInfoBox.getChildren().add(snake2InfoBox);

        game.getChildren().add(gameInfoBox);
    }

    private void createSnake1InfoBox() {
        snake1InfoBox = new VBox();
        snake1InfoBox.setPadding(new Insets(10));
        snake1InfoBox.setSpacing(8);

        Text titleSnake1 = new Text("BLUE DRAGON: 0");
        titleSnake1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 18));
        titleSnake1.setFill(Color.TURQUOISE);

        snake1InfoBox.getChildren().add(titleSnake1);
    }

    private void createSnake2InfoBox() {
        snake2InfoBox = new VBox();
        snake2InfoBox.setPadding(new Insets(10));
        snake2InfoBox.setSpacing(8);

        Text titleSnake2 = new Text("RED DRAGON: 0");
        titleSnake2.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 18));
        titleSnake2.setFill(Color.INDIANRED);

        snake2InfoBox.getChildren().add(titleSnake2);
    }
}
