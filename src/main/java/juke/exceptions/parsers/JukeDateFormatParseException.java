package juke.exceptions.parsers;

/**
 * Represents an error with the parsing of some datetime input.
 */
public class JukeDateFormatParseException extends JukeParseException {
    /**
     * Creates an instance of {@code JukeDateFormatParseException}.
     *
     * @param error Error description
     */
    public JukeDateFormatParseException(String error) {
        super(error);
    }
}
