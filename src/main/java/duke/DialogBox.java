package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private Label dialog;
    private ImageView displayPicture;

    /**
     * DialogBox constructor
     * @param l Label
     * @param iv ImageView
     */
    public DialogBox(Label l, ImageView iv) {
        dialog = l;
        displayPicture = iv;

        dialog.setWrapText(true);

        displayPicture.setFitWidth(50.0);
        displayPicture.setFitHeight(50.0);
        // Clip the ImageView into a circle
        Circle clipCircle = new Circle(25); // ImageView clipped to Circle of radius 25
        displayPicture.setClip(clipCircle);
        clipCircle.setCenterX(displayPicture.getFitWidth() / 2);
        clipCircle.setCenterY(displayPicture.getFitHeight() / 2);

        this.setAlignment(Pos.TOP_RIGHT);
        // Add padding between each DialogBox
        this.setPadding(new Insets(10));

        // Add padding between each ImageView and its Label
        Insets imageViewLabelPadding = new Insets(5);
        HBox.setMargin(displayPicture, imageViewLabelPadding);
        HBox.setMargin(dialog, imageViewLabelPadding);

        this.getChildren().addAll(dialog, displayPicture);
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
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
