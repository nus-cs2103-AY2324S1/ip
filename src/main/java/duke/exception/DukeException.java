package duke.exception;

/**
 * the parent class for all the exception.
 */
public class DukeException extends Exception {
    /**
     * Constructor for the exception.
     * @param msg the error message
     */
    public DukeException(String msg) {
        super(msg);
    }

}
