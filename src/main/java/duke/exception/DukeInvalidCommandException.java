package duke.exception;

/**
 * Exceptions that arise when the user input is not a valid command.
 */
public class DukeInvalidCommandException extends DukeParserException {
    public DukeInvalidCommandException(String message) {
        super(message);
    }
}
