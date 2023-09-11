package duke.exception;

/**
 * Represents an exception that occurs during the execution of Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException with the specified detail message.
     *
     * @param message The detail message.
     */
    public DukeException(String message) {
        super("OOPS!!! :(( " + message);
    }
}
