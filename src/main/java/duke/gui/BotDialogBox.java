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
public class BotDialogBox extends HBox {
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
    public BotDialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/BotDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setClip(new javafx.scene.shape.Circle(35, 35, 35));
    }

    /**
     * Returns a DialogBox object representing Bots dialog box.
     *
     * @param text Text to be displayed in the dialog box.
     * @param img Image to be displayed in the dialog box.
     * @return DialogBox object representing Bots dialog box.
     */
    public static BotDialogBox getBotDialog(String text, Image img) {
        return new BotDialogBox(text, img);
    }
}
