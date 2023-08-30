package duke.exception;

/**
 * The duke.exception.NoEndDateException class represents a custom exception used in the duke.Duke class.
 * It extends the duke.exception.DukeException class.
 * It is thrown when the end date of the event task is empty.
 */
public class NoEndDateException extends DukeException {
    /**
     * Constructor for duke.exception.NoEndDateException class.
     *
     */
    public NoEndDateException() {
        super("☹ OOPS!!! Please provide a end date for your event.");
    }
}
