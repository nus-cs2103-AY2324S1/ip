package jeeves.exception;

/**
 * Custom exception that is thrown when the id provided is not a valid integer value. 
 * Encountered when attempting to mark, unmark or delete a task.
 */
public class NotIntegerIdException extends Exception {

    /**
     * Default constructor for NotIntegerIdException.
     * Calls the super constructor with the custom error message
     */
    public NotIntegerIdException() {
        super("I cannot do that as that is not a valid Task ID "
                + "(ID provided is not an integer)\n");
    }
}
