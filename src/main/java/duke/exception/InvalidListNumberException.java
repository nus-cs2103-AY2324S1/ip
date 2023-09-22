package duke.exception;

/**
 * Represents an exception when an invalid numbering for list of tasks is provided to the duke.Duke application.
 */
public class InvalidListNumberException extends DukeException {

    /**
     * Default constructor for InvalidListNumberException.
     */
    public InvalidListNumberException() {
        super();
    }

    /**
     * Returns a custom message indicating an invalid numbering provided for list of tasks.
     *
     * @return A string explaining the error.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! The task number entered is invalid.";
    }
}

