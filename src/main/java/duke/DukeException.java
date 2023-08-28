package duke;

/**
 * Represents an exception that will be thrown by Duke operations.
 */
public class DukeException extends Exception {
    /**
     * Constructs the Duke Exception.
     *
     * @param errMsg The error message that is supposed to be printed into the command line.
     */
    public DukeException(String errMsg) {
        super(errMsg);
    }
}
