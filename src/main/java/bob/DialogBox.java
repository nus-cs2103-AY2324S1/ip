package bob;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        text.setPadding(new Insets(5, 10, 5, 10));

        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);

        Rectangle clip = new Rectangle(
                displayPicture.getFitWidth(), displayPicture.getFitHeight()
        );
        clip.setArcWidth(100);
        clip.setArcHeight(100);
        displayPicture.setClip(clip);

        this.setPadding(new Insets(12, 12, 22, 12));
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
        var db = new DialogBox(l, iv);
        db.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,
                new CornerRadii(20),
                new Insets(5, 5, 15, 5))));
        return db;
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.setBackground(new Background(new BackgroundFill(Color.WHITE,
                new CornerRadii(20),
                new Insets(5, 5, 15, 5))));
        db.flip();
        return db;
    }
}

