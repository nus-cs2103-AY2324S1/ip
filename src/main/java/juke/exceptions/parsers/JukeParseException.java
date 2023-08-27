package juke.exceptions.parsers;

import juke.exceptions.JukeException;

/**
 * Represents an error thrown when any parser encounters an error with parsing
 * some input data.
 */
public class JukeParseException extends JukeException {
    /**
     * Constructor to create a {@code JukeParseException}.
     *
     * @param err Error description
     */
    public JukeParseException(String err) {
        super(err);
    }
}
