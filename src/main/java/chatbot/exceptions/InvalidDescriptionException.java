package chatbot.exceptions;
/**
 * Exception that is thrown when a command is correct but the description
 * is wrong.
 * 
 * @author Owen Yeo
 */
public class InvalidDescriptionException extends ChatBotException {

    public InvalidDescriptionException(String e) {
        super(e);
    }

}