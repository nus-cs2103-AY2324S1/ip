package exceptions;
/**
 * Represents illegal arguments by the user after giving a command to the chatbot.
 */
public class EkudIllegalArgException extends EkudException {
    public EkudIllegalArgException(String message) {
        super(message);
    }
}
