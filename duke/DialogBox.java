package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * The `DialogBox` class represents a custom control for displaying chat dialog boxes in the user interface.
 * Each `DialogBox` consists of an ImageView to represent the speaker's face and a label containing text from the speaker.
 * This class is used to create dialog boxes for both the user and Duke (the chatbot) in the graphical user interface.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a new `DialogBox` instance with the provided text and image.
     * This constructor is used internally to create dialog boxes for user and Duke messages.
     *
     * @param text The text content to be displayed in the dialog box.
     * @param img  The image (avatar) of the speaker to be displayed in the dialog box.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a new `DialogBox` for the user's messages.
     *
     * @param text The text content of the user's message.
     * @param img  The image (avatar) of the user.
     * @return A `DialogBox` instance for the user's message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a new `DialogBox` for Duke's (chatbot's) messages.
     *
     * @param text The text content of Duke's message.
     * @param img  The image (avatar) of Duke.
     * @return A `DialogBox` instance for Duke's message.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
