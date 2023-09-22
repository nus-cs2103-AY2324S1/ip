package smolbrain.exception;

/**
 * Handles out of bound array access id given by user.
 */
public class InvalidRangeException extends Exception {

    /**
     * Creates the exception.
     */
    public InvalidRangeException() {
        super();
    }

    /**
     * Returns the string representation of the exception.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "Please provide a valid number within the range.";
    }

}
