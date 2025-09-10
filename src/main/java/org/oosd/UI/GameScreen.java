package org.oosd.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import org.oosd.model.Game;

public class GameScreen implements ScreenWithGame{
    private final GamePane gamePane;
    private final Frame parent;
    private BorderPane borderPane;
    private Screen mainScreen;

    public GameScreen(Frame frame) {
        parent = frame;
        gamePane = new GamePane();
        gamePane.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                gamePane.startGame();
            }
        });
        buildScreen();
    }

    private StackPane getBottomPane() {
        Button back = new Button("Back");
        back.setOnAction(e->{
            gamePane.stopGame();
            parent.showScreen(mainScreen);
        });
        back.getStyleClass().add("menu-button");
        back.setFocusTraversable(false);

        StackPane bottomPane = new StackPane(back);
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setPadding(new Insets(0, 0, 20, 0));
        bottomPane.setFocusTraversable(false);
        return bottomPane;
    }

    private void buildScreen() {
        borderPane = new BorderPane();
        StackPane gamePaneWrapper = new StackPane(gamePane);
        gamePaneWrapper.setAlignment(Pos.CENTER);
        gamePaneWrapper.setPadding(new Insets(10, 0, 10, 10));

        borderPane.setTop(gamePaneWrapper);
        borderPane.setBottom(getBottomPane());
    }


    @Override
    public Node getScreen() {
        return borderPane;
    }

    @Override
    public void setGame(Game game) {
        gamePane.setGame(game);
    }

    @Override
    public void setRoute(String path, Screen screen) {
        if ("back".equals(path)) {
            mainScreen = screen;
        }
    }

}
