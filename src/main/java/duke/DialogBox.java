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
 * Represents the Dialog box that contains the profile picture and the text.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String textString, Image image, boolean isBot) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/views/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(textString);
        displayPicture.setImage(image);
        if (isBot) {
            flip();
        }
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
     * Returns a dialog box for the user.
     * @param text The input text from the user
     * @param displayPicture The display picture of the user
     * @return The dialog box for the user
     */
    public static DialogBox getUserDialogBox(String text, Image displayPicture) {
        return new DialogBox(text, displayPicture, false);
    }

    /**
     * Returns a dialog box for the chatbot.
     * @param text The response text from the chatbot
     * @param displayPicture The display picture of the chatbot
     * @return The dialog box of the chatbot
     */
    public static DialogBox getDukeDialogBox(String text, Image displayPicture) {
        return new DialogBox(text, displayPicture, true);
    }
}
