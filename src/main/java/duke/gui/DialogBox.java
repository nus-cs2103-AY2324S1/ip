package duke.gui;
import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * Represents a custom JavaFX control for displaying dialog boxes.
 * Each dialog box consists of an ImageView to represent the speaker's face and a label containing text from the speaker.
 */
public class DialogBox extends HBox {

    /**
     * Label to display the text in the dialog box.
     */
    @FXML
    private Label dialog;

    /**
     * ImageView to display the speaker's image.
     */
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a new DialogBox with the specified text and image.
     *
     * @param text   The text to display in the dialog box.
     * @param img    The image to display in the dialog box.
     * @param isUser True if the dialog belongs to the user; false otherwise.
     */
    protected DialogBox(String text, Image img, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Make the image a circle
        displayPicture.setClip(new Circle(50, 50, 45));

        // Set background color
        Color bgColor = isUser ? Color.LIGHTBLUE : Color.LIGHTGRAY;
        BackgroundFill bgFill = new BackgroundFill(bgColor, CornerRadii.EMPTY, Insets.EMPTY);
        Background bg = new Background(bgFill);
        this.setBackground(bg);

        // Set height to fit text
        double labelHeight = calculateLabelHeight(text);
        setMinHeight(labelHeight);

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Calculates the height required to display the given text in the dialog box.
     *
     * @param text The text to calculate the height for.
     * @return The height needed to display the text.
     */
    private double calculateLabelHeight(String text) {
        Text tempText = new Text(text);
        tempText.setWrappingWidth(200);
        tempText.setLineSpacing(0);
        double height = tempText.getLayoutBounds().getHeight();
        assert height > 0;
        return height;
    }

    /**
     * Flips the dialog box, placing the ImageView on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a DialogBox for the user with the specified text and image.
     *
     * @param text The text to display in the user's dialog box.
     * @param img  The image to display in the user's dialog box.
     * @return A DialogBox for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true);
    }

    /**
     * Creates a DialogBox for Duke with the specified text and image and flips it.
     *
     * @param text The text to display in Duke's dialog box.
     * @param img  The image to display in Duke's dialog box.
     * @return A flipped DialogBox for Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, false);
        db.flip();
        return db;
    }
}

