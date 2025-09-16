package org.oosd.UI.sprite;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Star extends Polygon {
    public Star() {
        this(10, 5, 5);  // outerRadius=10, innerRadius=5, numPoints=5
    }

    public Star(double outerRadius, double innerRadius, int numPoints) {
        super();
        double angleStep = Math.PI / numPoints;

        // Mathematical calculation for star point coordinates
        for (int i = 0; i < numPoints * 2; i++) {
            double radius = (i % 2 == 0) ? outerRadius : innerRadius;
            double angle = i * angleStep - Math.PI / 2;
            double x = Math.cos(angle) * radius;
            double y = Math.sin(angle) * radius;
            getPoints().addAll(x, y);
        }

        // Visual styling for the star
        setFill(Color.GOLD);
        setStroke(Color.ORANGE);
    }
}
