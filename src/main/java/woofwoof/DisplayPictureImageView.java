package woofwoof;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

/**
 * A custom ImageView for display pictures that displays a round image by applying a circular clip.
 */
public class DisplayPictureImageView extends ImageView {

    /**
     * Constructs a DisplayPictureImageView with the specified Image, displaying it as a round image.
     *
     * @param image The Image to be displayed in the round ImageView.
     */
    public DisplayPictureImageView(Image image) {
        super(image);
        setClip(createClip());
    }

    /**
     * Creates a circular clip based on the dimensions of the image.
     *
     * @return A circular clip as a Circle shape.
     */
    private Circle createClip() {
        double radius = 50;
        Circle clip = new Circle(radius);
        clip.setCenterX(radius);
        clip.setCenterY(radius);
        return clip;
    }
}
