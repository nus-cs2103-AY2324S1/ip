package dude;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class DialogBox extends HBox {

    private final Label text;
    private final ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        //@@author xenosf-reused
        //Reused from https://stackoverflow.com/a/42601328
        // auto resize text if overflow
        text.setMinHeight(Region.USE_PREF_SIZE);
        //@@author

        displayPicture.setFitWidth(75.0);
        displayPicture.setFitHeight(75.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setSpacing(15);
        this.setPadding(new Insets(15));
    }

    public static DialogBox getUserDialog(String text, Image image) {
        DialogBox db = new DialogBox(new Label(text), new ImageView(image));
        return db;
    }

    public static DialogBox getDudeDialog(String text, Image image) {
        var db = new DialogBox(new Label(text), new ImageView(image));
        db.flip();
        db.setStyle("-fx-background-color: #d2c5ef;"); // set color for dude dialog
        return db;
    }

    //@@author xenosf-reused
    // Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
    // With minor alterations

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }
}
//@@author
