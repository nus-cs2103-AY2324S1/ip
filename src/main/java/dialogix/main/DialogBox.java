package dialogix.main;

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
import javafx.scene.layout.Region;

/**
 * Represents a custom dialog box used for displaying messages in the user interface.
 * This dialog box consists of an ImageView for displaying an avatar or image, and a Label
 * for displaying text content.
 */
class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a dialog box with the specified text content and image.
     *
     * @param text The text content to display in the dialog box.
     * @param img  The image or avatar to display in the dialog box.
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
        setMinHeight(Region.USE_PREF_SIZE);
        displayPicture.setImage(img);
    }
    /**
     * Creates and returns a user dialog box with the specified text content and image.
     *
     * @param text The text content to display in the user's dialog box.
     * @param img  The image or avatar to display for the user.
     * @return A user dialog box.
     */
    static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates and returns a dialogix (bot) dialog box with the specified text content and image.
     *
     * @param text The text content to display in the dialogix's dialog box.
     * @param img  The image or avatar to display for dialogix.
     * @return A dialogix (bot) dialog box.
     */
    static DialogBox getDialogixDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        ObservableList<Node> tmp = FXCollections.observableArrayList(db.getChildren());
        Collections.reverse(tmp);
        db.getChildren().setAll(tmp);
        db.setAlignment(Pos.TOP_LEFT);
        return db;
    }
}
