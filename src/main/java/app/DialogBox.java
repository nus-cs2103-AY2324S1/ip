package app;import java.io.IOException;
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

        // add padding between image and text and below the dialog box
        dialog.setStyle("-fx-padding: 10 10 10 10;");

    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public void setBackgroundColor(String color) {
        this.setStyle("-fx-background-color: " + color + "; -fx-padding: 10 0 0 0;");
    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox box = new DialogBox(text, img);
        box.setBackgroundColor("#e6f2ff");
        return box;
    }

    public static DialogBox getBotDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setBackgroundColor("#e6ffe6");
        return db;
    }

}

