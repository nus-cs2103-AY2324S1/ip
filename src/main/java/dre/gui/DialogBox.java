package dre.gui;

import java.io.IOException;
import java.util.Collections;

import dre.MainWindow;
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
 * A custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a new instance of the DialogBox with specified text and image.
     *
     * @param text The content for the dialog.
     * @param img The image to be used as a display picture.
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
     * Adjusts the layout of the dialog box by swapping the positions of the ImageView and text.
     * Places the ImageView to the left and the text to the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a DialogBox for user's dialog.
     *
     * @param text The content of the user's dialog.
     * @param img The image to represent the user.
     * @return A DialogBox tailored for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a DialogBox for Dre's dialog.
     * The produced DialogBox will have its layout adjusted such that Dre's image is on the left.
     *
     * @param text The content of Dre's dialog.
     * @param img The image to represent Dre.
     * @return A DialogBox tailored for Dre.
     */
    public static DialogBox getDreDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
