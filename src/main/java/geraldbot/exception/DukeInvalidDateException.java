package geraldbot.exception;

/**
 * Represents an exception thrown when an invalid date or time is provided by the user.
 */
public class DukeInvalidDateException extends DukeException {

    /**
     * Constructs a DukeInvalidDateException with a default error message for an invalid date/time.
     */
    public DukeInvalidDateException() {
        super("☹ OOPS!!! The selected date/time is invalid.");
    }
}
