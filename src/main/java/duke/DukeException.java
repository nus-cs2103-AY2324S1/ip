package duke;

/**
 * Represents exceptions specific to the Duke application.
 * This class extends the base Exception class to provide custom error handling for Duke.
 */
public class DukeException extends Exception{
    /**
     * Constructs a DukeException object.
     *
     * @param message The error message to be displayed.
     */
    DukeException(String message) {
        super(message);
    }
}
