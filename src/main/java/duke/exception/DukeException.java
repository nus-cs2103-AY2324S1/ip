package duke.exception;

/**
 * duke.exception.DukeException class is a custom duke.exception class that extends Exception.
 */
public class DukeException extends Exception {

    /**
     * Constructor for duke.exception.DukeException.
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
