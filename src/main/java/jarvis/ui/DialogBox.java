package jarvis.ui;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * The DialogBox Class.
 * Responsible for formatting the messages.
 *
 * @author Shishir
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Pane messageOutline;
    @FXML
    private Circle circle;

    /**
     * Constructs the DialogBox.
     * @param text User's message.
     * @param img User's profile picture.
     * @param isUser User's type.
     */
    private DialogBox(String text, Image img, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        circle.setFill(new ImagePattern(img));
        messageOutline.prefHeightProperty().bind(dialog.heightProperty());

        int messageWidth = text.length() * 10;
        if (messageWidth < 260) {
            dialog.setPrefWidth(messageWidth);
            messageOutline.setPrefWidth(messageWidth + 20);
        } else {
            dialog.setPrefWidth(260);
            messageOutline.setPrefWidth(280);
        }

        if (isUser) {
            messageOutline.getStyleClass().add("blue-background");
        } else {
            messageOutline.getStyleClass().add("gray-background");
        }
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
        return new DialogBox(text, img, true);
    }

    public static DialogBox getJarvisDialog(String text, Image img) {
        var db = new DialogBox(text, img, false);
        db.flip();
        return db;
    }

}



