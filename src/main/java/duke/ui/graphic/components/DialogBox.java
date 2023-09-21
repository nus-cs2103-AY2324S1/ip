package duke.ui.graphic.components;

import java.io.IOException;

import duke.ui.graphic.MainWindow;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Represents a dialog box, which is a message within the GUI.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dialog.setText(text);
        this.displayPicture.setImage(img);

        if (!isUser) {
            this.setAlignment(Pos.TOP_LEFT);
            ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
            FXCollections.reverse(tmp);
            this.getChildren().setAll(tmp);
            this.dialog.setFont(new Font("Consolas", this.dialog.getFont().getSize()));
        }
    }

    /**
     * Instantiate a new dialog box for user.
     * @param l The message from user.
     * @param iv User's representative image.
     * @return A new instance of DialogBox for user.
     */
    public static DialogBox getUserDialog(String l, Image iv) {
        return new DialogBox(l, iv, true);
    }

    /**
     * Instantiate a new dialog box for bot.
     * @param l The message from bot.
     * @param iv Bot's representative image.
     * @return A new instance of DialogBox for bot.
     */
    public static DialogBox getDukeDialog(String l, Image iv) {
        return new DialogBox(l, iv, false);
    }

    /**
     * Instantiate a new dialog box for bot to notify an error.
     * @param l The message from the bot.
     * @param iv Bot's representative image.
     * @return A new instance of DialogBox for notifying error.
     */
    public static DialogBox getDukeErrorDialog(String l, Image iv) {
        DialogBox dialogBox = new DialogBox(l, iv, false);
        dialogBox.dialog.setTextFill(Color.rgb(235, 7, 7));
        return dialogBox;
    }
}
