package duke;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

/**
 * Creates a Circle Clip for the avatars in the chatbot
 */
public class CircleClip {

    private final int centerX;
    private final int centerY;
    private final int radius;
    private final Circle circle;

    /**
     * constructor for Circle clip
     * @param centerX x-coordinates of center
     * @param centerY y-coordinates of center
     * @param radius radius of the clip
     */
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
