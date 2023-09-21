package duke;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
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
 * A custom control representing a dialog box in the Duke application.
 * It consists of an ImageView for the speaker's image and a label for the text.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a new DialogBox with the given text and image.
     *
     * @param text The text content of the dialog box.
     * @param img  The image representing the speaker.
     */
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

        //Size of image and dialog properties
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
//        dialog.setWrapText(true);

        //Circle Clip for displayPicture
        Circle clipCircle = new Circle(50);
        displayPicture.setClip(clipCircle);
        clipCircle.setCenterX(displayPicture.getFitWidth() / 2);
        clipCircle.setCenterY(displayPicture.getFitHeight() / 2);

        // Padding and Setting for DialogBox
        this.setAlignment(Pos.TOP_RIGHT);
        this.setPadding(new Insets(20)); //Padding around DialogBox
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
     * Constructs a new DialogBox with the given text and image.
     *
     * @param text The text content of the dialog box.
     * @param img  The image representing the speaker.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db =  new DialogBox(text, img);

        //Background for text-part only
        db.dialog.setBackground(new Background(new BackgroundFill(Color.web("#ABEB9B"), null, null)));
        return db;
    }

    /**
     * Creates a DialogBox for Duke's responses.
     *
     * @param text The text content of Duke's response.
     * @param img  The image representing Duke.
     * @return A DialogBox for Duke's response.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();

        //Background for text-part only
        db.dialog.setBackground(new Background(new BackgroundFill(Color.web("#BFD2EF"), null, null)));
        return db;
    }
}


