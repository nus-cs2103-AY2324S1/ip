package todoify.chatbot.exception.command;

/**
 * A checked exception caused by chatbot internal command processing, specifically when a command processed is
 * irrelevant to the type of processing performed.
 */
public class ChatbotIrrelevantOperationException extends ChatbotCommandException {
    /**
     * Constructs a new checked exception due to a command being irrelevant to the parsed category, such as trying to
     * parse a number in a command operation type that does not expect numbers.
     *
     * @param message The associated message.
     */
    public ChatbotIrrelevantOperationException(String message) {
        super(message);
    }
}
