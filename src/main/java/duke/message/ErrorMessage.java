package duke.message;

import javafx.scene.paint.Color;

/**
 * Represents an error message that can be displayed to the user.
 * Extends the {@link Message} class.
 */
public class ErrorMessage extends Message {

    private static final Color ERROR_COLOR = Color.RED;
    public ErrorMessage(String content) {
        super(content, BOT_IMAGE, ERROR_COLOR);
    }
}
