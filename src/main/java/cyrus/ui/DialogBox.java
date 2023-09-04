package cyrus.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Dialog box to display bot/user text.
 */
public class DialogBox extends VBox {
    @FXML
    private HBox userInfo;
    @FXML
    private Label dialog;
    @FXML
    private Label username;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor to create a DialogBox dynamically.
     *
     * @param text text to display.
     * @param user username of person sending the message.
     * @param img  image to display for the user.
     */
    public DialogBox(String text, String user, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        username.setText(user);
        displayPicture.setImage(img);
    }

    public static DialogBox getDialog(String text, String user, Image img) {
        return new DialogBox(text, user, img);
    }
}
