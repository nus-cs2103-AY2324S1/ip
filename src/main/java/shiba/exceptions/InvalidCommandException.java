package shiba.exceptions;

/**
 * Represents an exception that occurs when an invalid command is given.
 */
public class InvalidCommandException extends ShibaException {
    public InvalidCommandException(String msg) {
        super(msg);
    }
}
