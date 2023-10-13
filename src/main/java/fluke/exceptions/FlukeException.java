package fluke.exceptions;

/**
 * This exception is thrown whenever an error occurs with Fluke.
 */
public class FlukeException extends Exception {
    /**
     * Constructs a FlukeException.
     * @param message Error message
     */
    public FlukeException(String message) {
        super(message);
    }
}
