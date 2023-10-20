package duke.ui;

import javafx.scene.image.Image;

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
public class UserDialogBox extends DialogBox {
    protected UserDialogBox(String text, Image img) {
        super(text,img,"user-dialog");
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */

    public static DialogBox createDialog(String text, Image img) {
        return new UserDialogBox(text, img);
    }
}

