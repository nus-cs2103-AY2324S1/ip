package duke.parser;

/**
 * Represents an error when parsing user input.
 */
public class DukeParseException extends Exception {
    public DukeParseException(String message) {
        super(message);
    }
}
