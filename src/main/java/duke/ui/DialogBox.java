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
 * Represents a custom control using FXML.
 * This control creates a dialog box containing an ImageView for the speaker's face and a label for the speaker's text.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Private constructor to initialize the dialog box with specified text and image.
     *
     * @param text The message text.
     * @param img The image for the dialog box.
     */
    private DialogBox(String text, Image img) {
        loadFxml();
        initializeComponents(text, img);
    }

    private void loadFxml() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the components of the dialog box.
     *
     * @param text The message text.
     * @param img The image for the dialog box.
     */
    private void initializeComponents(String text, Image img) {
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box orientation: ImageView to the left and text to the right.
     */
    private void flip() {
        ObservableList<Node> elements = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(elements);
        getChildren().setAll(elements);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a user dialog box.
     *
     * @param text The message text.
     * @param img The image for the dialog box.
     * @return A dialog box for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a Duke dialog box and reverses its orientation.
     *
     * @param text The message text.
     * @param img The image for the dialog box.
     * @return A flipped dialog box for Duke.
     */
    public static DialogBox getServerDialog(String text, Image img) {
        DialogBox serverDialogBox = new DialogBox(text, img);
        serverDialogBox.flip();
        return serverDialogBox;
    }
}
