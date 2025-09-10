package org.oosd.controller;

import org.oosd.model.Game;
import javafx.scene.input.KeyCode;

public class GameController {
    private final Game game;

    public GameController(Game game) {
        this.game = game;
    }

    public void receiveKeyPress(KeyCode key) {
        switch (key) {
            case UP -> game.increaseY();
            case DOWN -> game.decreaseY();
            case LEFT -> game.increaseX();
            case RIGHT -> game.decreaseX();
        }
    }
}
