package socrates.data.exception;

/**
 * Represents an invalid keyword for a {@code FindCommand}.
 */
public class InvalidKeywordException extends SocratesException {
    /**
     * Returns an instance of {@code SocratesException} with the given error message.
     *
     */
    public InvalidKeywordException() {
        super("The keyword must be specified and a single word.");
    }
}
