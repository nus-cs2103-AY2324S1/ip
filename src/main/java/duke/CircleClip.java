package duke;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class CircleClip {

    private final int centerX;
    private final int centerY;
    private final int radius;
    private final Circle circle;

    public CircleClip(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.circle = new Circle();

        this.circle.setCenterX(this.centerX);
        this.circle.setCenterY(this.centerY);
        this.circle.setRadius(this.radius);
    }
    public void clip(ImageView iv) {
        iv.setClip(this.circle);
    }
}
