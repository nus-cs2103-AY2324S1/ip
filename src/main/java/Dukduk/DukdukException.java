package dukduk;

/**
 * Custom exception class for Dukduk chatbot.
 * This exception is thrown to indicate errors specific to the Dukduk chatbot.
 */
public class DukdukException extends Exception {

    /**
     * Constructs a new DukdukException with the specified error message.
     *
     * @param message The error message associated with this exception.
     */
    public DukdukException(String message) {
        super(message);
    }
}
