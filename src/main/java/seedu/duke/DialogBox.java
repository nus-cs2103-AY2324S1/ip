package seedu.duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * To create a dialog box shown on GUI
 */
public class DialogBox extends HBox {

    private Text text;
    private ImageView displayPicture;

    /**
     * Constructor of a dialog box
     * @param l message input by user or response
     * @param iv the image of Lemon or User
     */
    public DialogBox(String l, Image iv) {
        text = new Text(l);
        ImageView imageView = new ImageView();
        Circle clip = new Circle(30, 30, 30);
        imageView.setClip(clip);
        imageView.setImage(iv);
        displayPicture = imageView;
        displayPicture.setFitWidth(60.0);
        displayPicture.setFitHeight(60.0);


        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
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

    public static DialogBox getUserDialog(String l, Image iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(String l, Image iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
