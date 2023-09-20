package jarvis.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * The Dialog class control represents a dialog box consisting of an ImageView
 * to represent the speaker's face and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    public static final int MESSAGE_BOX_WIDTH = 400;
    public static final int ARC_VALUE = 40;
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a new instance of DialogBox with the specified text and image.
     *
     * @param message The text content of the dialog box.
     * @param img     The image to be displayed in the dialog box.
     */
    private DialogBox(String message, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/views/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        buildDialogBox(message, img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a user dialog box.
     *
     * @param text The text content of the user dialog box.
     * @param img  The image to be displayed for the user dialog box.
     * @return A DialogBox representing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a Jarvis dialog box.
     *
     * @param text The text content of the Jarvis dialog box.
     * @param img  The image to be displayed for the Jarvis dialog box.
     * @return A DialogBox representing Jarvis's dialog.
     */
    public static DialogBox getJarvisDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    @FXML
    private void buildDialogBox(String message, Image img) {
        Text text = createText(message);
        Rectangle messageBox = createMessageBox(text);
        Circle circleImage = new Circle(0, 0, 30);
        circleImage.setFill(new ImagePattern(img));
        StackPane dialogBox = new StackPane(messageBox, text);
        this.getChildren().addAll(dialogBox, circleImage);
    }

    private Text createText(String string) {
        assert string != null : "String to be converted cannot be null";
        return wrapText(new Text(string));
    }

    private Rectangle createMessageBox(Text text) {
        assert text != null : "Text cannot be null.";

        Text wrappedText = wrapText(text);
        double messageBubbleHeight = wrappedText.getBoundsInLocal().getHeight() + 40;
        Rectangle messageBox = new Rectangle(MESSAGE_BOX_WIDTH, messageBubbleHeight);
        messageBox.setFill(Color.WHITE);
        messageBox.setArcWidth(ARC_VALUE);
        messageBox.setArcHeight(ARC_VALUE);
        return messageBox;
    }

    private Text wrapText(Text text) {
        TextFlow textFlow = new TextFlow();
        textFlow.getChildren().add(text);
        textFlow.setPrefWidth(MESSAGE_BOX_WIDTH - 100); // Adjust the width as needed
        text.setTextOrigin(VPos.TOP);
        text.setWrappingWidth(MESSAGE_BOX_WIDTH - 100); // Adjust the width as needed
        return text;
    }
}

