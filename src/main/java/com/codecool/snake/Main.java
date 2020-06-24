package com.codecool.snake;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game game = new Game();
        Scene mainScene = new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
        game.setTableBackground(new Image("/background.png"));

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        Globals.getInstance().stage = primaryStage;

        // create menu bar and set its width
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

        // create game menu
        Menu menuGame = new Menu("Game");

        // create restart option
        MenuItem restart = new MenuItem("Restart");
        restart.setOnAction(e -> game.restart());

        // create exit option
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> game.exit());

        // add options to game menu
        menuGame.getItems().add(restart);
        menuGame.getItems().add(exit);

        // add game menu to bar menu
        menuBar.getMenus().add(menuGame);

        // add menu bar to stage
        game.getChildren().add(menuBar);

//        Game Info Box
        VBox gameInfoBox = new VBox();
        gameInfoBox.setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 40, 0.8), CornerRadii.EMPTY, Insets.EMPTY)));
        gameInfoBox.setPrefSize(200, 100);
        gameInfoBox.setLayoutX(30);
        gameInfoBox.setLayoutY(120);

//        Snake 1 Info
        VBox snake1InfoBox = new VBox();
        snake1InfoBox.setPadding(new Insets(10));
        snake1InfoBox.setSpacing(8);

        Text titleSnake1 = new Text("BLUE DRAGON: 0");
        titleSnake1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 18));
        titleSnake1.setFill(Color.TURQUOISE);

        snake1InfoBox.getChildren().add(titleSnake1);

//        Snake 2 Info
        VBox snake2InfoBox = new VBox();
        snake2InfoBox.setPadding(new Insets(10));
        snake2InfoBox.setSpacing(8);

        Text titleSnake2 = new Text("RED DRAGON: 0");
        titleSnake2.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 18));
        titleSnake2.setFill(Color.INDIANRED);

        snake2InfoBox.getChildren().add(titleSnake2);






//        Image img = new Image("fireball.png");
//        ImageView imageView = new ImageView(img);


//        Add elements to screen
        gameInfoBox.getChildren().add(snake1InfoBox);
        gameInfoBox.getChildren().add(snake2InfoBox);
        game.getChildren().add(gameInfoBox);

        game.start();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Exiting..");
    }
}
