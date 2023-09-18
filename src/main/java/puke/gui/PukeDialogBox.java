package puke.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class PukeDialogBox extends HBox {
    private static final String STARTUP_MESSAGE = "Salutations! I hereby would like to inform you that my identity is "
            + "that of Puke, an exceedingly verbose conversation simulation program.";
    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;

    private PukeDialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/PukeDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    public static PukeDialogBox getPukeDialog(String text, Image img) {
        return new PukeDialogBox(text, img);
    }

    /**
     * Returns the startup dialog
     *
     * @param img puke image
     * @return startup dialog
     */
    public static PukeDialogBox startup(Image img) {
        return new PukeDialogBox(STARTUP_MESSAGE, img);
    }
}
