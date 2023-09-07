package duke.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    private static final String USER_RESOURCE = "/view/UserDialogBox.fxml";
    private static final String DUKE_RESOURCE = "/view/DukeDialogBox.fxml";
    @FXML
    private Label dialog;

    private DialogBox(String text, String resource) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(resource));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
    }

    public static DialogBox getUserDialog(String text) {
        return new DialogBox(text, USER_RESOURCE);
    }

    public static DialogBox getDukeDialog(String text) {
        return new DialogBox(text, DUKE_RESOURCE);
    }
}
