package org.oosd.UI;

import javafx.animation.AnimationTimer;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.oosd.model.Game;

public class GamePane extends Pane {
    private AnimationTimer timer;
    private Circle ball;
    private Game game;

    public void setGame(Game game) {
        this.game = game;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                game.proceed();
                ball.setCenterX(game.getX());
                ball.setCenterY(game.getY());
            }
        };
    }

    private void buildGamePane(){
        getChildren().clear();
        Rectangle field = new Rectangle(0, 9, Game.fieldWidth, Game.fieldHeight);
        field.setFill(Color.TRANSPARENT);
        field.setStroke(Color.BLACK);

        ball = new Circle(game.getSize(), game.getColor());
        ball.setCenterX(game.getX());
        ball.setCenterY(game.getY());

        if (game.isHasShadow()){
            DropShadow shadow = new DropShadow();
            shadow.setOffsetX(5);
            shadow.setOffsetY(5);
            shadow.setColor(Color.GRAY);
            ball.setEffect(shadow);
        }

        getChildren().setAll(field, ball);
        requestFocus();
    }

    void stopGame(){
        if (timer != null){
            timer.stop();
        }
    }

    void startGame(){
        buildGamePane();
        timer.start();
    }

}
