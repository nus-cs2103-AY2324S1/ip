package duke;

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

/**
 * The DialogBox class allows generation of dialog boxes for the user and Duke.
 */
public class DialogBox extends HBox {
    private Label label;
    @FXML
    private ImageView displayPicture;

    /**
     * Generates a dialog box.
     *
     * @param text Message from either user or Duke.
     * @param img Image of either user or Duke.
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
        label = new Label(text);
        displayPicture = new ImageView(img);

        label.setWrapText(true);
        displayPicture.setFitWidth(75.0);
        displayPicture.setFitHeight(75.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(label, displayPicture);
    }

    /**
     * Flips orientation of the dialog box.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates the user's dialog box.
     *
     * @param text Input from the user.
     * @param img Image of user.
     * @return The user's dialog box.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates Duke's dialog box.
     *
     * @param text Input from Duke.
     * @param img Image of Duke.
     * @return Duke's dialog box.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
