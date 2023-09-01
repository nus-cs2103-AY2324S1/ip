package parsers;

/**
 * Represents an exception that is thrown when an error occurs due to an invalid command format.
 */
public class InvalidCommandFormatException extends InvalidParsingFormatException {
    /**
     * Initializes a new InvalidCommandFormatException with the specified error message.
     *
     * @param msg The error message describing the cause of the exception.
     */
    public InvalidCommandFormatException(String msg) {
        super(msg);
    }
}
