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



    private static Image userImage = new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/profile.png")));
    private static Image eggImage = new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/egg.png")));

    private DialogBox(String text, String user, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        userName.setText(user);
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

    public static DialogBox getUserDialog(String text) {
        return new DialogBox(text, "You:", userImage);
    }

    public static DialogBox getEggbotDialog(String text) {
        var db = new DialogBox(text, "Eggbot:", eggImage);
        db.flip();
        return db;
    }
}
