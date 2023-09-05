package smolbrain;

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
 * Custom BotDialog control, used to display a dialog from the chatbot.
 */
public class BotDialog extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private BotDialog(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/BotDialog.fxml"));
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
     * Takes in a string input and an image to create a dialog for a bot.
     *
     * @param text Input string for dialog.
     * @param img  Input image for dialog.
     * @return A BotDialog object.
     */
    public static BotDialog getDialog(String text, Image img) {
        var db = new BotDialog(text, img);
        db.flip();
        return db;
    }
}
