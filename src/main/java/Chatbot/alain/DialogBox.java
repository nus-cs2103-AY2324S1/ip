package chatbot.alain;

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
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * Represents a dialog box in the UI that displays text and an associated image.
 *
 * This class is an extension of the HBox and contains a Label for text and an ImageView for the display picture.
 * The dialog box is designed to adjust the image size and position the text and image in the appropriate layout.
 *
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Creates a new DialogBox with the specified label and image view.
     *
     * The label's text is set to wrap if it exceeds the box width.
     * The image view is resized to a fixed width and height.
     * Both the label and image view are then added to the HBox with the label text displayed first.
     *
     * @param l The label containing text for the dialog box.
     * @param iv The image view displaying the associated picture.
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        Circle circleClip = new Circle(50, 50, 50);
        displayPicture.setClip(circleClip);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);

        // Padding for the HBox
        this.setPadding(new Insets(10, 10, 10, 10));

        // Spacing between elements in the HBox
        this.setSpacing(10);

        // Rounded corners for the dialog box
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(5), Insets.EMPTY)));

        // Set max width and auto resizing for the text
        text.setMaxWidth(250);
        text.setMinHeight(Region.USE_PREF_SIZE);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        var userDialog = new DialogBox(l, iv);
        userDialog.text.setFont(new Font("Georgia", 16));
        userDialog.text.setTextFill(Color.DARKGOLDENROD);
        userDialog.setBackground(new Background(
                new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(30), Insets.EMPTY)));
        return userDialog;
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var dukeDialog = new DialogBox(l, iv);
        dukeDialog.text.setFont(new Font("Times New Roman", 16));
        dukeDialog.text.setTextFill(Color.CORNSILK);
        dukeDialog.setBackground(new Background(
                new BackgroundFill(Color.LIGHTCORAL, new CornerRadii(30), Insets.EMPTY)));
        dukeDialog.flip();
        return dukeDialog;
    }
}
