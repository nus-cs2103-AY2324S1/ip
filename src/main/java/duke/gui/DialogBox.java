package duke.gui;

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
 * DialogBox class encapsulates a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 * It is the controller of DialogBox.fxml.
 *
 * @author ruo-x
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor of a DialogBox object.
     *
     * @param text Text to add inside the dialog box.
     * @param img Image to add inside the dialog box
     */
    private DialogBox(String text, Image img) {
        try {
            // Load layout of dialog box
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize a new DialogBox
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
     * Returns a dialog box designed for the user.
     * Image is at the right-most position followed by the text on its left.
     *
     * @param text Text to add inside the dialog box.
     * @param img Image to add inside the dialog box.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns a dialog box designed for Bobi.
     * Image is at the left-most position followed by the text on its right.
     *
     * @param text Text to add inside the dialog box.
     * @param img Image to add inside the dialog box.
     */
    public static DialogBox getBobiDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
