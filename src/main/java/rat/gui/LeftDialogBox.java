package rat.gui;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A custom control component using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 * @author Keagan
 */
public class LeftDialogBox extends HBox {

    /**
     * The label containing text from the speaker.
     */
    @FXML
    private Label dialog;

    /**
     * The ImageView to represent the speaker's face.
     */
    @FXML
    private ImageView displayPicture;

    /**
     * The label containing the time of the message.
     */
    @FXML
    private Label time;

    /**
     * Constructs a DialogBox.
     *
     * @param text The text from the speaker.
     * @param img  The image of the speaker.
     */
    private LeftDialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/LeftDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setImage(img);
        time.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
    }

    /**
     * Factory method for creating a LeftDialogBox.
     * @param text The text from the speaker.
     * @param img The image of the speaker.
     */
    public static LeftDialogBox getRatDialog(String text, Image img) {
        return new LeftDialogBox(text, img);
    }

}

