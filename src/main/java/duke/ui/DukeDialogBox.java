package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;

import java.util.Collections;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DukeDialogBox extends DialogBox {
    protected DukeDialogBox(String text, Image img) {
        super(text,img,"duke-dialog");

        super.flip();
    }
    public static DialogBox createDialog(String text, Image img) {
        return new DukeDialogBox(text, img);
    }
}

