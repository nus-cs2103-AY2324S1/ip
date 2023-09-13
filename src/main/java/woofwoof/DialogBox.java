package woofwoof;

import java.io.IOException;

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
 * The `DialogBox` class represents a graphical dialog box for displaying messages in the GUI application.
 * It provides methods for creating user and application dialog boxes, flipping their alignment,
 * and setting text wrapping.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Private constructor to create a new `DialogBox` with a label and an image view.
     *
     * @param text The text content to be displayed in the dialog box.
     * @param img  The image to be displayed in the dialog box.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(WoofWoof.class.getResource("/views/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.dialog = new Label(text);
        this.dialog.setWrapText(true);
        this.displayPicture = new DisplayPictureImageView(img);
        this.displayPicture.setFitWidth(100);
        this.displayPicture.setFitHeight(100);
        this.getChildren().addAll(this.dialog, this.displayPicture);
    }

    /**
     * Flips the dialog box, changing the alignment to place the ImageView on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates a new user dialog box.
     *
     * @param text The text content to be displayed in the user dialog box.
     * @param img  The image to be displayed in the user dialog box.
     * @return A new `DialogBox` representing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a new dialog box and flips it to place the ImageView on the left and text on the right.
     *
     * @param text The text content to be displayed in the application dialog box.
     * @param img  The image to be displayed in the application dialog box.
     * @return A new `DialogBox` representing the application's dialog.
     */
    public static DialogBox getBotDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
