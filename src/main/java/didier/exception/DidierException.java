package didier.exception;

/**
 * Thrown to indicate that some error occurred during the user interaction with Didier. This is the
 * parent class of all the checked exceptions that could be thrown due to user interaction with Didier.
 */
public class DidierException extends Exception {
    public DidierException(String message) {
        super(message);
    }
}
