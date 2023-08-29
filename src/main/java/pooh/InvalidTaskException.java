package pooh;

/**
 * Exception thrown when an invalid task is selected.
 * <p>
 * This exception extends from PoohException, and it overrides the toString method to provide
 * a custom error message indicating that the selected task is not valid.
 * </p>
 */
public class InvalidTaskException extends PoohException {
    
    /**
     * Returns a string representation of the exception, containing a custom error message.
     *
     * @return Custom error message indicating that the selected task is invalid.
     */
    @Override
    public String toString() {
        return "      ☹ OOPS!!! No such task! Please reselect carefully!";
    }
}
