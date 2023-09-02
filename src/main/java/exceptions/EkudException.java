package exceptions;

/**
 * Represents an exception thrown by the Ekud chatbot.
 */
public class EkudException extends Exception {
    public EkudException(String message) {
        super(message);
    }
}
