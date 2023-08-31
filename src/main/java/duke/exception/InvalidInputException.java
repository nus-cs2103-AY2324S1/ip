package duke.exception;

/**
 * Represents an exception when an invalid input is provided to the duke.Duke application.
 */
public class InvalidInputException extends DukeException {

    /**
     * Default constructor for InvalidInputException.
     */
    public InvalidInputException() {
        super();
    }

    /**
     * Returns a custom message indicating an invalid input.
     *
     * @return A string explaining the error.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
