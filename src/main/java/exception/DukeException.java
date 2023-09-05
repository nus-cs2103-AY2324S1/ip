package exception;

/**
 * DukeException class is subclass of the Exception class
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException
     * @param message message to be output when exception is thrown
     */
    public DukeException(String message) {
        super(message);
    }
}
