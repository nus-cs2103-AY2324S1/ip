package brotherman;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * Represents a dialog box
 */
public class DialogBox extends HBox {

    private StackPane content;
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox
     * @param content content for the dialog box
     * @param iv Image view for the dialog box
     */
    public DialogBox(StackPane content, ImageView iv) {
        this.content = content;
        displayPicture = iv;

        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(content, displayPicture);
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

    public static DialogBox getUserDialog(StackPane content, ImageView iv) {
        return new DialogBox(content, iv);
    }

    public static DialogBox getDukeDialog(StackPane content, ImageView iv) {
        var db = new DialogBox(content, iv);
        db.flip();
        return db;
    }
}

