package juke.exceptions;

/**
 * Represents an error thrown when the parser for the data files cannot
 * parse the data.
 * <p>
 * This is an unchecked exception as we cannot anticipate when a parsing
 * error will occur.
 */
public class JukeParseException extends JukeException {
    /**
     * Constructor to create a JukeParseException.
     *
     * @param error Error description
     */
    public JukeParseException(String error) {
        super(error);
    }
}
