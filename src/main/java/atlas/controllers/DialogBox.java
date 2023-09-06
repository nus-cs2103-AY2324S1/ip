package atlas.controllers;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This control represents a dialog box consisting of an ImageView to represent
 * the speaker's face and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    static final double PROFILE_RADIUS = 30.0;
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a Dialog Box containing the speaker's profile image and speaker's text.
     * @param text Text for the speaker to speak
     * @param img Speaker's profile image
     */
    public DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Circle profileClip = new Circle(PROFILE_RADIUS, PROFILE_RADIUS, PROFILE_RADIUS);
        displayPicture.setClip(profileClip);
        displayPicture.setImage(img);

        dialog.setText(text);
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

    /**
     * Returns a dialog box for the user. The profile image is on the right and text on the left.
     * @param text Input string
     * @param img User's profile image
     * @return Dialog Box for user
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.dialog.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, new CornerRadii(10), null)));
        return db;
    }

    /**
     * Returns a dialog box for Atlas. The profile image is on the left and text on the right.
     * @param text Atlas's output
     * @param img Atlas's profile image
     * @return Dialog box for Atlas
     */
    public static DialogBox getAtlasDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        db.dialog.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), null)));
        return db;
    }
}
