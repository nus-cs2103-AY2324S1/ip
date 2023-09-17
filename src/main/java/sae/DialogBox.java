package sae;

import java.io.IOException;
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
import java.util.Collections;


/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 * This has been reused (with minor changes)
 * from <a href="https://se-education.org/guides/tutorials/javaFx.html">tutorial</a>
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Calls constructor for DialogBox.
     *
     * @param text Text to display.
     * @param img  Image to display.
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
     * Creates a user dialog box with the given text and image.
     *
     * @param text The text to display in the dialog box.
     * @param img  The image to display in the dialog box.
     * @return A DialogBox containing the user's message and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a Sae dialog box with the given text and image.
     * This dialog box is typically used for Sae's responses.
     *
     * @param text The text to display in the dialog box.
     * @param img  The image to display in the dialog box.
     * @return A DialogBox containing Sae's message and image.
     */
    public static DialogBox getSaeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
