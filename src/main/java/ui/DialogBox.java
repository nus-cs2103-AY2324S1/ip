package ui;

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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final int MARGIN = 10;
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ChatWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setMargin(dialog, new Insets(0, MARGIN, 0, 0));
        dialog.setText(text);
        dialog.setTextFill(Color.WHITESMOKE);
        dialog.setBackground(new Background(
                new BackgroundFill(
                        Color.rgb(153, 128, 250),
                        new CornerRadii(5),
                        Insets.EMPTY
                )
        ));
        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(45, 45, 45));
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

    private void flipMargins() {
        setMargin(dialog, new Insets(0, 0, 0, MARGIN));
    }

    private void error() {
        dialog.setStyle("-fx-background-color: #4C3743");
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setStyle("-fx-background-color: #3C4841");
        db.flip();
        db.flipMargins();
        return db;
    }

    public static DialogBox getDukeErrorDialog(String text, Image img) {
        var db = getDukeDialog(text, img);
        db.error();
        return db;
    }
}
