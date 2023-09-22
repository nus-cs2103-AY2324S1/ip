package smolbrain.exception;

/**
 * Handles when keyword missing.
 */
public class MissingKeywordException extends Exception {

    /**
     * Creates the exception.
     */
    public MissingKeywordException() {
        super();
    }

    /**
     * Returns the string representation of the exception.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "Please provide a keyword for finding task(s).";
    }

}
