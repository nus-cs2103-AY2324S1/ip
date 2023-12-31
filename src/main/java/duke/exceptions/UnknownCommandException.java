package duke.exceptions;

/**
 * Represents an exception that is thrown when an unrecognized or invalid command is encountered.
 */
public class UnknownCommandException extends Exception {

    /**
     * Constructs a new UnknownCommandException with a specific message describing the invalid command.
     *
     * @param message The detailed information about the unrecognized or invalid command.
     */
    public UnknownCommandException(String message) {
        super(message);
    }
}
