package duke;

/**
 * Exception class for handling invalid commands
 */
public class DukeException extends Exception {

    /**
     * Constructs a new DukeException
     * @param message Error message to be printed to the user
     */
    public DukeException(String message) {
        super(message);
    }
}

