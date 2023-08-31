package duke.exception;

/**
 * Represents an exception when an invalid find format is provided to the duke.Duke application.
 */
public class InvalidFindException extends DukeException {

    /**
     * Default constructor for InvalidFindException.
     */
    public InvalidFindException() {
        super();
    }

    /**
     * Returns a custom message indicating an invalid find format.
     *
     * @return A string explaining the error.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a find cannot be empty.";
    }
}
