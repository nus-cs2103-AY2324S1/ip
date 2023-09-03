
package duke.uiux;

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
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
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
    private Circle profile;

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
        profile.setFill(new ImagePattern(img));
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
     * Returns a DialogBox with the user's text and image
     * @param text  the user's text
     * @param img   the user's image
     * @return  a DialogBox with the user's text and image
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox user = new DialogBox(text, img);
        user.dialog.setStyle("-fx-padding: 30 15 30 15; -fx-background-color: lightgreen;");
        return user;
    }

    /**
     * Returns a DialogBox with Duke's text and image
     * @param text  Duke's text
     * @param img   Duke's image
     * @return  a DialogBox with Duke's text and image
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setStyle("-fx-background-color: lightgrey; -fx-padding: 0 10 0 10;");
        db.flip();
        return db;
    }
}