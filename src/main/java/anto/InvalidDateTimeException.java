package anto;

/**
 * Anto Exception where String given is not in the correct DateTime format.
 */
public class InvalidDateTimeException extends AntoException {
    /**
     * Creates an Invalid DateTime Exception.
     */
    public InvalidDateTimeException() {
        super("InvalidDateException: OOPS!!! The datetime has to be in this format: dd-MM-yyyy HH:mm.");
    }
}
