package todoify.chatbot.exception.command;

import todoify.chatbot.exception.ChatbotException;

/**
 * A checked exception caused by chatbot internal command processing.
 */
public class ChatbotCommandException extends ChatbotException {

    /**
     * Constructs a new checked exception caused by chatbot internal command processing.
     *
     * @param message The associated message.
     */
    public ChatbotCommandException(String message) {
        super(message);
    }
}
