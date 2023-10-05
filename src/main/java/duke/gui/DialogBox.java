package duke.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    private static final String USERCOLOR = "aqua";
    private static final String BOTCOLOR = "grey";
    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv, String color) {
        text = l;
        displayPicture = iv;
        text.setWrapText(true);
        displayPicture.setFitWidth(70.0);
        displayPicture.setFitHeight(70.0);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setStyle("-fx-background-color: " + color + ";");
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

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv, USERCOLOR);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv, BOTCOLOR);
        db.flip();
        return db;
    }
}
