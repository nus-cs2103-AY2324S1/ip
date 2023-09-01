package  Exceptions;
/**
 * This class represents the exception which is thrown when an invalid command is given or faced.
 */
public class DukeException extends Exception {
    public DukeException (String message) {
        super(message);
    }
}
