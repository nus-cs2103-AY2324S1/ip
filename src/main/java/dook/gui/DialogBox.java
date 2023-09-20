package dook.gui;

import java.io.IOException;

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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Class representing a dialog box for displaying both user and Dook input/response.
 */
public class DialogBox extends HBox {
    @FXML
    private Text dialog;
    @FXML
    private Circle displayPicture;

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
        displayPicture.setFill(new ImagePattern(img));
    }


    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Instantiates a dialogue box for the user dialogue.
     * @param l User message
     * @param iv User icon
     * @return DialogBox to display user dialogue.
     */
    public static DialogBox getUserDialog(String l, Image iv) {
        var db = new DialogBox(l, iv);
        db.dialog.setTextAlignment(TextAlignment.RIGHT);
        Insets inset = new Insets(5);
        BackgroundFill backgroundFill =
                new BackgroundFill(Color.web("#ccccd7"), null, inset);
        db.setBackground(new Background(backgroundFill));
        return db;
    }

    /**
     * Instantiates a dialogue box for Dook's responses
     * @param l Dook response
     * @param iv Dook icon
     * @return DialogBox to dislay Dook's response.
     */
    public static DialogBox getDukeDialog(String l, Image iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        Insets inset = new Insets(5);
        BackgroundFill backgroundFill =
                new BackgroundFill(Color.web("#a4a6ad"), null, inset);
        db.setBackground(new Background(backgroundFill));

        return db;
    }
}
