package barbie;

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
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

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
    @FXML
    private Rectangle roundedRectangle;


    /**
     * Constructs an instance of a DialogBox.
     * @param text text of the DialogBox
     * @param img profile picture of the person
     */
    private DialogBox(String text, Image img, boolean isBarbie) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        dialog.setPadding(new Insets(0, 10, 0, 10));
        Circle circle = new Circle();
        circle.setRadius(50.0);
        circle.setCenterX(50.0);
        circle.setCenterY(50.0);

        displayPicture.setImage(img);
        displayPicture.setClip(circle);
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
     * Gets the DialogBox for the user.
     * @param text user's input
     * @param img user's profile picture
     * @return DialogBox object for the user
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, false);
    }

    /**
     * Gets the DialogBox for Barbie.
     * @param text Barbie's response
     * @param img Barbie's profile picture
     * @return DialogBox object for Barbie
     */
    public static DialogBox getDukeDialog(String text, Image img ) {
        var db = new DialogBox(text, img, true);
        db.flip();
        return db;
    }
}
