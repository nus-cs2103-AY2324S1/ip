package todoify.chatbot.exception;

/**
 * A runtime exception caused by chatbot activities.
 */
public class ChatbotRuntimeException extends RuntimeException {

    /**
     * Constructs a new runtime exception caused by chatbot activities.
     *
     * @param message The associated message.
     */
    public ChatbotRuntimeException(String message) {
        super(message);
    }

}
