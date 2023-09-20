package arona;

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
* This control represents a dialog box in the Arona application's user interface. Each dialog box consists of
* an ImageView to represent the speaker and a label containing text from the speaker.
*/
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private MessageType messageType;

    /**
     * Enum to define message types (User or Arona).
     */
    public enum MessageType {
        USER, ARONA
    }

    /**
     * Sets the message type for the dialog box.
     *
     * @param messageType The message type (USER or ARONA).
     */
    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
        setDialogStyle();
    }

    /**
     * Sets the visual style of the dialog box based on its message type.
     * If the message type is USER, it applies the user dialog box style.
     * If the message type is ARONA, it applies the Arona dialog box style.
     */
    private void setDialogStyle() {
        if (messageType == MessageType.USER) {
            dialog.getStyleClass().clear();
            dialog.getStyleClass().add("user-dialog-box");
        } else if (messageType == MessageType.ARONA) {
            dialog.getStyleClass().clear();
            dialog.getStyleClass().add("arona-dialog-box");
        }
    }

    /**
     * Creates a new dialog box with the specified text and image.
     *
     * @param text The text content of the dialog.
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
     * Creates and returns a dialog box representing user input.
     *
     * @param text The text content of the user's message.
     * @param img  The image representing the user.
     * @return A dialog box representing the user's message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        return db;
    }

    /**
     * Creates and returns a dialog box representing Arona's response.
     *
     * @param text The text content of Arona's message.
     * @param img  The image representing Arona.
     * @return A dialog box representing Arona's message.
     */
    public static DialogBox getAronaDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
