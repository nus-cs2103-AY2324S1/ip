package duke.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a custom dialog box consisting of an ImageView to represent
 * the speaker's <i>(user and bot)</i> icon and a label containing text from the speaker.
 */
public class UserDialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox object.
     *
     * @param text Text to be displayed in the dialog box.
     * @param img Image to be displayed in the dialog box.
     */
    public UserDialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/UserDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setClip(new javafx.scene.shape.Circle(25, 25, 25));
    }

    /**
     * Returns a UserDialogBox object representing the user's dialog box.
     *
     * @param text Text to be displayed in the dialog box.
     * @param img Image to be displayed in the dialog box.
     * @return DialogBox object representing the user's dialog box.
     */
    public static UserDialogBox getUserDialog(String text, Image img) {
        return new UserDialogBox(text, img);
    }
}
