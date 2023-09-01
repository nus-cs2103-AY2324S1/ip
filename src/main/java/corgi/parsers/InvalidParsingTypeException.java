package corgi.parsers;

/**
 * Custom exception class for parsing errors related to invalid types.
 */
public class InvalidParsingTypeException extends ParsingException{
    /**
     * Constructs a new InvalidParsingTypeException with the specified error message.
     *
     * @param msg The error message describing the invalid parsing type error.
     */
    public InvalidParsingTypeException(String msg) {
        super(msg);
    }
}
