package corgi.parsers;

/**
 * Represents an exception that is thrown when an error occurs due to an invalid command type.
 */
public class InvalidCommandTypeException extends InvalidParsingTypeException{
    /**
     * Initializes a new InvalidCommandTypeException with the specified error message.
     *
     * @param msg The error message describing the cause of the exception.
     */
    public InvalidCommandTypeException(String msg) {
        super(msg);
    }
}
