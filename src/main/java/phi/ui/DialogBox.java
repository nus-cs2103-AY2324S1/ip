package phi.ui;

import java.io.IOException;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Text dialog;
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
        dialog.setTextAlignment(TextAlignment.RIGHT);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        dialog.setTextAlignment(TextAlignment.LEFT);
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    //@@author phiphi-tan-reused
    //Reused from https://github.com/sunzihan23/ip/blob/master/src/main/java/duke/ui/DialogBox.java
    //with minor modifications
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setBackground(new Background(
                new BackgroundFill(Color.color(0.85, 0.9, 1),
                        new CornerRadii(15),
                        new Insets(5, 15, 5, 15))));
        return db;
    }

    public static DialogBox getPhiDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setBackground(new Background(
                new BackgroundFill(Color.color(1, 0.925, 0.925),
                        new CornerRadii(15),
                        new Insets(5, 15, 5, 15))));
        return db;
    }
    //@@author
}