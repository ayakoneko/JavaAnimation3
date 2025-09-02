package org.oosd.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainScreen implements Screen {
    private VBox mainScreen;
    private Screen configScreen, gameScreen;
    private final Frame parent;

    public MainScreen(Frame frame) {
        this.parent = frame;

        mainScreen = new VBox(10);
        mainScreen.setAlignment(Pos.CENTER);
        mainScreen.setPadding(new Insets(20));
        mainScreen.setSpacing(20);

        Label label = new Label("Main Screen");

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> parent.showScreen(gameScreen));

        Button configButton = new Button("Configuration");
        configButton.setOnAction(e -> parent.showScreen(configScreen));

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> parent.showExitConfirmation());

        mainScreen.getChildren().addAll(label, startButton, configButton, exitButton);
    }

    @Override
    public Node getScreen() {
        return mainScreen;
    }

    @Override
    public void setRoute(String path, Screen screen) {
        switch (path) {
            case "config" -> this.configScreen = screen;
            case "game" -> this.gameScreen = screen;
            default -> { /* no-op */ }
        }
    }

}
