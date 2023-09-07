package duke.exception;

/**
 * Throw any exception related to the chatbot
 */
public class ChattyException extends Exception {
    public ChattyException(String message) {
        super(message);
    }
}

