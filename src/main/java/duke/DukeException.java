package duke;

/**
 * The DukeException class is a custom exception class used to represent exceptions specific to the Duke application.
 * It extends the standard Java Exception class and provides a constructor to set the exception message.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
