package ekud.ui;
// DialogBox.java is taken from https://se-education.org/guides/tutorials/javaFxPart4.html
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
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 * (https://se-education.org/guides/tutorials/javaFxPart4.html)
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    private static final String USER_DIALOG_BG_COLOR = "#8FFFA1";
    private static final String DUKE_DIALOG_BG_COLOR = "#CFBCFF";

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

        // Clip display picture to a circle, credits to
        // https://stackoverflow.com/questions/20708295/put-a-image-in-a-circle-view
        Circle circleClip = new Circle(50, 50, 48);
        displayPicture.setClip(circleClip);
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
     * Returns a dialog box for the user.
     *
     * @param text User input.
     * @param img User display picture.
     * @return DialogBox
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        // Rounded corners styling for the dialog prop, credits to
        // https://stackoverflow.com/questions/33421353/java-fx-8-dialog-rounded-corners
        db.dialog.setStyle(String.format(
                "-fx-background-color: %s; -fx-background-radius: 15 15 15 15;"
                + "-fx-padding: 12 12 12 12; -fx-margin: 0 100 0 0",
                USER_DIALOG_BG_COLOR));
        db.dialog.setMinWidth(50);
        // Change the alignment of content within props using setAlignment() of the respective prop, credits to
        // https://docs.oracle.com/javafx/2/layout/size_align.htm#:~:text=setAlignment(Pos.,shown%20in%20Figure%202%2D5.
        db.dialog.setAlignment(Pos.CENTER);
        // Align the user dialog to the center right w.r.t. the dialog box
        db.setAlignment(Pos.CENTER_RIGHT);
        // Set left padding in dialog box so user dialog has some space from the left
        db.setStyle("-fx-padding: 5 5 5 50;");
        return db;
    }

    /**
     * Returns a dialog box for the chatbot.
     *
     * @param text Chatbot response.
     * @param img Chatbot display picture.
     * @return DialogBox
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialog.setStyle(String.format(
                "-fx-background-color: %s; -fx-background-radius: 20 20 20 20;"
                + "-fx-padding: 12 12 12 12; -fx-margin: 0 100 0 0",
                DUKE_DIALOG_BG_COLOR));
        // Align the ekud dialog to the centre left w.r.t the dialog box
        db.setAlignment(Pos.CENTER_LEFT);
        // Set right padding in dialog box so ekud dialog has some space from the right
        db.setStyle("-fx-padding: 5 50 5 5;");
        return db;
    }
}

