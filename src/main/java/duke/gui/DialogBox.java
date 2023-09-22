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
     * Constructs a DialogBox with specified text and image.
     *
     * @param text the text to be displayed in the dialog box.
     * @param img the image to be displayed in the dialog box.
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
     * Creates a user dialog box with specified text and image.
     *
     * @param text the text to be displayed as user's dialog.
     * @param img the image to be displayed representing the user.
     * @return a new DialogBox object representing user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.setStyle("-fx-background-color: #FFCCCC");
        return dialogBox;
    }

    /**
     * Creates a Adam' Chat Bot dialog box with specified text and image.
     * The dialog box is flipped to distinguish
     * Adam's dialog from the user's.
     *
     * @param text the text to be displayed as Adam's dialog.
     * @param img the image to be displayed representing Adam.
     * @return a new DialogBox object representing Adam's dialog.
     */
    public static DialogBox getAdamDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setStyle("-fx-background-color: #FFFFCC");
        return db;
    }
}
