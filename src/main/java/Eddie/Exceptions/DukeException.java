package Eddie.Exceptions;

/**
 * An exception which can be thrown by duke processes.
 */
public class DukeException extends Exception {

    public DukeException() {}
    public DukeException(String message) {
        super(message);
    }
}
