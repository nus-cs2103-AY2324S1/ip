package duke;

/**
 * The `DukeException` class represents an exception specific to the Duke program.
 * It is a subclass of the standard Java `Exception` class and is used
 * to handle custom error messages.
 */
public class DukeException extends Exception {

    /**
     * Initializes a new instance of `DukeException` with the specified error message.
     *
     * @param message The error message associated with this exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
