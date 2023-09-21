package todoify.chatbot.exception.command;

/**
 * A checked exception caused by chatbot internal command processing encountering an error that is not from within
 * the chatbot, like a disk read-write error.
 */
public class ChatbotExternalErrorException extends ChatbotCommandException {
    /**
     * Constructs a new checked exception signifying the processed command has failed due to an external error, like
     * a disk read-write error.
     *
     * @param message The associated message.
     */
    public ChatbotExternalErrorException(String message, Exception exception) {
        super(String.format(
                "%s\n\nThe error was: [%s] %s",
                message.endsWith(".") ? message : (message + '.'),
                exception.getClass().getSimpleName(),
                exception.getLocalizedMessage()
        ));
    }
}
