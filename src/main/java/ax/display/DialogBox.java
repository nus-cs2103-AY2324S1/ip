package ax.display;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * A Dialog Box that extends from a HBox
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * The constructor for DialogBox
     * @param l label
     * @param iv image view
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;


        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        this.setSpacing(5);
        Rectangle clip = new Rectangle(displayPicture.getFitWidth(), displayPicture.getFitHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);

        WritableImage image = displayPicture.snapshot(parameters, null);

        displayPicture.setClip(clip);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }
    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getAxDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

}
