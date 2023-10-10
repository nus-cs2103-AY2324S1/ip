package bert.exceptions;

/**
 * Represents an exception in the Bert chatbot program.
 */
public class BertException extends Exception {
    /**
     * Constructs a BertException instance.
     *
     * @param message The message describing the exception that occurred in the chatbot program.
     */
    public BertException(String message) {
        super(message);
    }
}
