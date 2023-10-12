package duke.exceptions;

/**
 * Represents an exception that is thrown when an unknown command is encountered.
 */
public class UnknownCommandException extends Exception {

    /**
     * Constructs an UnknownCommandException with a formatted error message.
     *
     * @param message The unknown command that caused the exception.
     */
    public UnknownCommandException(String message) {
        super(String.format("\u2639 OOPS!!! I'm sorry, but I don't know what %s means :-(", message));
    }
}
