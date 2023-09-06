package ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

/**
 * A class that represents the dialog box of the user and Mr Red
 * when a command has entered.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private Label name;

    /**
     * DialogBox constructor that takes in String, Image, int.
     * @param l The string to display on the dialog box.
     * @param iv The image to display on the dialog box.
     * @param u Checks if it is user or Mr Red.
     */
    public DialogBox(String l, Image iv, int u) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        name.setText(user(u));
        dialog.setText(l);
        if (u == 0) {
            dialog.setStyle("-fx-background-color: #c2dadf;"
                    + "-fx-padding: 10px;" + "-fx-background-radius: 20px;");
        } else {
            dialog.setStyle("-fx-background-color: #f9c9c4;"
                    + "-fx-padding: 10px;" + "-fx-background-radius: 20px;");
        }
        Rectangle clip = new Rectangle(
                displayPicture.getFitWidth(), displayPicture.getFitWidth()
        );
        clip.setArcHeight(200);
        clip.setArcWidth(200);
        displayPicture.setClip(clip);
        displayPicture.setImage(iv);
    }

    /**
     * Returns a string indicating the user or Mr Red.
     * @param i int representing user or Mr Red.
     * @return The type.
     */
    public String user(int i) {
        if (i == 0) {
            return "You";
        } else {
            return "Mr Red";
        }
    }
}
