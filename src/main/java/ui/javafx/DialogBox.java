package ui.javafx;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Text dialog;
    @FXML
    private Label userName;
    @FXML
    private ImageView displayPicture;
    @FXML
    private VBox bubble;

    private static String USER_BUBBLE_COLOR = " #ff6c55";
    private static String EGGBOT_BUBBLE_COLOR = " #173e5b";

    private static Image userImage = new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/profile.png")));
    private static Image eggImage = new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/egg.png")));

    private DialogBox(String text, String user, Image img, String color) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/dialogbox/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        userName.setText(user);
        displayPicture.setImage(img);
        bubble.setStyle("-fx-background-color: " + color + "; -fx-background-radius: 25 25 25 25; -fx-background-radius: 25 25 25 25;");
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

    public static DialogBox getUserDialog(String text) {
        DialogBox userDb = new DialogBox(text, "You:", userImage, USER_BUBBLE_COLOR);
        userDb.flip();
        return userDb;
    }

    public static DialogBox getEggbotDialog(String text) {
        var db = new DialogBox(text, "Eggbot:", eggImage, EGGBOT_BUBBLE_COLOR);
        return db;
    }
}
