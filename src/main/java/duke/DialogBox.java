package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;


/**
 * The type Dialog box.
 */
public class DialogBox extends HBox {

    private Label text;
    private Circle displayPicture;

    /**
     * Instantiates a new Dialog box.
     *
     * @param l  the label
     * @param c the circle image
     */
    DialogBox(Label l, Circle c) {
        super(10);
        text = l;
        displayPicture = c;

        text.setWrapText(true);
        //displayPicture.setFitWidth(80.0);
        //displayPicture.setFitHeight(80.0);

        this.setAlignment(Pos.TOP_RIGHT);
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
     * @param c the circle
     * @return the user dialog
     */
    public static DialogBox getUserDialog(Label l, Circle c) {
        return new DialogBox(l, c);
    }

    /**
     * Gets duke dialog.
     *
     * @param l  the label
     * @param c the circle
     * @return the duke dialog
     */
    public static DialogBox getDukeDialog(Label l, Circle c) {
        var db = new DialogBox(l, c);
        db.flip();
        return db;
    }
}
