package duke.data.exception;

/**
 * Represents an invalid keyword for a {@code FindCommand}.
 */
public class InvalidKeywordException extends DukeException {
    /**
     * Returns an instance of {@code DukeException} with the given error message.
     *
     */
    public InvalidKeywordException() {
        super("The keyword must be specified and a single word.");
    }
}
