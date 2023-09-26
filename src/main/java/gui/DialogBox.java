package gui;

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
import javafx.scene.shape.Circle;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
        clipToCircle(displayPicture);
    }

    /**
     * Adds circular border to ImageView object for profile picture .
     *
     * @param img ImageView object to be modified.
     */
    private static void clipToCircle(ImageView img) {
        Circle circle = new Circle(33);
        circle.setCenterX(img.getFitWidth() / 2);
        circle.setCenterY(img.getFitHeight() / 2);
        img.setClip(circle);
    }

    /**
     * Generates DialogBox for user input display.
     *
     * @param text User text input.
     * @param img Profile picture of user.
     * @return DialogBox object for GUI display.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setStyle("-fx-background-color: #FFF8DC ;");
        return db;
    }

    /**
     * Generates DialogBox for chatbot input display.
     *
     * @param text Chatbot text output.
     * @param img Profile picture of chatbot.
     * @return DialogBox object for GUI display.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
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
}
