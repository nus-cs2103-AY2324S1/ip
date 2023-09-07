package aj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

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
     * Factory method that takes in a Label and Imageview and return a DialogBox control for Aj-bot.
     *
     * @param l  Label control with text
     * @param iv Imageview control with the image
     * @return DialogBox control to be displayed in scene
     */
    public static DialogBox getAjDialog(Label l, ImageView iv) {
        return new DialogBox(l,
                iv);
    }

    /**
     * Factory method that takes in a Label and Imageview and return a DialogBox control for user.
     *
     * @param l  Label control with text
     * @param iv Imageview control with the image
     * @return DialogBox control to be displayed in scene
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        var db = new DialogBox(l,
                iv);
        db.flip();
        return db;
    }

    public DialogBox(Label l, ImageView iv) {
        this.text = l;
        this.displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text,
                displayPicture);
    }
}
