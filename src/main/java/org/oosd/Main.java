package org.oosd;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.oosd.UI.Frame;
import org.oosd.UI.Screen;
import org.oosd.UI.ScreenWithGame;
import org.oosd.UI.MainScreen;
import org.oosd.UI.ConfigScreen;
import org.oosd.UI.GameScreen;
import org.oosd.controller.GameController;
import org.oosd.model.Game;

public class Main extends Application implements Frame {

    private StackPane root;      // Main container
    private Scene scene;
    private Game game;

    private void buildScreens() {
        Screen mainScreen = new MainScreen(this);
        ScreenWithGame configScreen = new ConfigScreen(this);
        ScreenWithGame gameScreen = new GameScreen(this);

        mainScreen.setRoute("config", configScreen);
        mainScreen.setRoute("game", gameScreen);

        configScreen.setGame(game);
        configScreen.setRoute("back", mainScreen);

        gameScreen.setGame(game);
        gameScreen.setRoute("back", mainScreen);

        showScreen(mainScreen);
    }

    @Override
    public void start(Stage primaryStage) {
        game = new Game();
        GameController gc = new GameController(game);
        root = new StackPane();

        scene = new Scene(root, Game.fieldWidth + 2 * Env.HORIZONTAL_MARGIN, Game.fieldHeight + Env.TOP_MARGIN + Env.BOTTOM_MARGIN);
        scene.setOnKeyPressed(e->gc.receiveKeyPress(e.getCode()));
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        primaryStage.setTitle("JavaFX Multi-Screen Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

        buildScreens();
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            showExitConfirmation();
        });

    }

    @Override
    public void showScreen(Screen scr) {
        root.getChildren().setAll(scr.getScreen());
    }

    @Override
    public void showExitConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setContentText("Are you sure you want to exit?");
        var result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
