package anto;

/**
 * Anto Exception where Index given is invalid.
 */
public class InvalidIndexException extends AntoException {
    /**
     * Creates an invalid index exception with error message.
     *
     * @param errorMessage String with description of exception.
     */
    public InvalidIndexException(String errorMessage) {
        super("anto.InvalidIndexException: " + errorMessage);
    }
}
