package org.oosd.UI;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.oosd.model.Game;

public class GameScreen implements ScreenWithGame{
    private final Frame parent;
    private Game game;
    private final Pane gamePane = new Pane();
    private Screen mainScreen;
    private AnimationTimer timer;
    private Circle ball;

    public GameScreen(Frame frame) {
        this.parent = frame;

        gamePane.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                buildScreen();
                setControl(newScene);
                startGame();
            }
        });
    }

    private void setControl(Scene scene) {
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
    }

    private void buildScreen() {
        Rectangle field = new Rectangle(0, 0, Game.fieldWidth, Game.fieldHeight);
        field.setFill(Color.TRANSPARENT);
        field.setStroke(Color.BLACK);

        ball = new Circle(game.getSize(), game.getColor());
        ball.setCenterX(Game.fieldWidth / 2);
        ball.setCenterY(Game.fieldHeight / 2);
        if (game.isHasShadow()) {
            DropShadow shadow = new DropShadow();
            shadow.setOffsetX(5);
            shadow.setOffsetY(5);
            ball.setEffect(shadow);
        }

        Button back = new Button("Back");
        back.setLayoutX(10);
        back.setLayoutY(10);
        back.setOnAction(e -> {
            if (timer != null) timer.stop();
            parent.showScreen(mainScreen);
        });

        gamePane.getChildren().setAll(field, ball, back);
        gamePane.requestFocus();
    }

    private void startGame() {
        timer = new AnimationTimer() {
            int count;

            @Override
            public void handle(long now) {
                double nextX = ball.getCenterX() + game.getDx();
                double nextY = ball.getCenterY() + game.getDy();

                count++;
                if (count % 10 == 0) System.out.println("Count: " + count);

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
    }

    @Override
    public Node getScreen() {
        return gamePane;
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void setRoute(String path, Screen screen) {
        if ("back".equals(path)) {
            this.mainScreen = screen;
        }
    }

}
