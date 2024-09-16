package chatbot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Dialog box create a dialog box of the conversation between user and chatbot.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor of Dialog Box.
     *
     * @param l takes in Label
     * @param iv takes in ImageView
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        assert text != null : "text cannot be empty";
        assert displayPicture != null : "image cannot be empty";

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Set up the dialog box for user message.
     *
     * @param l takes in Label
     * @param iv takes in Image view
     * @return return a Dialog Box
     */
    public static DialogBox setUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Set up dialog box for chatbot reply which is flipped direction with the user side.
     *
     * @param l takes in Label
     * @param iv takes in Image view
     * @return return a DialogBox
     */
    public static DialogBox setDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
