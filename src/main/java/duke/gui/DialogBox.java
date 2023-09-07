package duke.gui;

import java.io.IOException;
import java.util.Collections;

import duke.Main;
import javafx.application.Application;
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


/**
 * The `DialogBox` class represents a graphical dialog box used in the Duke application's user interface.
 * It extends the JavaFX `HBox` class and is used to display a conversation between the user and Duke,
 * the chatbot.
 *
 * This class includes methods to create user and Duke dialog boxes, flip the order of items in the dialog
 * for Duke's responses, and handle the text and image to be displayed in the dialog box.
 *
 * @author Your Name
 * @version 1.0
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
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

        dialog.setText(text);
        displayPicture.setImage(img);
    }


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

    /**
     * A launcher class to workaround classpath issues.
     */
    public static class Launcher {
        public static void main(String[] args) {
            Application.launch(Main.class, args);
        }
    }
}
