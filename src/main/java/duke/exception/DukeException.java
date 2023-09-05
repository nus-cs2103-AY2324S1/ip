package duke.exception;

/**
 * Represents an exception that occurs when the format for a command is incorrect.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with a specified detail message.
     *
     * @param message The specified detail message.
     */
    public DukeException(String message) {
        super(message);
    }
}