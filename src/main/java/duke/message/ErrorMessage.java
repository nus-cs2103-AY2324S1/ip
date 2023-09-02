package duke.message;

/**
 * Represents the ErrorMessage.
 */
public class ErrorMessage extends Message {
    private final String message;

    /**
     * Constructor for ErrorMessage.
     * @param message Error message.
     */
    public ErrorMessage(String message) {
        this.message = message;
    }

    /**
     * Returns String representation of ErrorMessage.
     */
    @Override
    public String send() {
        return createMessage(message);
    }
}
