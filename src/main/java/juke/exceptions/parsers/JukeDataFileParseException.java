package juke.exceptions.parsers;

/**
 * Represents an error with the parsing the datafile.
 */
public class JukeDataFileParseException extends JukeParseException {
    /**
     * Creates an instance of {@code JukeDataFileParseException}.
     *
     * @param error Error description
     */
    public JukeDataFileParseException(String error) {
        super(error);
    }
}
