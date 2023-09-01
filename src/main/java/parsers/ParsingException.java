package parsers;

/**
 * Custom exception class to handle parsing errors.
 */
public class ParsingException extends Exception {
    /**
     * Constructs a new ParsingException with the specified error message.
     *
     * @param message The error message describing the parsing error.
     */
    public ParsingException(String message) {
        super(message);
    } 
}
