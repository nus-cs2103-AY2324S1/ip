package juke.ui.components;

import java.util.Objects;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Represents a dialog box containing the user's message.
 */
public class UserDialog extends DialogBox {
    /** The background color of the dialog box. */
    private static final Color USER_COLOR = Color.LIGHTGREEN;

    /** The profile image of the user. */
    private static final Image USER_IMAGE = new Image(Objects.requireNonNull(
            UserDialog.class.getResourceAsStream("/images/DaUser.png")));

    /**
     * Constructs an instance of {@code UserDialog}.
     *
     * @param text Text to display in the dialog box
     */
    public UserDialog(String text) {
        super(text, UserDialog.USER_IMAGE);
        this.setDialogBubbleColor(UserDialog.USER_COLOR);
    }
}
