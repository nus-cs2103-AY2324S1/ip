/**
 * The NoEndDateException class represents a custom exception used in the Duke class.
 * It extends the DukeException class.
 * It is thrown when the end date of the event task is empty.
 */
public class NoEndDateException extends DukeException {
    /**
     * Constructor for NoEndDateException class.
     *
     */
    public NoEndDateException() {
        super("☹ OOPS!!! Please provide a end date for your event.");
    }
}
