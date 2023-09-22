package duke.controller;

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
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBoxController extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for DialogBoxController.
     * @param text Text to be displayed.
     * @param img Image to be displayed.
     */
    private DialogBoxController(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindowController.class.getResource("/view/DialogBox.fxml"));
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
     * Returns a DialogBoxController object representing the user's dialog box.
     * @param text Text to be displayed.
     * @param img Image to be displayed.
     * @return DialogBoxController object representing the user's dialog box.
     */
    public static DialogBoxController getUserDialog(String text, Image img) {
        return new DialogBoxController(text, img);
    }

    /**
     * Returns a DialogBoxController object representing Duke's dialog box.
     * @param text Text to be displayed.
     * @param img Image to be displayed.
     * @return DialogBoxController object representing Duke's dialog box.
     */
    public static DialogBoxController getDukeDialog(String text, Image img) {
        var db = new DialogBoxController(text, img);
        db.flip();
        return db;
    }
}
