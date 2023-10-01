package socrates.data.exception;

/**
 * Represents an exception with the SoCrates chatbot.
 */
public class SocratesException extends Exception {

    /**
     * Returns an instance of {@code SocratesException} with the given error message.
     *
     * @param message The  error message of the exception.
     */
    public SocratesException(String message) {
        super(message);
    }
}
