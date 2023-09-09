package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * The `DialogBox` class represents a chat dialog box in the Fishron application's user interface.
 * It contains a label for text and an image view for displaying an avatar or profile picture.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructs a new `DialogBox` with the provided label and image view.
     *
     * @param l The label containing text to display in the dialog.
     * @param iv The image view displaying an avatar or profile picture.
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
     * Flips the dialog box to change the alignment, placing the image view on the left and text on the right.
     * This is useful for displaying messages from the user and the application.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates and returns a `DialogBox` for representing a user's message.
     *
     * @param l The label containing the user's message text.
     * @param iv The image view displaying the user's avatar or profile picture.
     * @return A `DialogBox` for the user's message.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Creates and returns a `DialogBox` for representing a message from Duke (the application).
     * The dialog box is flipped to show Duke's message on the left side.
     *
     * @param l The label containing Duke's message text.
     * @param iv The image view displaying Duke's avatar or profile picture.
     * @return A flipped `DialogBox` for Duke's message.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
