package trackerbot.gui;

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
 * Represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 * <p>Skeleton code from the JavaFX tutorial provided.</p>
 *
 * @version Level-10
 */
public class DialogBox extends HBox {
    /** Label for the DialogBox. */
    @FXML
    private Label dialog;

    /** Avatar image for the DialogBox. */
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs the DialogBox controller object.
     * @param text The description to display in the box.
     * @param img The image of the avatar.
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

        Circle circleClip = new Circle(
                displayPicture.getLayoutX() + displayPicture.getFitWidth() / 2,
                displayPicture.getLayoutY() + displayPicture.getFitHeight() / 2,
                displayPicture.getFitWidth() / 2
        );

        displayPicture.setClip(circleClip);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * Creates a user dialog box, to represent the user input.
     *
     * @param text The description to display in the box.
     * @param img The image of the avatar.
     * @return The constructed DialogBox object.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a bot dialog box, to represent the TrackerBot reply.
     *
     * @param text The description to display in the box.
     * @param img The image of the avatar.
     * @return The constructed DialogBox object, flipped by the flip method.
     */
    public static DialogBox getBotDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
