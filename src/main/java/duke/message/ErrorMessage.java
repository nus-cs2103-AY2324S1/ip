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
     * Prints ErrorMessage.
     */
    @Override
    public void send() {
        System.out.println(createMessage(message));
    }
}
