package duke.exception;

/**
 * Represents a duke.exception.DukeException.
 *
 * @author Pearlynn
 */

public class DukeException extends Exception {

    /**
     * Constructor for duke.exception.DukeException class.
     *
     * @param errorMessage The error message of the exception.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
