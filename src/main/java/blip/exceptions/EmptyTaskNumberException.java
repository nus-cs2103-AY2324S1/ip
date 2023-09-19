package blip.exceptions;

/**
 * The EmptyTaskNumberException class is an exception class
 * that is thrown when the index of delete/mark/unmark commands are missing.
 */
public class EmptyTaskNumberException extends Exception {
    /**
     * Creates an instance of EmptyTaskNumberException.
     *
     * @param message The error message
     */
    public EmptyTaskNumberException(String message) {
        super(message);
    }

}
