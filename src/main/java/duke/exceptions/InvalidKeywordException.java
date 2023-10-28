package duke.exceptions;

/**
 * An exception class that represents an invalid keyword exception when finding relevant tasks.
 * This exception is thrown when an operation expects a valid keyword but none is provided, or if there
 * is more than 1 keyword.
 */
public class InvalidKeywordException extends Exception {
    /**
     * Constructs a duke.exceptions.InvalidKeywordException with the specified detail message.
     *
     * @param message The detail message explaining the cause of the exception.
     */
    public InvalidKeywordException(String message) {
        super(message);
    }
}
