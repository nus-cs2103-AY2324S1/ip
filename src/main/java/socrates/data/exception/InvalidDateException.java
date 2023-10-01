package socrates.data.exception;

/**
 * Represents an exception due to an invalid date.
 */
public class InvalidDateException extends SocratesException {

    /**
     * Returns an instance of {@code SocratesException} with the given error message.
     *
     */
    public InvalidDateException() {
        super("The dates must be filled in \"yyyy-mm-dd\" format.");
    }
}
