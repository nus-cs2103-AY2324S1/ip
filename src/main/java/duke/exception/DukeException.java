package duke.exception;

/**
 * Represents an exception that occurs when the format for a command is incorrect.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}