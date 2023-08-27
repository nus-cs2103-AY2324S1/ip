package juke.exceptions.parsers;

/**
 * Represents an error with the parsing the datafile.
 */
public class JukeDataFileParseException extends JukeParseException {
    /**
     * Constructor to create a JukeDataFileParseException.
     * @param err Error description
     */
    public JukeDataFileParseException(String err) {
        super(err);
    }
}
