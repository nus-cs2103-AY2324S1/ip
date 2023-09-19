package duke.gui;

import java.io.IOException;

import duke.util.Response;
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
     * @param response Response to be displayed in the dialog box.
     * @param img Image to be displayed in the dialog box.
     */
    public BotDialogBox(Response response, Image img) {
        try {
            String fxmlPath = response.isErrorResponse()
                    ? "/view/ErrorDialogBox.fxml"
                    : "/view/BotDialogBox.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(fxmlPath));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(response.toString());
        displayPicture.setImage(img);
        displayPicture.setClip(new javafx.scene.shape.Circle(25, 25, 25));
    }

    /**
     * Returns a DialogBox object representing Bots dialog box.
     *
     * @param response Response to be displayed in the dialog box.
     * @param img Image to be displayed in the dialog box.
     * @return DialogBox object representing Bots dialog box.
     */
    public static BotDialogBox getBotDialog(Response response, Image img) {
        return new BotDialogBox(response, img);
    }
}
