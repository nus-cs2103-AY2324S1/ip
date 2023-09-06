package exceptions;

/**
 * Represents invalid commands by the user to the chatbot.
 */
public class EkudInvalidCommandException extends EkudException {
    public EkudInvalidCommandException(String message) {
        super(message);
    }
}
