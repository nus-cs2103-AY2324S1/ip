package duke.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * Controller for a Message Bubble in the GUI.
 */
public class GobbleMessage extends HBox {
    @FXML
    private Label message;
    @FXML
    private Label from;

    private GobbleMessage(String text, String from) {
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
        this.from.setText(from);
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
     * @param text text to be displayed.
     * @return a user styled dialog.
     */
    public static GobbleMessage getUserDialog(String text) {
        GobbleMessage message = new GobbleMessage(text, "User");
        message.setUserStyles();
        message.flip();
        return message;
    }

    /**
     * Returns a duke styled dialog.
     *
     * @param text    text to be displayed.
     * @param command command to be displayed.
     * @return a duke styled dialog.
     */
    public static GobbleMessage getDukeDialog(String text, String command) {
        GobbleMessage db = new GobbleMessage(text, command);
        return db;
    }
}