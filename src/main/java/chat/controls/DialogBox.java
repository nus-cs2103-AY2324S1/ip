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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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
     * @param chatName display name for chat.
     * @param chatColor display name color for chat.
     * @param text display text for DialogBox.
     * @param img display image for DialogBox.
     * @param isChat whether the dialog box is printed by chat.
     */
    public DialogBox(String chatName, Color chatColor, String text, Image img, boolean isChat) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isChat) {
            super.setBackground(new Background(new BackgroundFill(Color.web("#333333"), null, null)));
        } else {
            super.setBackground(new Background(new BackgroundFill(Color.web("#444444"), null, null)));
        }
        setLabelStyle(chat, chatName, chatColor);
        setLabelStyle(dialog, text, Color.WHITE);
        
        chat.setMinWidth(Region.USE_PREF_SIZE);
        dialog.setMinHeight(Region.USE_PREF_SIZE);
        displayPicture.setImage(img);
    }

    /**
     * Helper function that sets label text and color.
     * @param label Label being modified.
     * @param text Label text.
     * @param color Label color.
     */
    public static void setLabelStyle(Label label, String text, Color color) {
        label.setText(text);
        label.setTextFill(color);
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
        var db = new DialogBox("", Color.WHITE, text, img, false);
        return db;
    }

    public static DialogBox getChatDialog(ChatWrapper chatWrapper, String text) {

        var db = new DialogBox(chatWrapper.chatName, chatWrapper.chatColor, text, chatWrapper.chatImage, true);
        db.flip();
        return db;
    }
}
