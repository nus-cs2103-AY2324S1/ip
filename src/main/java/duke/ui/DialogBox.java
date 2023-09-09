package duke.ui;

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
 * A custom control class representing a dialog box with an ImageView for the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {

    /** The label for displaying text from the speaker. */
    @FXML
    private Label dialog;

    /** The ImageView for displaying the speaker's face. */
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a `DialogBox` with the specified text and image.
     * This private constructor is used internally to create `DialogBox` instances.
     *
     * @param text The text to display in the dialog box.
     * @param img  The image to display as the speaker's face.
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

        // Add assertions to check assumptions about the provided parameters
        assert text != null : "Text should not be null";
        assert img != null : "Image should not be null";

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
     * Creates a user dialog box with the specified text and image.
     *
     * @param text The text to display in the user dialog.
     * @param img  The image to display as the user's face.
     * @return A `DialogBox` instance representing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a Duke dialog box with the specified text and image.
     *
     * @param text The text to display in Duke's dialog.
     * @param img  The image to display as Duke's face.
     * @return A `DialogBox` instance representing Duke's dialog.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}

