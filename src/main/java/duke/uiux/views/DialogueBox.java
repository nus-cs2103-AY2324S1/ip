package duke.uiux.views;

import java.io.IOException;
import java.util.Collections;

import duke.uiux.MainWindow;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogueBox extends HBox {
    @FXML
    private Label dialogue;
    @FXML
    private Circle displayPicture;
    private Color paint;

    private DialogueBox(String text, Image img, Color paint) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("./views/DialogueBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialogue.setText(text);
        displayPicture.setFill(new ImagePattern(img));
        BackgroundFill bf = new BackgroundFill(paint, new CornerRadii(0), null);
        this.setBackground(new Background(bf));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
    }

    public static DialogueBox getUserDialogue(String text, Image img) {
        return new DialogueBox(text, img, Color.GREY);
    }

    public static DialogueBox getDukeDialogue(String text, Image img) {
        var db = new DialogueBox(text, img, Color.LIGHTGREY);
        db.flip();
        return db;
    }
}