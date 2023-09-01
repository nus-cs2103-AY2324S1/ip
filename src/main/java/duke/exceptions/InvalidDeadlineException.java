package duke.exceptions;

/**
 * Signals an invalid deadline due to invalid format given by the user.
 */
public class InvalidDeadlineException extends DukeException {
    /**
     * Public constructor for InvalidDeadlineException
     */
    public InvalidDeadlineException() {
        super("Please enter a deadline with the format '<<message>> /by dd/mm/yyyy HH:mm' format");
    }
}
