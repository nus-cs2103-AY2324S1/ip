package geraldbot.exception;

/**
 * Represents an exception thrown when a user doesn't provide the date/time for a Deadline/Event.
 */
public class DukeEmptyParametersException extends DukeException {

    /**
     * Constructs a DukeEmptyParametersException with a default error message when user does not input a date/time.
     */
    public DukeEmptyParametersException() {
        super("☹ OOPS!!! I'm sorry, but you did not input a specific date/time for the Deadline/Event task! :-(");
    }
}
