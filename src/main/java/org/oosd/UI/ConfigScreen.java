package org.oosd.UI;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class ConfigScreen {

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

}
