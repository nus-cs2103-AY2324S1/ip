package duke;

/**
 * Represents a DukeException.
 */
public class DukeException extends Exception {
    /**
     * A constructor for DukeException.
     *
     * @param exception the type of exception.
     */
    public DukeException(ExceptionTypes exception) {
        super(exception.getErrormessage());
    }
}
