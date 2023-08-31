package duke.exceptions;

/**
 * Represents an exception that is thrown when an invalid or nonexistent index is used.
 */
public class NoIndexException extends Exception {

    /**
     * Constructs a NoIndexException with a formatted error message.
     *
     * @param message The index value that caused the exception.
     */
    public NoIndexException(String message) {
        super(String.format("☹ OOPS!!! The index %s does not exist.", message));
    }
}
