package chatbot.exceptions;
/**
 * Exception that is thrown when an invalid command is keyed into the chatbot.
 * 
 * @author Owen Yeo
 */
public class InvalidCommandException extends ChatBotException{

    public InvalidCommandException(String e) {
        super(e);
    }
    
}
