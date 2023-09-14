package duke.exception;

/**
 * Represents an exception specific to the Duke chatbot.
 * Inherits from the Exception class.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException object with the given error message.
     *
     * @param message The error message associated with the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
