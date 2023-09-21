package Duke;

/**
 * Custom exception class to handle exceptions thrown by Duke.
 */
public class DukeException extends Exception {
    /**
     * Public constructor for DukeException.
     *
     * @param msg The error message associated with the exception.
     */
    public DukeException(String msg) {
        super(msg);
    }
}