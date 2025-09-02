package org.oosd.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.oosd.model.Game;

public class ConfigScreen implements ScreenWithGame {
    private final Frame parent;
    private Game game;
    private final BorderPane configScreen;
    private Screen mainScreen;

    public ConfigScreen(Frame frame) {
        this.parent = frame;
        this.configScreen = new BorderPane();
    }

    private StackPane buildTop() {
        Label label = new Label("Configuration");
        label.getStyleClass().add("title-label");

        StackPane top = new StackPane(label);
        top.setPadding(new Insets(10, 0, 0, 0));
        top.setAlignment(Pos.CENTER);
        return top;
    }

    private StackPane buildBottom() {
        Button back = new Button("Back");
        back.setOnAction(e -> parent.showScreen(mainScreen));
        back.getStyleClass().add("menu-button");

        StackPane bottom = new StackPane(back);
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(0, 0, 20, 0));
        return bottom;
    }

    private VBox buildCentre() {
        VBox centre = new VBox(14);
        centre.setPadding(new Insets(20));

        CheckBox cbShadow = new CheckBox("Enable Shadow");
        cbShadow.setSelected(game.isHasShadow());
        cbShadow.setOnAction(e -> game.setHasShadow(cbShadow.isSelected()));

        HBox colourPane = new HBox(20);
        Label colourLabel = new Label("Color:");
        RadioButton rbRed = new RadioButton("Red");
        RadioButton rbGreen = new RadioButton("Green");
        RadioButton rbBlue = new RadioButton("Blue");
        ToggleGroup group = new ToggleGroup();
        rbRed.setToggleGroup(group);
        rbGreen.setToggleGroup(group);
        rbBlue.setToggleGroup(group);
        rbRed.setOnAction(e -> game.setColorString("RED"));
        rbGreen.setOnAction(e -> game.setColorString("GREEN"));
        rbBlue.setOnAction(e -> game.setColorString("BLUE"));
        switch (game.getColorString()) {
            case "RED" -> rbRed.setSelected(true);
            case "GREEN" -> rbGreen.setSelected(true);
            case "BLUE" -> rbBlue.setSelected(true);
            default -> rbRed.setSelected(true);
        }
        colourPane.getChildren().addAll(colourLabel, rbRed, rbGreen, rbBlue);

        HBox sizePane = new HBox(10);
        Label sizeLabel = new Label("Size: " + game.getSize());
        Slider sizeSlider = new Slider(5, 20, game.getSize());
        sizeSlider.setShowTickLabels(true);
        sizeSlider.setShowTickMarks(true);
        sizeSlider.setMajorTickUnit(5);
        sizeSlider.setMinorTickCount(1);
        sizeSlider.valueProperty().addListener((obs, ov, nv) -> {
            int s = nv.intValue();
            game.setSize(s);
            sizeLabel.setText("Size: " + s);
        });
        HBox.setHgrow(sizeSlider, Priority.ALWAYS);
        sizePane.getChildren().addAll(sizeLabel, sizeSlider);

        centre.getChildren().addAll(cbShadow, colourPane, sizePane);
        return centre;
    }

    private void buildScreen() {
        configScreen.setTop(buildTop());
        configScreen.setCenter(buildCentre());
        configScreen.setBottom(buildBottom());
    }


    @Override
    public Node getScreen() {
        return configScreen;
    }

    @Override
    public void setRoute(String path, Screen screen) {
        if ("back".equals(path)) {
            this.mainScreen = screen;
            buildScreen();
        }
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

}
