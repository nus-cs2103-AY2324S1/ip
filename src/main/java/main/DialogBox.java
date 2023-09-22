package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face and a label containing text
 * from the speaker.
 */
public class DialogBox extends HBox {

    /**
     * The Label containing text from the speaker.
     */
    private Label text;
    private ImageView displayPicture;
    private StackPane stackPane;

    /**
     * Constructs a dialog box.
     *
     * @param l The Label containing text from the speaker.
     * @param iv The ImageView to represent the speaker's face.
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        // Create a StackPane to hold the Label and give it a background
        stackPane = new StackPane();
        stackPane.getChildren().add(text);
        stackPane.setBackground(null);
        stackPane.setPadding(new Insets(10));

        // Make the StackPane grow with the content
        stackPane.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(stackPane, Priority.ALWAYS);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(stackPane, displayPicture);
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
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
