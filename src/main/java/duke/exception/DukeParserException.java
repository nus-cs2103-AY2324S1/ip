package duke.exception;

/**
 * All exceptions that arise when parsing user input.
 */
public class DukeParserException extends DukeRuntimeException {
    public DukeParserException(String message) {
        super(message);
    }
}
