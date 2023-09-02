package juke.ui.components;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Represents a dialog box containing Juke's response.
 */
public class JukeDialog extends DialogBox {
    /** The background color of the dialog box. */
    private static final Color JUKE_COLOR = Color.LIGHTBLUE;

    /** The profile image for Juke. */
    @FXML
    private static final Image JUKE_IMAGE = new Image(Objects.requireNonNull(
            JukeDialog.class.getResourceAsStream("/images/DaDuke.png")));

    /**
     * Constructs an instance of {@code JukeDialog}.
     *
     * @param text Text to display in the dialog box
     */
    public JukeDialog(String text) {
        super(text, JukeDialog.JUKE_IMAGE);
        this.setDialogBubbleColor(JukeDialog.JUKE_COLOR);
        this.flip();
    }
}
