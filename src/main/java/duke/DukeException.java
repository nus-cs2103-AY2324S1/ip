package duke;

/**
 * Represents a custom exception specific to the Duke application.
 * Inherits from the Exception class.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException object with the provided error message.
     *
     * @param message The error message associated with the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
