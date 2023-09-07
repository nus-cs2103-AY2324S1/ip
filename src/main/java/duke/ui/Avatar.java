package duke.ui;

import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



/**
 * Encapsulates a custom Avatar component.
 *
 * Adapted from <a href="https://gist.github.com/jewelsea/7904493">this stackoverflow answer</a>
 */
public class Avatar extends ImageView {
    private ImageView iv;
    private Rectangle clip;

    /**
     * Constructor for an avatar.
     * Provides a rounded border.
     *
     * @param image
     */
    public Avatar(Image image) {
        // snapshot the rounded image.
        super(image);
//        Rectangle clip = new Rectangle(
//                this.getFitWidth(), this.getFitHeight()
//        );
//        clip.setArcWidth(15);
//        clip.setArcHeight(15);
//        clip.setStroke(Color.BLACK);
//        this.setClip(clip);
//
//
//        SnapshotParameters parameters = new SnapshotParameters();
//        parameters.setFill(Color.TRANSPARENT);
//        WritableImage wimg = this.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
//        this.setClip(null);

        // apply a shadow effect.
        this.setEffect(new DropShadow(15, Color.BLACK));

//        // store the rounded image in the imageView.
//        this.setImage(wimg);



    }


}
