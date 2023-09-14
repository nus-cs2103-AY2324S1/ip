package rayshawn.chatbot.exceptions;

/**
 * Signals that some data does not fulfill the constraints.
 */
public class ChatBotException extends Exception {

    /**
     * Constructor for ChatBotException.
     *
     * @param message contains relevant information on the failed constraint(s)
     */
    public ChatBotException(String message) {
        super(message);
    }
}
