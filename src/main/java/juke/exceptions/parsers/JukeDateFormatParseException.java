package juke.exceptions.parsers;

/**
 * Represents an error with the parsing of some datetime input.
 */
public class JukeDateFormatParseException extends JukeParseException {
    /**
     * Constructor to create a JukeDateFormatParseException.
     * @param err Error description
     */
    public JukeDateFormatParseException(String err) {
        super(err);
    }
}
