import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private TextArea dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Instantiates a DialogBox object that will be displayed either when the user prompts or when Duke replies
     *
     * @param text String Object. Either user's prompt or DUKE's response.
     * @param img Image Object that is generated from providing a reference link to the /resources/Images folder
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
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        TextArea dialogTextArea = (TextArea) tmp.get(1);
        dialogTextArea.setPadding(new Insets(0, 0, 0, 10));
        dialogTextArea.setStyle("-fx-control-inner-background: lightblue;");
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Generates a default DialogBox GUI whenever user's prompts DUKE
     *
     * @param text String Object. User's prompt to the DUKE chatbot.
     * @param img Image Object. User's default picture.
     * @return DialogBox Object.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Generates a default DialogBox GUI whenever DUKE replies the user's prompt.
     * Compared to the user's DialogBox, DUKE's DialogBox is flipped to showcase contrast in response.
     *
     * @param text String Object. DUKE's response.
     * @param img Image Object. Duke's default picture.
     * @return ialogBox Object.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
