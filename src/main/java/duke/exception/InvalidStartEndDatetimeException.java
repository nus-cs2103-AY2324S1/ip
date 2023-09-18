package duke.exception;

/**
 * Represents a duke.exception.InvalidStartEndDatetimeException.
 *
 * @author Pearlynn
 */

public class InvalidStartEndDatetimeException extends Exception {

    /**
     * Constructor for duke.exception.InvalidStartEndDatetimeException class.
     */
    public InvalidStartEndDatetimeException() {
        super("☹ OOPS!!! Start datetime cannot be >= end datetime.");
    }
}
