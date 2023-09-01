package blip.exceptions;

/**
 * The InvalidCommandException class is an exception class
 * that is thrown when the command are not valid.
 */
public class InvalidCommandException extends Exception {
    /**
     * Constructor of InvalidCommandException.
     * @param message The error message
     */
    public InvalidCommandException(String message) { super(message); }
}
