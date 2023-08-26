package main.java.exceptions;

import main.java.exceptions.JukeException;

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
     * @param err Error description
     */
    public JukeParseException(String err) {
        super(err);
    }
}
