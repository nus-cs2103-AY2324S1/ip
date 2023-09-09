package duke.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv, boolean isUser) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(80.0);
        displayPicture.setFitHeight(80.0);

        this.setMinHeight(100.0);

        if (isUser) {
            setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
            setAlignment(Pos.TOP_RIGHT);
        } else {
            setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
            setAlignment(Pos.TOP_LEFT);
        }

        this.setAlignment(Pos.TOP_RIGHT);

        int spacing = 10; // Adjust the spacing value as needed
        setPadding(new Insets(spacing, spacing, spacing, spacing));

        if(!isUser) {
            HBox imageTextHBox = new HBox();
            imageTextHBox.setSpacing(10); // Adjust the spacing value between image and text
            imageTextHBox.getChildren().addAll(displayPicture, text);
            this.getChildren().addAll(imageTextHBox);
        } else {
            HBox imageTextHBox = new HBox();
            imageTextHBox.setSpacing(10); // Adjust the spacing value between image and text
            imageTextHBox.getChildren().addAll(text, displayPicture);
            this.getChildren().addAll(imageTextHBox);
        }
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
        return new DialogBox(l, iv, true);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv, false);
        db.flip();
        return db;
    }

}

