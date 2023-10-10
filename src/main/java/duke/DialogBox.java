package duke;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    private static final Ui ui = new Ui();
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    /**
     * Creates a DialogBox with the specified text and image.
     *
     * @param text The text to display in the dialog box.
     * @param img  The image to display in the dialog box.
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
        dialog.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
        dialog.setMinWidth(260);
        displayPicture.setImage(img);
        setAlignment(Pos.CENTER);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
    }

    /**
     * Sets the background of the dialog box to a user-specific color.
     */
    private void setUserBackground() {
        setBackground(new Background(new BackgroundFill(Color.rgb(173, 216, 230), new CornerRadii(12.00), null)));
    }

    /**
     * Sets the background of the dialog box to a Duke-specific color.
     */
    private void setDukeBackground() {
        setBackground(new Background(new BackgroundFill(Color.rgb(230, 230, 250), new CornerRadii(12.00), null)));
    }

    /**
     * Creates and returns a DialogBox for user messages with the specified text and image.
     *
     * @param text The text to display in the user's dialog box.
     * @param img  The image to display in the user's dialog box.
     * @return A DialogBox configured for user messages.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        text = ui.formatText(text);
        var db = new DialogBox(text, img);
        db.setUserBackground();
        return db;
    }

    /**
     * Creates and returns a DialogBox for Duke's messages with the specified text and image.
     *
     * @param text The text to display in Duke's dialog box.
     * @param img  The image to display in Duke's dialog box.
     * @return A DialogBox configured for Duke's messages.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        text = ui.formatText(text);
        var db = new DialogBox(text, img);
        db.flip();
        db.setDukeBackground();
        return db;
    }
}
