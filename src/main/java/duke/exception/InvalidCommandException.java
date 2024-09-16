package duke.exception;

/** Exception for invalid command */
public class InvalidCommandException extends DukeException {
    /**
     * Initializes Invalid Command Exception.
     *
     * @param message Error message to print out.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
