package todoify.chatbot.exception;

/**
 * A runtime exception caused by chatbot processing.
 */
public class ChatbotRuntimeException extends RuntimeException {

    /**
     * Constructs a new runtime exception caused by chatbot processing.
     *
     * @param message The associated message.
     */
    public ChatbotRuntimeException(String message) {
        super(message);
    }

}
