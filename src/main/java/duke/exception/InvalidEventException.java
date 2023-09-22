package duke.exception;

/**
 * Represents an exception when an invalid event format is provided to the duke.Duke application.
 */
public class InvalidEventException extends DukeException {

    /**
     * Default constructor for InvalidEventException.
     */
    public InvalidEventException() {
        super();
    }

    /**
     * Returns a custom message indicating an invalid event format.
     *
     * @return A string explaining the error.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! The description of an event cannot be empty.";
    }
}

