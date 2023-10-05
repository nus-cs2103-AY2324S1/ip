package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;


/**
 * The type Dialog box.
 */
public class DialogBox extends HBox {

    private Label text;

    private Circle c;

    /**
     * Instantiates a new Dialog box.
     *
     * @param l  the label
     * @param i the  image
     */
    DialogBox(Label l, Image i) {
        super(10);
        this.c = new Circle(40, new ImagePattern(i));
        text = l;
        text.setWrapText(true);

        this.setAlignment(Pos.BOTTOM_RIGHT);
        this.setPadding(new Insets(10, 5, 10, 5));
        this.getChildren().addAll(text, c);
    }

    /**
     * Flip.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Gets user dialog.
     *
     * @param l  the label
     * @param i the image
     * @return the user dialog
     */
    public static DialogBox getUserDialog(Label l, Image i) {
        return new DialogBox(l, i);
    }

    /**
     * Gets duke dialog.
     *
     * @param l  the label
     * @param i the image
     * @return the duke dialog
     */
    public static DialogBox getDukeDialog(Label l, Image i) {

        var db = new DialogBox(l, i);
        db.flip();
        return db;
    }
}
