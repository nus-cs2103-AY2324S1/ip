package anto;

/**
 * Class represents an Exception in the Anto chatbot.
 */
public class AntoException extends Exception {

    /**
     * Creates a Anto Exception with the specified error message.
     *
     * @param errorMessage Error message for Anto exception.
     */
    public AntoException(String errorMessage) {
        super(errorMessage);
    }
}
