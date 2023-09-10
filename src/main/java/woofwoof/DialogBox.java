package woofwoof;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * The `DialogBox` class represents a graphical dialog box for displaying messages in the GUI application.
 * It provides methods for creating user and application dialog boxes, flipping their alignment,
 * and setting text wrapping.
 */
public class DialogBox extends HBox {
    /**
     * Private constructor to create a new `DialogBox` with a label and an image view.
     *
     * @param text The text content to be displayed in the dialog box.
     * @param img  The image to be displayed in the dialog box.
     */
    private DialogBox(String text, Image img) {
        this.setPadding(new Insets(10.0, 0.0, 10.0, 0.0));

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setColor(new Color(0.0, 0.0, 0.0, 0.5));

        Insets bubbleMargin = new Insets(20.0); // top, right, bottom, left

        Circle displayPicture = new Circle(0, 0, 50);
        displayPicture.setFill(new ImagePattern(img));

        Text message = new Text(text);
        double messageBubbleHeight = message.getBoundsInLocal().getHeight() * 1.26 + 20;
        Rectangle messageBubble = new Rectangle(560, messageBubbleHeight);
        messageBubble.setFill(Color.WHITE);
        messageBubble.setArcWidth(30);
        messageBubble.setArcHeight(30);

        StackPane bubbleDialog = new StackPane(messageBubble, message);
        StackPane.setMargin(message, bubbleMargin);
        bubbleDialog.setAlignment(Pos.TOP_LEFT);
        bubbleDialog.setStyle(
            "-fx-padding: 0px 20px 0px 20px;"
        );

        displayPicture.setEffect(dropShadow);
        messageBubble.setEffect(dropShadow);
        this.getChildren().addAll(bubbleDialog, displayPicture);
    }


    /**
     * Flips the dialog box, changing the alignment to place the ImageView on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates a new user dialog box.
     *
     * @param text The text content to be displayed in the user dialog box.
     * @param img  The image to be displayed in the user dialog box.
     * @return A new `DialogBox` representing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a new dialog box and flips it to place the ImageView on the left and text on the right.
     *
     * @param text The text content to be displayed in the application dialog box.
     * @param img  The image to be displayed in the application dialog box.
     * @return A new `DialogBox` representing the application's dialog.
     */
    public static DialogBox getBotDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
