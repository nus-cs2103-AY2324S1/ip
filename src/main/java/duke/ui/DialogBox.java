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
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of
 *  an ImageView to represent the speaker's face, and
 *  a Label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    // Enum to store paths to FXML files for different types of dialog boxes.
    private enum DialogBoxType {
        USER("/view/UserDialogBox.fxml"),
        DUKE("/view/DukeDialogBox.fxml");
        private final String path;

        /**
         * Constructor for a DialogBoxType.
         *
         * @param path The path to the FXML file for the dialog box.
         */
        DialogBoxType(String path) {
            this.path = path;
        }
    }

    /**
     * Constructor for a DialogBox.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed in the dialog box.
     * @param type The type of dialog box to be displayed.
     */
    private DialogBox(String text, Image img, DialogBoxType type) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(type.path));
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
     * Returns a DialogBox with the user's text and image.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed in the dialog box.
     * @return A DialogBox with the user's text and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, DialogBoxType.USER);
    }

    /**
     * Returns a DialogBox with Duke's text and image.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed in the dialog box.
     * @return A DialogBox with Duke's text and image.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, DialogBoxType.DUKE);
        db.flip();
        return db;
    }
}
