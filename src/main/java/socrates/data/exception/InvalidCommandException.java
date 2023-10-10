package socrates.data.exception;

/**
 * Repesents an exception due to an invalid command.
 */
public class InvalidCommandException extends SocratesException {
    /**
     * Returns an instance of {@code SocratesException} with the given error message.
     */
    public InvalidCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
