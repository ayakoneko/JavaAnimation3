package org.oosd.model;

import javafx.scene.paint.Color;

public class Game {

    private double dx = 3;       // X velocity
    private double dy = 3;       // Y velocity
    private boolean hasShadow = false;
    private String colorString = "RED";
    private int size = 10;

    private double x = fieldWidth/2, y=fieldHeight/2; // initial ball's position
    public static final double fieldWidth = 400;
    public static final double fieldHeight = 270;

    public double getDx() { return dx; }
    public void setDx(double dx) { this.dx = dx; }
    public double getDy() { return dy; }
    public void setDy(double dy) { this.dy = dy; }

    public boolean isHasShadow() { return hasShadow; }
    public void setHasShadow(boolean hasShadow) { this.hasShadow = hasShadow; }

    public String getColorString() { return colorString; }
    public void setColorString(String colorString) { this.colorString = colorString; }

    public Color getColor() {
        return switch (colorString) {
            case "RED" -> Color.RED;
            case "GREEN" -> Color.GREEN;
            case "BLUE" -> Color.BLUE;
            default -> Color.RED;
        };
    }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    public void increaseX() { dx = dx > 0 ? dx - 1 : dx + 1; }
    public void decreaseX() { dx = dx < 0 ? dx - 1 : dx + 1; }
    public void increaseY() { dy = dy > 0 ? dy + 1 : dy - 1; }
    public void decreaseY() { dy = dy < 0 ? dy + 1 : dy - 1; }

    public double getX() { return x; }
    public double getY() { return y; }

    public void proceed(){
        double nextX = x + dx;
        double nextY = y + dy;

        //When ball fit the fieldWidth, revert the direction
        if (nextX - size < 0 || nextX + size > Game.fieldWidth){
            dx = -dx;
        }
        if (nextY - size < 0 || nextY + size > Game.fieldHeight){
            dy = -dy;
        }

        x += dx;
        y += dy;
    }
}
