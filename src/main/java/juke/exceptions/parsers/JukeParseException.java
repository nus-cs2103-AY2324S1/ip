package juke.exceptions.parsers;

import juke.exceptions.JukeException;

/**
 * Represents an error thrown when any parser encounters an error with parsing
 * some input data.
 */
public class JukeParseException extends JukeException {
    /**
     * Creates an instance of {@code JukeParseException}.
     *
     * @param error Error description
     */
    public JukeParseException(String error) {
        super(error);
    }
}
