package jeeves.exception;

/**
 * Custom exception that is thrown when the id provided does not belong to a task on the list.
 * Encountered when attempting to mark, unmark or delete a task.
 */
public class OutOfBoundIdException extends Exception {

    /**
     * Default constructor for OutOfBoundIdException.
     * Calls the super constructor with the custom error message
     */
    public OutOfBoundIdException() {
        super("I cannot do that as that is not a valid Task ID "
                + "(ID provided does not exist)\n");
    }
}