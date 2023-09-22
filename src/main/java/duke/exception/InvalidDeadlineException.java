package duke.exception;

/**
 * Represents an exception when an invalid deadline format is provided to the duke.Duke application.
 */
public class InvalidDeadlineException extends DukeException {

    /**
     * Default constructor for InvalidDeadlineException.
     */
    public InvalidDeadlineException() {
        super();
    }

    /**
     * Returns a custom message indicating an invalid deadline format.
     *
     * @return A string explaining the error.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a deadline cannot be empty.";
    }
}

