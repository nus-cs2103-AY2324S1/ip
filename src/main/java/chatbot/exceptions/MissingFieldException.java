package chatbot.exceptions;

/**
 * Class that represents a generic exception due to a valid command in a wrong format.
 */
public abstract class MissingFieldException extends ChatBotException {
    @Override
    public String toString() {
        return "OOPS, your command is valid, but it is missing an important field";
    }
}
