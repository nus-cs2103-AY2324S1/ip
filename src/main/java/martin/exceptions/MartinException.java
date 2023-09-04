package martin.exceptions;

/**
 * Represents an exception that is raised when running Martin chatbot.
 * 
 * @param message Error message to be printed out.
 */
public class MartinException extends Exception {
    public MartinException(String message) {
        super(message);
    }
}