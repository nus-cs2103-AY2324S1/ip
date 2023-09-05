package seedu.duke.exceptions;

/**
 * The InvalidDeadlineException is thrown when a command that creates a deadline
 * task is not in the correct format.
 */
public class InvalidDeadlineException extends LemonException {
    public InvalidDeadlineException(String message) {
        super(":( OPPS!!! Please add a deadline with /by & date in format yyyy-mm-dd!");
    }
}
