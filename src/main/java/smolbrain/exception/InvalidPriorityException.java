package smolbrain.exception;

/**
 * Handles invalid priority level given by user.
 */
public class InvalidPriorityException extends Exception {

    /**
     * Creates the exception.
     */
    public InvalidPriorityException() {
        super();
    }

    /**
     * Returns the string representation of the exception.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "Please provide a valid priority level from 0 to 3.";
    }

}
