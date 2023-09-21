package duke.exception;

/**
 * Exceptions that arise when the user input is not in the correct format.
 */
public class DukeWrongCommandFormatException extends DukeParserException {
    public DukeWrongCommandFormatException(String message) {
        super(message);
    }
}
