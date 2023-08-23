/**
 * Custom exception class for handling errors specific to the Sae chatbot.
 */
public class SaeException extends Exception {

    /**
     * Constructs a new SaeException with the specified error message.
     *
     * @param errorMessage The error message associated with the exception.
     */
    public SaeException(String errorMessage) {
        super(errorMessage);
    }
}
