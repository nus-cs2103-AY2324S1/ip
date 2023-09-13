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
import javafx.scene.layout.HBox;

/**
 * The DialogBox class represents dialog.
 */
public class DialogBox extends HBox {
    /** The text attribute represents the text message **/
    @FXML
    private Label dialog;
    /** The picture of the chatbot **/
    @FXML
    private ImageView displayPicture;

    /**
     * Initiates an instance of the dialog.
     *
     * @param text The text.
     * @param img The image.
     */
    public DialogBox(String text, Image img) {
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
     * Flips the chatbot to the right side.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_RIGHT);
    }

    /**
     * Returns an instance of DialogBox containing the user's text and image.
     *
     * @param text The user's input typed in.
     * @param img The user's profile image.
     * @return Instance of DialogBox for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns an instance of DialogBox containing the user's text and image.
     *
     * @param response The response of the chatbot.
     * @param img The profile image of the bot.
     * @return Instance of DialogBox for the user.
     */
    public static DialogBox getBotDialog(String response, Image img) {
        var db = new DialogBox(response, img);
        db.flip();
        return db;
    }
}
