package zean.gui;

import java.io.IOException;
import java.util.Collections;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
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
        displayPicture.setClip(new Circle(25.0, 25.0, 25.0));
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
     * Sets the color of the DialogBox.
     *
     * @param bgFill The BackgroundFill object containing the color information.
     */
    private void setColor(BackgroundFill bgFill) {
        this.dialog.setBackground(new Background(bgFill));
    }

    /**
     * Returns a DialogBox containing the user image and the user input text.
     *
     * @param text The user input text
     * @param img The Image object representing the user image.
     * @return A DialogBox containing the user image and the user input text.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        BackgroundFill userBg = new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), Insets.EMPTY);
        DialogBox userDialogBox = new DialogBox(text, img);
        userDialogBox.setColor(userBg);
        return userDialogBox;
    }

    /**
     * Returns a DialogBox containing the image of zean and the response.
     *
     * @param text The response from zean
     * @param img The Image object representing zean.
     * @return A DialogBox containing the image of zean and the response.
     */
    public static DialogBox getZeanDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        BackgroundFill botBg = new BackgroundFill(Color.BEIGE, new CornerRadii(10), Insets.EMPTY);
        db.setColor(botBg);
        return db;
    }

    /**
     * Returns an orange DialogBox containing the image of zean and the error message.
     *
     * @param text The response from zean
     * @param img The Image object representing zean.
     * @return A DialogBox containing the image of zean and the error message.
     */
    public static DialogBox getErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        BackgroundFill botBg = new BackgroundFill(Color.ORANGE, new CornerRadii(10), Insets.EMPTY);
        db.setColor(botBg);
        return db;
    }
}
