package todoify.chatbot.exception;

/**
 * A checked exception caused by chatbot activities.
 */
public class ChatbotException extends Exception {

    /**
     * Constructs a new checked exception caused by chatbot activities.
     *
     * @param message The associated message.
     */
    public ChatbotException(String message) {
        super(message);
    }

}
