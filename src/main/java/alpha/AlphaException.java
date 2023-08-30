package alpha;

/**
 * A class to handle exceptions related to ChatBot Alpha.
 */
public abstract class AlphaException extends Exception {

    public AlphaException(String errorMessage) {
        super(errorMessage);
    }
}
