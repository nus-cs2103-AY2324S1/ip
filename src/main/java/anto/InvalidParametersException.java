package anto;

/**
 * Anto exception where the parameters given are invalid.
 */
public class InvalidParametersException extends AntoException {
    /**
     * Creates an invalid parameters exception.
     *
     * @param errorMessage String with description of exception.
     */
    public InvalidParametersException(String errorMessage) {
        super("anto.InvalidParametersException: " + errorMessage);
    }
}
