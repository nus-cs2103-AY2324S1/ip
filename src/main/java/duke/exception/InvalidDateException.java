package duke.exception;

/**
 * Exception thrown when a date is input in the wrong format.
 */
public class InvalidDateException extends DukeException {

    /**
     * Constructs InvalidDateException.
     */
    public InvalidDateException() {
        super("☹ OOPS!!! Please enter a valid date in yyyy-mm-dd format.");
    }

}
