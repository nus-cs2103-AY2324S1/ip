package duke.exception;

/**
 * Exception thrown when an end date precedes a start date.
 */
public class DateRangeException extends DukeException {

    /**
     * Constructs DateRangeException.
     */
    public DateRangeException() {
        super("OOPS!!! End date cannot be before start date.");
    }

}
