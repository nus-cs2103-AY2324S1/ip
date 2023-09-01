package corgi.parsers;

/**
 * Custom exception class for parsing errors related to invalid formats.
 */
public class InvalidParsingFormatException extends ParsingException{
    /**
     * Constructs a new InvalidParsingFormatException with the specified error message.
     *
     * @param msg The error message describing the invalid parsing format error.
     */
    public InvalidParsingFormatException(String msg) {
        super(msg);
    }
}
