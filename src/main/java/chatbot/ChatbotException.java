package chatbot;

/**
 * Exception class specific for chatbot application
 */
public class ChatbotException extends Exception{

    /**
     * constructor for ChatbotException class
     * @param errorMessage error message
     */
    public ChatbotException(String errorMessage) {
        super(errorMessage);
    }
}
