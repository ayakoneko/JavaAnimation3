package org.oosd;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.oosd.model.Game;

public class Main extends Application {

    private StackPane root;      // Main container
    private Scene scene;
    private AnimationTimer timer;
    private Game game;

    @Override
    public void start(Stage primaryStage) {
        game = new Game();
        root = new StackPane();
        scene = new Scene(root, Game.fieldWidth, Game.fieldHeight);

        showMainScreen();

        primaryStage.setTitle("JavaFX Multi-Screen Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showMainScreen() {
        VBox mainScreen = new VBox(10);
        mainScreen.setPadding(new Insets(20));

        Label label = new Label("Main Screen");

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> showGameScreen());

        Button configButton = new Button("Configuration");
        configButton.setOnAction(e -> showConfigScreen());

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> System.exit(0));


        mainScreen.getChildren().addAll(label, startButton, configButton, exitButton);
        root.getChildren().setAll(mainScreen);
    }

    private void showConfigScreen() {
        VBox configScreen = new VBox(10);
        configScreen.setPadding(new Insets(20));
        Label label = new Label("Configuration");

        CheckBox cbShadow = new CheckBox("Enable Shadow");
        cbShadow.setSelected(game.isHasShadow());
        cbShadow.setOnAction(e -> game.setHasShadow(cbShadow.isSelected()));

        Label colorLabel = new Label("Color");

        RadioButton rbRed = new RadioButton("Red");
        RadioButton rbGreen = new RadioButton("Green");
        RadioButton rbBlue = new RadioButton("Blue");
        ToggleGroup colorGroup = new ToggleGroup();
        rbBlue.setToggleGroup(colorGroup);
        rbRed.setToggleGroup(colorGroup);
        rbGreen.setToggleGroup(colorGroup);
        rbBlue.setOnAction(e -> game.setColorString("BLUE"));
        rbRed.setOnAction(e -> game.setColorString("RED"));
        rbGreen.setOnAction(e -> game.setColorString("GREEN"));
        switch (game.getColorString()) {
            case "RED" -> rbRed.setSelected(true);
            case "GREEN" -> rbGreen.setSelected(true);
            case "BLUE" -> rbBlue.setSelected(true);
            default -> rbRed.setSelected(true);
        }

        Label sizeLabel = new Label("Size: " + game.getSize());
        Slider sizeSlider = new Slider(5, 20, game.getSize());
        sizeSlider.setShowTickLabels(true);
        sizeSlider.setShowTickMarks(true);
        sizeSlider.setMajorTickUnit(5);
        sizeSlider.setMinorTickCount(1);
        sizeSlider.valueProperty().addListener(
                (obs, oldVal, newVal) -> {
                    int newSize = newVal.intValue();
                    game.setSize(newSize);
                    sizeLabel.setText("Size: " + newSize);
                }
        );

        Button back = new Button("Back");
        back.setOnAction(e -> showMainScreen());

        configScreen.getChildren().addAll(label, cbShadow, colorLabel, rbRed, rbGreen, rbBlue, sizeLabel, sizeSlider, back);
        root.getChildren().setAll(configScreen);
    }

    private void showGameScreen() {
        Pane gamePane = new Pane();

        // Create field border
        Rectangle field = new Rectangle(0, 0, Game.fieldWidth, Game.fieldHeight);
        field.setFill(Color.TRANSPARENT);
        field.setStroke(Color.BLACK);

        // Create red ball
        Circle ball = new Circle(game.getSize(), game.getColor());
        ball.setCenterX(Game.fieldWidth / 2);
        ball.setCenterY(Game.fieldHeight / 2);
        if (game.isHasShadow()) {
            DropShadow shadow = new DropShadow();
            shadow.setOffsetX(5);
            shadow.setOffsetY(5);
            ball.setEffect(shadow);
        }

        Button backButton = new Button("Back");
        backButton.setLayoutX(10);
        backButton.setLayoutY(10);
        backButton.setOnAction(e -> {
            timer.stop();
            showMainScreen();
        });

        gamePane.getChildren().addAll(field, ball, backButton);

        // Key control
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                game.increaseY();
            } else if (e.getCode() == KeyCode.DOWN) {
                game.decreaseY();
            } else if (e.getCode() == KeyCode.LEFT) {
                game.increaseX();
            } else if (e.getCode() == KeyCode.RIGHT) {
                game.decreaseX();
            }
        });

        timer = new AnimationTimer() {
            int count;

            @Override
            public void handle(long now) {
                double nextX = ball.getCenterX() + game.getDx();
                double nextY = ball.getCenterY() + game.getDy();
                count++;
                if (count % 10 == 0) System.out.println("Count: " + count);
                // Bounce off edges
                if (nextX - ball.getRadius() < 0 || nextX + ball.getRadius() > Game.fieldWidth) {
                    game.setDx(-game.getDx());
                }
                if (nextY - ball.getRadius() < 0 || nextY + ball.getRadius() > Game.fieldHeight) {
                    game.setDy(-game.getDy());
                }

                ball.setCenterX(ball.getCenterX() + game.getDx());
                ball.setCenterY(ball.getCenterY() + game.getDy());
            }
        };

        timer.start();

        root.getChildren().setAll(gamePane);
        gamePane.requestFocus();  // Ensure pane gets key input
    }

    public static void main(String[] args) {
        launch(args);
    }
}
