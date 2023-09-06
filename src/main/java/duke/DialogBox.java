package duke;

import java.io.IOException;
import java.util.Collections;

import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;



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

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv, true);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv, false);
        db.flip();
        return db;
    }

    public DialogBox(Label l, ImageView iv, boolean isUserDialog) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
            text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(80);
        displayPicture.setFitHeight(80);

        // Add padding between ImageView and Label
        HBox.setMargin(displayPicture, new Insets(0, 10, 0, 10)); // Adjust the insets as needed
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);

        Color backgroundColor = isUserDialog ? Color.LIGHTBLUE : Color.LIGHTGREEN;
        BackgroundFill backgroundFill = new BackgroundFill(backgroundColor, null, null);
        Background background = new Background(backgroundFill);
        this.setBackground(background);
    }
}
