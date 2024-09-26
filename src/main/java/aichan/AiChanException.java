package aichan;

/**
 * Represents exception caused by the chatbot AiChan.
 * Inherits from the Exception class.
 */
public class AiChanException extends Exception {

    /**
     * Constructs an AiChanException with error message.
     *
     * @param errorMsg The message indicating the details of the error.
     */
    public AiChanException(String errorMsg) {
        super(errorMsg);
    }
}
