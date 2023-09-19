package hachi;

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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * A custom control for use by JavaFX. Appears as a dialogue box in the graphical interface.
 */
public class DialogBox extends HBox {

    private Label text;
    private Image displayPicture;

    /**
     * Constructor for the DialogBox class.
     * @param l The text of the dialog box.
     * @param image The picture to display.
     */
    public DialogBox(Label l, Image image) {
        text = l;
        text.setFont(Font.font("Comic Sans MS", 12));
        text.setMaxWidth(200.0);
        text.setWrapText(true);
        text.setStyle("-fx-background-color: lavenderblush; -fx-border-color: black; "
                + "-fx-border-radius: 10.0; -fx-border-width: 1.0; -fx-padding: 5.0");

        StackPane textBubble = new StackPane(text);
        textBubble.setAlignment(Pos.TOP_LEFT);

        Circle c = new Circle();
        c.setRadius(50.0);
        c.setFill(new ImagePattern(image));

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(textBubble, c);
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

    public static DialogBox getUserDialog(Label l, Image image) {
        return new DialogBox(l, image);
    }

    public static DialogBox getHachiDialog(Label l, Image image) {
        var db = new DialogBox(l, image);
        db.flip();
        return db;
    }


}
