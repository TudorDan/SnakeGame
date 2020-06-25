package com.codecool.snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
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

        primaryStage.setTitle("Dragon Snake");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        Globals.getInstance().stage = primaryStage;

//        Add game info to screen
        new GameInfoBox(game);

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

        game.start();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Exiting..");
    }
}
