package ui;

import java.io.IOException;
import java.util.Collections;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent
 * the speaker's face and a label
 * containing text from the speaker.
 *
 * @author Ho Khee Wei
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

        displayPicture.setImage(img);
        dialog.setText(text);
        setupDisplayPicture();
        setupLabel();

        this.getStyleClass().add("dialog-box");
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the
     * right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Sets the style attributes of the display picture.
     */
    private void setupDisplayPicture() {
        Circle clip = new Circle(displayPicture.getFitWidth() / 2, displayPicture.getFitHeight() / 2, 25);
        displayPicture.setClip(clip);
    }

    /**
     * Sets the style attributes of the label.
     */
    private void setupLabel() {
        dialog.setPadding(new Insets(0, 15.0, 5.0, 15.0));
        dialog.setMaxHeight(Double.MAX_VALUE);
    }

    /**
     * Constructs the dialog box for the user's message.
     *
     * @param text Text to display in the diaglog box.
     * @param img  The image of the user.
     * @return the user diaglog box with the message and user's image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.getStyleClass().add("user-dialog");
        return db;
    }

    /**
     * Constructs the dialog box for Thorndike's message.
     *
     * @param text Text to display in the diaglog box.
     * @param img  The image of the Thorndike.
     * @return the Thorndike diaglog box with the message and Thorndike's image.
     */
    public static DialogBox getThorndikeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialog.getStyleClass().add("thorndike-dialog");

        db.setOpacity(0);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), db);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setDelay(Duration.seconds(0.2));

        fadeTransition.play();

        return db;
    }
}
