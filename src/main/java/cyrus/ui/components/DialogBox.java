package cyrus.ui.components;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
    public DialogBox(String text, String user, Image img, Color color) {
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
        dialog.getStyleClass().add(user);
        displayPicture.setImage(img);
        dialog.setTextFill(color);
    }

    /**
     * Generates instance of {@code DialogBox} given arguments.
     *
     * @param text text to include in body of {@code DialogBox}.
     * @param user user's name.
     * @param img  user's image.
     * @return {@code DialogBox} instance.
     */
    public static DialogBox getDialog(String text, String user, Image img) {
        return new DialogBox(text, user, img, Color.BLACK);
    }

    /**
     * Generates instance of {@code DialogBox} given arguments.
     *
     * <p>Supports changing of text color.</p>
     *
     * @param text text to include in body of {@code DialogBox}.
     * @param user user's name.
     * @param img  user's image.
     * @param color {@code text} color.
     * @return {@code DialogBox} instance.
     */
    public static DialogBox getDialog(String text, String user, Image img, Color color) {
        return new DialogBox(text, user, img, color);
    }
}
