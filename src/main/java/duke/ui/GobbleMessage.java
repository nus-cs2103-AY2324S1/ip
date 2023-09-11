package duke.ui;

import java.io.IOException;
import java.util.Collections;

import duke.enums.UserType;
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
import javafx.scene.layout.Region;

/**
 * Controller for a Message Bubble in the GUI.
 */
public class GobbleMessage extends HBox {
    @FXML
    private Label message;
    @FXML
    private ImageView profileImage;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private final Image gobbleImage = new Image(this.getClass().getResourceAsStream("/images/system.jpg"));

    private GobbleMessage(String text, UserType from) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.message.setText(text);
        this.message.setMinHeight(Region.USE_PREF_SIZE);
        this.profileImage.setImage(from == UserType.USER ? userImage : gobbleImage);
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

    private void setUserStyles() {
        this.setStyle("-fx-background-color: #A9A9A9; -fx-background-radius: 10; -fx-padding: 10");
        this.message.setStyle("-fx-text-fill: #ffffff ;-fx-font-weight: bold");
    }

    /**
     * Returns a user styled dialog.
     *
     * @param text Text to be displayed.
     * @return GobbleMessage user styled dialog.
     */
    public static GobbleMessage getUserDialog(String text) {
        assert !text.isEmpty() : "text should not be empty";
        GobbleMessage message = new GobbleMessage(text, UserType.USER);
        message.setUserStyles();
        message.flip();
        return message;
    }

    /**
     * Returns a duke styled dialog.
     *
     * @param text text to be displayed.
     * @return GobbleMessage system styled dialog.
     */
    public static GobbleMessage getGobbleDialog(String text) {
        assert text != null : "text should not be null";
        GobbleMessage db = new GobbleMessage(text, UserType.SYSTEM);
        return db;
    }
}