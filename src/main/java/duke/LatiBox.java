package duke;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Displays the chatbox for the Dukebot
 */
public class LatiBox extends HBox{
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Initiates a LatiBox class
     * @param text The text to be shown
     * @param img The image of the bot
     */
    private LatiBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/LatiBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setFont(new Font("Courier New", 12));
        dialog.setTextFill(Color.GREEN);
        super.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        displayPicture.setImage(img);
    }

    /**
     * Returns the LatiBox dialog
     * @param text The text to be shown
     * @param img The avatar of the bot
     * @return A new LatiBox instance
     */
    public static LatiBox getDukeDialog(String text, Image img) {
        return new LatiBox(text, img);
    }
}
