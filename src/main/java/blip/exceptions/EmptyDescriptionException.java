package blip.exceptions;

/**
 * The EmptyDescriptionException class is an exception class
 * that is thrown when any component of the commands are missing.
 */
public class EmptyDescriptionException extends Exception {
    /**
     * Constructor of EmptyDescriptionException.
     * @param message The error message
     */
    public EmptyDescriptionException(String message) { super(message); }
}
