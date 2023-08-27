package robert.exception;

/**
 * An exception that is thrown when an error occurs within Robert.
 *
 * @author Lee Zhan Peng
 */
public class RobertException extends Exception {

    /** The error message */
    private final String errorMessage;

    /**
     * Constructs RobertException using an error message.
     *
     * @param errorMessage the error message to be tagged to the exception.
     */
    public RobertException(String errorMessage) {
        super("OOPS!!! " + errorMessage);
        this.errorMessage = "OOPS!!! " + errorMessage;
    }

    /**
     * Returns the error message unique to Robert.
     *
     * @return the error message.
     */
    @Override
    public String toString() {
        return errorMessage;
    }
}
