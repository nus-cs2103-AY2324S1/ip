package evo;

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
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text and image.
     *
     * @param text  The text content to be displayed in the dialog box.
     * @param image The image to represent the speaker.
     */
    public DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(image);
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
     * Creates a user dialog box with the specified text and user's image.
     *
     * @param text  The text content to be displayed in the dialog box.
     * @param image The image to represent the user.
     * @return A DialogBox representing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image image) {
        return new DialogBox(text, image);
    }

    /**
     * Creates an Evo dialog box with the specified text and Evo's image.
     * The dialog box is flipped to display Evo's dialog.
     *
     * @param text  The text content to be displayed in the dialog box.
     * @param image The image to represent Evo.
     * @return A DialogBox representing Evo's dialog.
     */
    public static DialogBox getEvoDialog(String text, Image image) {
        var dialogBox = new DialogBox(text, image);
        dialogBox.flip();
        return dialogBox;
    }
}
