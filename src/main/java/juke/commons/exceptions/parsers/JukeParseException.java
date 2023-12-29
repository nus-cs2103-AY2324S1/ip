package juke.commons.exceptions.parsers;

import juke.commons.exceptions.JukeException;

/**
 * Represents an error thrown when any parser encounters an error with parsing
 * some input data. This exception is the superclass of all other exceptions that
 * involve some parsing error in Juke.
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
