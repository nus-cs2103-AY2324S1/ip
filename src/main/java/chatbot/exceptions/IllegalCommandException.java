package chatbot.exceptions;

/**
 * Class that represents an exception due to an invalid command provided by user.
 */
public class IllegalCommandException extends ChatBotException {
    @Override
    public String toString() {
        return "Sorry, I'm not sure what that means...";
    }
}
