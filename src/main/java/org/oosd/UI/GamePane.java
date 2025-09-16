package org.oosd.UI;

import javafx.animation.AnimationTimer;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.oosd.model.Game;
import org.oosd.UI.sprite.Sprite;
import org.oosd.UI.sprite.SpriteFactory;
import java.util.ArrayList;
import java.util.List;

public class GamePane extends Pane {
    private AnimationTimer timer;
    private Circle ball;
    private Game game;

    private final List<Sprite> sprites;
    public GamePane() {
        sprites = new ArrayList<>();  // Initialise sprite collection
    }

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

    private void initSprites(int num) {
        sprites.clear();
        // Get factory instance (singleton pattern)
        SpriteFactory factory = SpriteFactory.getFactory();
        // Create specified number of sprites with random positioning
        for (int i = 0; i < num; i++) {
            Sprite sprite = factory.createSprite(); // Fully random sprite
            sprites.add(sprite);
        }
    }

    private void buildGamePane(){
        getChildren().clear();
        Rectangle field = new Rectangle(0, 9, Game.fieldWidth, Game.fieldHeight);
        field.setFill(Color.TRANSPARENT);
        field.setStroke(Color.BLACK);

        // This prevents sprites from appearing outside the game field
        Rectangle clip = new Rectangle(0, 0, Game.fieldWidth, Game.fieldHeight);
        setClip(clip);

        ball = new Circle(game.getSize(), game.getColor());
        if (game.isHasShadow()) {
            DropShadow shadow = new DropShadow();
            shadow.setOffsetX(5);
            shadow.setOffsetY(5);
            ball.setEffect(shadow);
        }

        getChildren().setAll(field, ball);

        initSprites(10);
        for (Sprite sprite : sprites) {
            getChildren().add(sprite.getNode());
        }

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
