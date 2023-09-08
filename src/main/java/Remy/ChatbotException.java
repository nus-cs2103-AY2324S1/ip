package remy;

/**
 * Exception for this Chatbot.
 */
public class ChatbotException extends Exception {

    /**
     * Constructs new ChatbotException with an error message.
     * @param message The error message.
     */
    public ChatbotException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
