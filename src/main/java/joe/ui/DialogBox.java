package joe.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Represents the dialog box in the Ui using FXML.
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

        displayPicture.setImage(img);

        //Round the displayPicture
        Rectangle clip = new Rectangle(
                displayPicture.getFitWidth(), displayPicture.getFitHeight()
        );
        clip.setArcWidth(50);
        clip.setArcHeight(50);
        displayPicture.setClip(clip);
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
     * Creates the dialog box for the user.
     *
     * @param text The text in the dialog.
     * @param img The image to be displayed for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setTextAlignment(TextAlignment.RIGHT);
        db.setBackgroundColor("#DDDDDD");
        return db;
    }

    /**
     * Creates the dialog box for joe.
     *
     * @param text The text in the dialog.
     * @param img The image to be displayed for joe.
     */
    public static DialogBox getJoeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setTextAlignment(TextAlignment.LEFT);
        db.flip();
        db.setBackgroundColor("#CCCCCC");
        return db;
    }

    /**
     * Sets the background color of the dialog box.
     */
    private void setBackgroundColor(String color) {
        this.setStyle("-fx-background-color: " + color + ";");
    }

}
