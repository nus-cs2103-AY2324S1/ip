package duke.ui;

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

/**
 * DialogBox Controller.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/duke/ui/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
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

    /**
     * Returns a User DialogBox.
     *
     * @param text user text
     * @param img user image
     * @return a DialogBox formatted for the user text
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns a Duke DialogBox.
     * @param text duke message
     * @param img duke image
     * @return a DialogBox formatted for the duke text
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setStyle("-fx-background-color: #E0E0E0");
        db.flip();
        return db;
    }

    /**
     * Overloaded function to return Duke DialogBox with Colored Text.
     *
     * @param text duke message
     * @param img duke image
     * @param color text color
     * @return a DialogBox formatted for the duke text with colored text
     */
    public static DialogBox getDukeDialog(String text, Image img, String color) {
        DialogBox db = new DialogBox(text, img);
        db.setStyle("-fx-background-color: #E0E0E0");
        db.dialog.setTextFill(Color.valueOf(color));
        db.flip();
        return db;
    }
}
