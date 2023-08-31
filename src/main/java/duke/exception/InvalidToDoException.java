package duke.exception;

/**
 * Represents an exception when an invalid to do format is provided to the duke.Duke application.
 */
public class InvalidToDoException extends DukeException {

    /**
     * Default constructor for InvalidToDoException.
     */
    public InvalidToDoException() {
        super();
    }

    /**
     * Returns a custom message indicating an invalid to do format.
     *
     * @return A string explaining the error.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }
}
