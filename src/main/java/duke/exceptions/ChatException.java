package duke.exceptions;

/**
 * Represents an exception specific to the chatbot.
 */
public class ChatException extends Exception {
    public ChatException(String message) {
        super(message);
    }
}
