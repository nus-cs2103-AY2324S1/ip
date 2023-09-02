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
import javafx.scene.shape.Circle;

/**
 * Represents a custom UI component for displaying dialog messages in the Duke application.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Private constructor for the DialogBox class. It initializes the UI components and sets their properties.
     *
     * @param text The text message to display in the dialog.
     * @param img  The image to display in the dialog.
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

        Circle clipCircle = new Circle();
        clipCircle.radiusProperty().bind(displayPicture.fitWidthProperty().divide(2));
        clipCircle.centerXProperty().bind(displayPicture.fitWidthProperty().divide(2));
        clipCircle.centerYProperty().bind(displayPicture.fitHeightProperty().divide(2));
        displayPicture.setClip(clipCircle);
    }

    /**
     * Creates a new DialogBox representing a user's message.
     *
     * @param text The text message to display.
     * @param img  The user's profile image.
     * @return A DialogBox with the user's message and profile image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a new DialogBox representing Duke's response.
     *
     * @param text The text message to display.
     * @param img  Duke's profile image.
     * @return A DialogBox with Duke's message and profile image.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
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
}
