package peko;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The `DialogBox` class is used to create custom dialog boxes for a chat interface.
 * It extends `HBox` and consists of a label for text and an image view for a display picture (e.g., user's avatar).
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Creates a new `DialogBox` with the specified label and image view.
     *
     * @param l  The label containing the text to be displayed in the dialog box.
     * @param iv The image view containing the display picture to be displayed in the dialog box.
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }
    /**
     * Flips the alignment of the dialog box to create Peko's dialog box (aligned to the top left).
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates and returns a user's dialog box with the specified label and image view.
     *
     * @param l  The label containing the text to be displayed in the dialog box.
     * @param iv The image view containing the display picture to be displayed in the dialog box.
     * @return A user's dialog box.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Creates and returns Peko's dialog box (flipped for alignment) with the specified label and image view.
     *
     * @param l  The label containing the text to be displayed in the dialog box.
     * @param iv The image view containing the display picture to be displayed in the dialog box.
     * @return Peko's dialog box.
     */
    public static DialogBox getPekoDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}