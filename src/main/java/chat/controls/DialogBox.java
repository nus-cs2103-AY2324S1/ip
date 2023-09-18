package chat.controls;

import java.io.IOException;
import java.util.Collections;

import chat.MainWindow;
import chat.utils.Ui.ChatWrapper;
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

/**
 * DialogBox Controller for Chat GUI.
 * @author juzzztinsoong
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private Label chat;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox.
     * @param text display text for DialogBox.
     * @param img display image for DialogBox.
     */
    public DialogBox(String chatName, Color chatColor, String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        chat.setText(chatName);
        chat.setTextFill(chatColor);
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

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox("", Color.BLACK, text, img);
    }

    public static DialogBox getChatDialog(ChatWrapper chatWrapper, String text) {

        var db = new DialogBox(chatWrapper.chatName, chatWrapper.chatColor, text, chatWrapper.chatImage);
        db.flip();
        return db;
    }
}
