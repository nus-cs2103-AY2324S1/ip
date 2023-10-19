package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * The DialogBox class represents a graphical dialog box in the user interface. It is used to display
 * messages from the user and responses from Duke (the chatbot).
 * It extends the HBox class and contains a Label for text content and an ImageView for displaying images.
 */
public class DialogBox extends HBox {

    private final Label text;
    private final ImageView displayPicture;

    /**
     * Constructs a DialogBox with a Label and an ImageView to display text and an image, respectively.
     *
     * @param l  The Label containing the text content of the dialog box.
     * @param iv The ImageView displaying an image in the dialog box (e.g., user's or Duke's avatar).
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
     * Creates and returns a user's dialog box with the provided Label and ImageView.
     *
     * @param l  The Label containing the user's message.
     * @param iv The ImageView for the user's avatar or image.
     * @return A DialogBox representing the user's message.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Creates and returns Duke's dialog box with the provided Label and ImageView.
     * Additionally, it flips the dialog box to align it to the top-left corner (Duke's messages).
     *
     * @param l  The Label containing Duke's response message.
     * @param iv The ImageView for Duke's avatar or image.
     * @return A DialogBox representing Duke's response.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

    /**
     * Flips the alignment of the dialog box to the top-left corner.
     * This is used for Duke's messages to align them differently from the user's messages.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
}