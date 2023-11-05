package Duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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
 * A custom control representing a dialog box in a chat interface.
 * This control consists of an ImageView to display an image and a Label to display text.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for the DialogBox.
     *
     * @param text The text content to be displayed in the dialog.
     * @param img  The image to be displayed in the dialog.
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
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box to change the alignment of elements (ImageView and Label).
     * This is used to distinguish between user and Duke (bot) dialogs.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Create a DialogBox for user messages.
     *
     * @param text The text content of the user's message.
     * @param img  The user's image.
     * @return A DialogBox containing the user's message and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        assert !text.isEmpty();
        return new DialogBox(text, img);
    }

    /**
     * Create a DialogBox for Duke's (bot) messages.
     *
     * @param text The text content of Duke's message.
     * @param img  Duke's image.
     * @return A DialogBox containing Duke's message and image.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        assert !text.isEmpty();
        var db = new DialogBox(text, img);
        db.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,
                CornerRadii.EMPTY, Insets.EMPTY)));
        db.flip();
        return db;
    }
}
