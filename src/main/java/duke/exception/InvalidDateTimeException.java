package duke.exception;

/**
 * Represents an exception when an invalid datetime format is provided to the duke.Duke application.
 */
public class InvalidDateTimeException extends DukeException {

    /**
     * Default constructor for InvalidDateTimeException.
     */
    public InvalidDateTimeException() {
        super();
    }

    /**
     * Returns a custom message indicating an invalid datetime format.
     *
     * @return A string explaining the error.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! The datetime format is invalid.";
    }
}
