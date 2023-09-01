package noac;

/**
 * Exception class for specific error for the Noac chatbot
 */
public class NoacException extends Exception{

    /**
     * Create the new exception with the message.
     *
     * @param errMessage The error message.
     */
    public NoacException(String errMessage) {
        super(errMessage);
    }
}
