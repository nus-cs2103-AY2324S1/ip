package seedu.duke;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    private final Font TEXT_FONT = Font.font("Andale Mono", 15.0);
    private final Color TEXT_BUBBLE_COlOR = Color.rgb(255, 255, 255);
    private final int TEXT_BUBBLE_PADDING_X = 30;
    private final int TEXT_BUBBLE_PADDING_Y = 20;
    private final int TEXT_BUBBLE_ARC_WIDTH = 30;
    private final int TEXT_BUBBLE_ARC_HEIGHT = 30;
    private final int SCROLL_PANE_MAX_WIDTH = 600;
    private final int IMAGE_POSITION_X = 50;
    private final int IMAGE_POSITION_Y = 55;
    private final int IMAGE_CIRCLE_RADIUS = 40;

    @FXML
    private StackPane dialog;
//    private Text dialog;
//    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setDialog(text);
        setImage(img);
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

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    public void setDialog(String text) {
        Text message = new Text(text);
        message.setFont(TEXT_FONT);

        double messageWidth = message.getLayoutBounds().getWidth();
        double bubbleWidth = messageWidth + TEXT_BUBBLE_PADDING_X > SCROLL_PANE_MAX_WIDTH
                ? SCROLL_PANE_MAX_WIDTH
                : messageWidth + TEXT_BUBBLE_PADDING_X;
        message.setWrappingWidth(bubbleWidth - TEXT_BUBBLE_PADDING_X);

        double messageHeight = message.getLayoutBounds().getHeight();
        double bubbleHeight = messageHeight + TEXT_BUBBLE_PADDING_Y;

        TextBubble textBubble = new TextBubble();
        textBubble.setWidth(bubbleWidth);
        textBubble.setHeight(bubbleHeight);
        textBubble.setArcWidth(TEXT_BUBBLE_ARC_WIDTH);
        textBubble.setArcHeight(TEXT_BUBBLE_ARC_HEIGHT);
        textBubble.setFill(TEXT_BUBBLE_COlOR);

        dialog.getChildren().addAll(textBubble, message);
    }

    public void setImage(Image img) {
        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(
                IMAGE_POSITION_X,
                IMAGE_POSITION_Y,
                IMAGE_CIRCLE_RADIUS));
    }
}
