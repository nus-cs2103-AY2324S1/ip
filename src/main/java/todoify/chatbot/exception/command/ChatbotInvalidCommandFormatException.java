package todoify.chatbot.exception.command;

/**
 * A checked exception caused by chatbot internal command processing, specifically when a command processed is
 * in a format that is not expected by the operation.
 */
public class ChatbotInvalidCommandFormatException extends ChatbotCommandException {
    /**
     * Constructs a new checked exception signifying the processed command is invalid, such as a format error.
     *
     * @param message The associated message.
     */
    public ChatbotInvalidCommandFormatException(String message) {
        super(message);
    }
}
