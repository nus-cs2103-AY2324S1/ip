package chatbot.exception;

/**
 * A checked exception caused by chatbot processing.
 */
public class ChatbotException extends Exception {

    /**
     * Constructs a new checked exception caused by chatbot processing.
     *
     * @param message The associated message.
     */
    public ChatbotException(String message) {
        super(message);
    }

}
