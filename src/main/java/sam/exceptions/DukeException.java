package sam.exceptions;

/**
 * Signals an error caused by command.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
