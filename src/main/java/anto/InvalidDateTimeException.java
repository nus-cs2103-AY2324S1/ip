package anto;

/**
 * Anto Exception where String given is not in the correct DateTime format.
 */
public class InvalidDateTimeException extends AntoException {
    /**
     * Creates an Invalid DateTime Exception.
     *
     * @param msg Message of InvalidDateTimeException.
     */
    public InvalidDateTimeException(String msg) {
        super("InvalidDateException: " + msg);
    }
}
