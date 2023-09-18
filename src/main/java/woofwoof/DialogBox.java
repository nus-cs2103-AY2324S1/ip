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
     * Private constructor to create a new `DialogBox`.
     *
     * @param message The text content to be displayed in the dialog box.
     * @param img  The image to be displayed in the dialog box.
     */
    private DialogBox(String message, Image img) {
        assert message != null : "message cannot be null";
        assert img != null : "image cannot be null";

        this.setPadding(new Insets(10.0, 0.0, 10.0, 0.0));
        DropShadow dropShadow = createDropShadow();
        Circle displayPicture = createDisplayPicture(img);
        Text text = createText(message);
        Rectangle messageBubble = createMessageBubble(text);

        displayPicture.setEffect(dropShadow);
        messageBubble.setEffect(dropShadow);
        StackPane bubbleDialog = createBubbleDialog(messageBubble, text);
        this.getChildren().addAll(bubbleDialog, displayPicture);
    }

    /**
     * Create and configure the drop shadow effect for elements.
     *
     * @return The configured DropShadow effect.
     */
    private DropShadow createDropShadow() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setColor(new Color(0.0, 0.0, 0.0, 0.5));
        return dropShadow;
    }

    /**
     * Create and configure the display picture (circle with an image fill).
     *
     * @param img The display image to be displayed.
     * @return The configured Circle element for the display picture.
     */
    private Circle createDisplayPicture(Image img) {
        assert img != null : "image cannot be null";

        Circle displayPicture = new Circle(0, 0, 50);
        displayPicture.setFill(new ImagePattern(img));
        displayPicture.setEffect(createDropShadow());
        return displayPicture;
    }

    /**
     * Create a Text element with the given message content.
     *
     * @param message The text content to be displayed.
     * @return The configured Text element.
     */
    private Text createText(String message) {
        assert message != null : "message cannot be null";

        return new Text(message);
    }

    /**
     * Create and configure the message bubble (rounded rectangle).
     *
     * @param text The Text element to calculate the height of the bubble.
     * @return The configured Rectangle element for the message bubble.
     */
    private Rectangle createMessageBubble(Text text) {
        assert text != null : "text cannot be null";

        text.setFont(WoofWoof.getFont());
        double messageBubbleHeight = text.getBoundsInLocal().getHeight() + 40;
        Rectangle messageBubble = new Rectangle(560, messageBubbleHeight);
        messageBubble.setFill(Color.WHITE);
        messageBubble.setArcWidth(40);
        messageBubble.setArcHeight(40);
        messageBubble.setEffect(createDropShadow());
        return messageBubble;
    }

    /**
     * Create a StackPane to combine the message bubble and text message.
     *
     * @param messageBubble The message bubble element.
     * @param text       The text message element.
     * @return The configured StackPane containing the message bubble and text message.
     */
    private StackPane createBubbleDialog(Rectangle messageBubble, Text text) {
        assert text != null : "message cannot be null";

        Insets bubbleMargin = new Insets(20.0); // top, right, bottom, left
        StackPane bubbleDialog = new StackPane(messageBubble, text);
        StackPane.setMargin(text, bubbleMargin);
        bubbleDialog.setAlignment(Pos.TOP_LEFT);
        bubbleDialog.setStyle(
            "-fx-padding: 0px 20px 0px 20px;"
        );
        return bubbleDialog;
    }

    /**
     * Flips the dialog box, changing the alignment to place the display picture on the left and message bubble
     * on the right.
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
     * @param message The message content to be displayed in the user dialog box.
     * @param img  The display image to be displayed in the user dialog box.
     * @return A new `DialogBox` representing the user's dialog.
     */
    public static DialogBox getUserDialog(String message, Image img) {
        assert message != null : "message cannot be null";
        assert img != null : "image cannot be null";

        return new DialogBox(message, img);
    }

    /**
     * Creates a new dialog box and flips it to place the ImageView on the left and message on the right.
     *
     * @param message The message content to be displayed in the application dialog box.
     * @param img  The display image to be displayed in the application dialog box.
     * @return A new `DialogBox` representing the application's dialog.
     */
    public static DialogBox getBotDialog(String message, Image img) {
        assert message != null : "message cannot be null";
        assert img != null : "image cannot be null";

        var db = new DialogBox(message, img);
        db.flip();
        return db;
    }
}
