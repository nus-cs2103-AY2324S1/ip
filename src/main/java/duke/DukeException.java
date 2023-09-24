package duke;

/**
 * Custom exception class for Duke application.
 * DukeException is thrown when there are errors or exceptions specific to the Duke application.
 */
public class DukeException extends Exception {

    /**
     * Constructs a new DukeException with the specified error message.
     *
     * @param message The error message associated with this exception.
     */
    public DukeException(String message) {
        super(message);
    }

}
