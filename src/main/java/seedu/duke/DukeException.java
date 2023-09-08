package seedu.duke;

/**
 * Represents a unique type of Exception
 * only for the Duke chatbot.
 *
 * @author KAM JIA YUE
 * @since 2023-08-29
 */
public class DukeException extends Exception {

    /**
     * The main constructor of DukeException.
     *
     * @param message The message of this DukeException.
     */
    public DukeException(String message) {
        super(message);
    }
}
